package order_service.example.order_service.service;


import order_service.example.order_service.dto.OrderRequest;
import order_service.example.order_service.entity.Order;
import order_service.example.order_service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    // OLD METHOD (Do not remove)
    public Order saveOrder(Long productId) {

        Order order = new Order();

        order.setProductId(productId);
        order.setStatus("PLACED");

        return orderRepository.save(order);
    }

    // NEW METHOD
    public Order createOrder(OrderRequest request) {

        Order order = new Order();

        order.setUserId(request.getUserId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());

        // Demo Price
        double productPrice = 1500.0;

        order.setTotalPrice( productPrice * request.getQuantity()
        );

        order.setStatus("PLACED");

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order Not Found"));
    }
    public Long getTotalSales() {
        return orderRepository.getTotalSales();
    }
}

