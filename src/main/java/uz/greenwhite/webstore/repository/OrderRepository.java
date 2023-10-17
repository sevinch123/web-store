package uz.greenwhite.webstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.greenwhite.webstore.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
}