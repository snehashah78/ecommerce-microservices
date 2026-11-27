package payment_service.example.payment_service.service;


import org.springframework.stereotype.Service;
import payment_service.example.payment_service.dto.OrderResponse;
import payment_service.example.payment_service.dto.PaymentRequest;
import payment_service.example.payment_service.dto.PaymentResponse;
import payment_service.example.payment_service.entity.Payment;
import payment_service.example.payment_service.feign.OrderClient;
import payment_service.example.payment_service.repository.PaymentRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderClient orderClient;
    private final RazorpayClient razorpayClient;

    public PaymentService(
            PaymentRepository paymentRepository,
            OrderClient orderClient,
            RazorpayClient razorpayClient) {

        this.paymentRepository = paymentRepository;
        this.orderClient = orderClient;
        this.razorpayClient = razorpayClient;
    }

    public PaymentResponse processPayment(
            PaymentRequest request) {

        OrderResponse order =
                orderClient.getOrderById(
                        request.getOrderId());

        if(order == null) {
            throw new RuntimeException(
                    "Order Not Found");
        }

        Payment payment = new Payment();

        payment.setOrderId(order.getOrderId());

        payment.setAmount(
                order.getTotalAmount());

        payment.setPaymentMethod(
                request.getPaymentMethod());

        payment.setPaymentStatus(
                "SUCCESS");

        Payment saved =
                paymentRepository.save(payment);

        return new PaymentResponse(
                saved.getPaymentId(),
                saved.getPaymentStatus(),
                "Payment Successful"
        );

    }
    public String createRazorpayOrder(
            Integer amount,
            Long orderId)
            throws Exception {

        JSONObject orderRequest =
                new JSONObject();

        orderRequest.put(
                "amount",
                amount * 100
        );

        orderRequest.put(
                "currency",
                "INR"
        );

        orderRequest.put(
                "receipt",
                orderId.toString()
        );

        Order razorpayOrder =
                razorpayClient.orders.create(
                        orderRequest
                );

        return razorpayOrder.toString();
    }
}