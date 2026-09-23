package ra.flashsalecache.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.flashsalecache.enity.Product;
import ra.flashsalecache.exception.ProductNotFoundException;
import ra.flashsalecache.repository.ProductRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductPriceService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Cacheable(
            value = "productPrices",
            key = "#productId",
            condition = "#productId != null && !#productId.trim().isEmpty()",
            unless = "#result == null"
    )
    public Integer getProductPrice(String productId) {
        validateProductId(productId);

        log.info(
                "Cache miss hoặc Redis lỗi, đọc giá từ Database: {}",
                productId
        );

        return productRepository.findById(productId)
                .map(Product::getPrice)
                .orElseThrow(() ->
                        new ProductNotFoundException(productId)
                );
    }

    @Transactional
    @CacheEvict(
            value = "productPrices",
            key = "#productId",
            condition = "#productId != null && !#productId.trim().isEmpty()"
    )
    public Integer updateProductPrice(
            String productId,
            Integer newPrice
    ) {
        validateProductId(productId);
        validatePrice(newPrice);

        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ProductNotFoundException(productId)
                );

        product.setPrice(newPrice);
        Product savedProduct = productRepository.save(product);

        log.info(
                "Đã cập nhật giá sản phẩm {} thành {} và xóa cache cũ",
                productId,
                newPrice
        );

        return savedProduct.getPrice();
    }

    private void validateProductId(String productId) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException(
                    "productId không được null hoặc rỗng"
            );
        }
    }

    private void validatePrice(Integer price) {
        if (price == null || price <= 0) {
            throw new IllegalArgumentException(
                    "Giá sản phẩm phải lớn hơn 0"
            );
        }
    }
}