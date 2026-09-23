package ra.flashsalecache.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdatePriceRequest(

        @NotNull(message = "Giá mới không được để trống")
        @Positive(message = "Giá mới phải lớn hơn 0")
        Integer newPrice
) {
}