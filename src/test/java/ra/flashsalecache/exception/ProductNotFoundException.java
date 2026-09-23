package ra.flashsalecache.exception;


public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String productId) {
        super("Không tìm thấy sản phẩm có mã: " + productId);
    }
}