package uz.greenwhite.webstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.greenwhite.webstore.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    @Query(value = "SELECT * FROM users WHERE login=?",nativeQuery = true)
    Optional<Users> findByUsername(String username);
}
