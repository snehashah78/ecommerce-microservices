package order_service.example.order_service.repository;

import order_service.example.order_service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(
            "SELECT SUM(o.totalPrice) FROM Order o"
    )
    Long getTotalSales();
}
