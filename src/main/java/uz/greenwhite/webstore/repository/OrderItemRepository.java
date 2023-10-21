package uz.greenwhite.webstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.greenwhite.webstore.entity.*;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
   List<OrderItem> findAllByOrders(Orders orders);

}
