package ra.flashsalecache.controller;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.flashsalecache.dto.UpdatePriceRequest;
import ra.flashsalecache.service.ProductPriceService;

import java.util.Map;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductPriceController {

    private final ProductPriceService productPriceService;

    @GetMapping("/{productId}/price")
    public ResponseEntity<Map<String, Object>> getPrice(
            @PathVariable String productId
    ) {
        Integer price =
                productPriceService.getProductPrice(productId);

        return ResponseEntity.ok(
                Map.of(
                        "productId", productId,
                        "price", price
                )
        );
    }

    @PutMapping("/{productId}/price")
    public ResponseEntity<Map<String, Object>> updatePrice(
            @PathVariable String productId,
            @Valid @RequestBody UpdatePriceRequest request
    ) {
        Integer updatedPrice =
                productPriceService.updateProductPrice(
                        productId,
                        request.newPrice()
                );

        return ResponseEntity.ok(
                Map.of(
                        "message", "Cập nhật giá thành công",
                        "productId", productId,
                        "price", updatedPrice
                )
        );
    }
}