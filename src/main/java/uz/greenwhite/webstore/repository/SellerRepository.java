package uz.greenwhite.webstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.greenwhite.webstore.entity.User;

@Repository
public interface SellerRepository extends JpaRepository<User, Long> {
}
