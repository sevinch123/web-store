package uz.greenwhite.webstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.greenwhite.webstore.entity.Category;
import uz.greenwhite.webstore.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(Category category);
    List<Product> findAllByCategoryOrderByPriceAsc(Category category);
    List<Product> findAllByCategoryOrderByPriceDesc(Category category);
    
    Page<Product> findAllByOrderByPriceAsc(Pageable pageable);
    Page<Product> findAllByOrderByPriceDesc(Pageable pageable);
    
    List<Product> findAllByPriceGreaterThan(Long price);
    List<Product> findAllByPriceLessThan(Long price);
    List<Product> findByPriceBetween(Long minPrice, Long maxPrice);

    List<Product> findAllByCategoryAndPriceGreaterThan(Category category,Long price);
    List<Product> findAllByCategoryAndPriceLessThan(Category category,Long price);
    List<Product> findByCategoryAndPriceBetween(Category category,Long minPrice, Long maxPrice);
    
    List<Product> findAllByCategoryAndPriceGreaterThanOrderByPriceDesc(Category category,Long price);
    List<Product> findAllByCategoryAndPriceGreaterThanOrderByPriceAsc(Category category,Long price);
    List<Product> findAllByCategoryAndPriceLessThanOrderByPriceAsc(Category category,Long price);
    List<Product> findAllByCategoryAndPriceLessThanOrderByPriceDesc(Category category,Long price);
    List<Product> findByCategoryAndPriceBetweenOrderByPriceDesc(Category category,Long minPrice, Long maxPrice);
    List<Product> findByCategoryAndPriceBetweenOrderByPriceAsc(Category category,Long minPrice, Long maxPrice);
    
    List<Product> findAllByPriceGreaterThanOrderByPriceDesc(Long price);
    List<Product> findAllByPriceGreaterThanOrderByPriceAsc(Long price);
    List<Product> findAllByPriceLessThanOrderByPriceAsc(Long price);
    List<Product> findAllByPriceLessThanOrderByPriceDesc(Long price);
    List<Product> findByPriceBetweenOrderByPriceDesc(Long minPrice, Long maxPrice);
    List<Product> findByPriceBetweenOrderByPriceAsc(Long minPrice, Long maxPrice);

}