package payment_service.example.payment_service.feign;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import payment_service.example.payment_service.dto.OrderResponse;



@FeignClient(name = "order-service",
        url = "http://localhost:8081")
public interface OrderClient {

    @GetMapping("/orders/{id}")
    OrderResponse getOrderById(
            @PathVariable Long id
    );
}
