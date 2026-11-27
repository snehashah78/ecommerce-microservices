package payment_service.example.payment_service.dto;


import lombok.Data;

@Data
public class OrderResponse {
    private Long orderId;

    private Long productId;

    private Integer quantity;

    private Double totalAmount;

    private String status;

}
