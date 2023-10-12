package uz.greenwhite.webstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.greenwhite.webstore.entity.Cart;
import uz.greenwhite.webstore.entity.Product;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

//    Page<Cart> findByToken(String token);

//    @Query(value = "select * from Cart c where c.product.productId = ?1 and c.token = ?2")
//    Optional<Cart> findCartByTokenAndProduct(Product product, String token);
    Optional<Cart> findByTokenAndProduct(String token, Product product);

    List<Cart> findAllByToken(String token);
}
