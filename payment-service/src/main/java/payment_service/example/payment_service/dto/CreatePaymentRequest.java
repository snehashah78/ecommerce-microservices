package payment_service.example.payment_service.dto;

import lombok.Data;

@Data
public class CreatePaymentRequest {

    private Long orderId;

    private Integer amount;
}
