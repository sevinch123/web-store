package uz.greenwhite.webstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.greenwhite.webstore.entity.Orders;
import java.util.List;
import org.springframework.data.domain.Page;
import uz.greenwhite.webstore.enums.OrderStatus;
import org.springframework.data.domain.Pageable;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByStatus(OrderStatus status);
    List<Orders> findAllByStatusOrderByCreatedOnAsc(OrderStatus status);
    List<Orders> findAllByStatusOrderByCreatedOnDesc(OrderStatus status);
    Page<Orders> findAllByOrderByCreatedOnAsc(Pageable pageable);
    Page<Orders> findAllByOrderByCreatedOnDesc(Pageable pageable);

}