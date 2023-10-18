package uz.greenwhite.webstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.greenwhite.webstore.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
