package order_service.example.order_service.controller;


import order_service.example.order_service.dto.OrderRequest;
import order_service.example.order_service.entity.Order;
import order_service.example.order_service.client.InventoryClient;
import order_service.example.order_service.repository.OrderRepository;
import order_service.example.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    @Autowired
    private InventoryClient inventoryClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    // GET ALL ORDERS
    @GetMapping
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    // OLD API (Keep It)
    @GetMapping("/{id}")
    public Order placeOrder(
            @PathVariable Long id) {

        inventoryClient.checkInventory(id);

        return orderService.saveOrder(id);
    }

    // NEW ORDER API
    @PostMapping
    public Order createOrder(
            @RequestBody OrderRequest request) {

        inventoryClient.checkInventory(
                request.getProductId());

        return orderService.createOrder(request);
    }
    // GET ORDER BY ID
    @GetMapping("/details/{id}")
    public Order getOrderById(
            @PathVariable Long id) {

        return orderService.getOrderById(id);
    }
    @GetMapping("/count")
    public Long getOrderCount() {
        return orderRepository.count();
    }
    @GetMapping("/sales")
    public Long getSales() {
        return orderService.getTotalSales();
    }

}