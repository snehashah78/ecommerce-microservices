package payment_service.example.payment_service.dto;


import lombok.Data;

@Data
public class PaymentRequest {
    private Long orderId;

    private Double amount;

    private String paymentMethod;
}
