package payment_service.example.payment_service.controller;


import org.springframework.web.bind.annotation.*;
import payment_service.example.payment_service.dto.CreatePaymentRequest;
import payment_service.example.payment_service.dto.PaymentRequest;
import payment_service.example.payment_service.dto.PaymentResponse;
import payment_service.example.payment_service.service.PaymentService;

@RestController
@RequestMapping("/payments")
@CrossOrigin("*")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public PaymentResponse makePayment(
            @RequestBody PaymentRequest request) {

        return paymentService.processPayment(request);
    }
    @PostMapping("/create-order")
    public String createRazorpayOrder(
            @RequestBody CreatePaymentRequest request)
            throws Exception {

        return paymentService.createRazorpayOrder(
                request.getAmount(),
                request.getOrderId()
        );
    }
}
