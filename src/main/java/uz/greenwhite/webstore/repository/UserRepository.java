package uz.greenwhite.webstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.greenwhite.webstore.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
