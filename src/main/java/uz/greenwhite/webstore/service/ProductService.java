package uz.greenwhite.webstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.greenwhite.webstore.entity.Product;
import uz.greenwhite.webstore.repository.ProductRepository;


@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    @Autowired
    CategoryService categoryService;

    public List<Product> getAllByPriceGreaterThanOrderByPriceDesc(Long price,Pageable pageable){
        return repository.findAllByPriceGreaterThanOrderByPriceDesc(price);
    }

    public List<Product> getAllByPriceGreaterThanOrderByPriceAsc(Long price,Pageable pageable){
        return repository.findAllByPriceGreaterThanOrderByPriceAsc(price);
    }

    public List<Product> getAllByPriceLessThanOrderByPriceDesc(Long price,Pageable pageable){
        return repository.findAllByPriceLessThanOrderByPriceDesc(price);
    }

    public List<Product> getAllByPriceLessThanOrderByPriceAsc(Long price,Pageable pageable){
        return repository.findAllByPriceLessThanOrderByPriceAsc(price);
    }

    public List<Product> getByPriceBetweenOrderByPriceDesc(Long minPrice, Long maxPrice,Pageable pageable){
        return repository.findByPriceBetweenOrderByPriceDesc(minPrice,maxPrice);
    } 

    public List<Product> getByPriceBetweenOrderByPriceAsc(Long minPrice, Long maxPrice,Pageable pageable){
        return repository.findByPriceBetweenOrderByPriceAsc(minPrice,maxPrice);
    } 
    //

    public List<Product> getAllByCategoryAndPriceGreaterThanOrderByPriceDesc(Long categoryId,Long price,Pageable pageable){
        return repository.findAllByCategoryAndPriceGreaterThanOrderByPriceDesc(categoryService.getById(categoryId),price);
    }

    public List<Product> getAllByCategoryAndPriceGreaterThanOrderByPriceAsc(Long categoryId,Long price,Pageable pageable){
        return repository.findAllByCategoryAndPriceGreaterThanOrderByPriceAsc(categoryService.getById(categoryId),price);
    }

    public List<Product> getAllByCategoryAndPriceLessThanOrderByPriceDesc(Long categoryId,Long price,Pageable pageable){
        return repository.findAllByCategoryAndPriceLessThanOrderByPriceDesc(categoryService.getById(categoryId),price);
    }

    public List<Product> getAllByCategoryAndPriceLessThanOrderByPriceAsc(Long categoryId,Long price,Pageable pageable){
        return repository.findAllByCategoryAndPriceLessThanOrderByPriceAsc(categoryService.getById(categoryId),price);
    }

    public List<Product> getByCategoryAndPriceBetweenOrderByPriceDesc(Long categoryId,Long minPrice, Long maxPrice,Pageable pageable){
        return repository.findByCategoryAndPriceBetweenOrderByPriceDesc(categoryService.getById(categoryId),minPrice,maxPrice);
    } 

    public List<Product> getByCategoryAndPriceBetweenOrderByPriceAsc(Long categoryId,Long minPrice, Long maxPrice,Pageable pageable){
        return repository.findByCategoryAndPriceBetweenOrderByPriceAsc(categoryService.getById(categoryId),minPrice,maxPrice);
    } 

    public List<Product> getAllByCategoryAndPriceGreaterThan(Long categoryId,Long price,Pageable pageable){
        return repository.findAllByCategoryAndPriceGreaterThan(categoryService.getById(categoryId),price);
    }

    public List<Product> getAllByCategoryAndPriceLessThan(Long categoryId,Long price,Pageable pageable){
        return repository.findAllByCategoryAndPriceLessThan(categoryService.getById(categoryId),price);
    }

    public List<Product> getByCategoryAndPriceBetween(Long categoryId,Long minPrice, Long maxPrice,Pageable pageable){
        return repository.findByCategoryAndPriceBetween(categoryService.getById(categoryId),minPrice,maxPrice);
    } 

    public List<Product> getAllByPriceGreaterThan(Long price,Pageable pageable){
        return repository.findAllByPriceGreaterThan(price);
    }
    
    public List<Product> getAllByPriceLessThan(Long price,Pageable pageable){
        return repository.findAllByPriceLessThan(price);
    }

    public List<Product> getByPriceBetween(Long minPrice, Long maxPrice,Pageable pageable){
        return repository.findByPriceBetween(minPrice,maxPrice);
    } 

    public Page<Product> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public Page<Product> getAllByOrderByPriceAsc(Pageable pageable) {
        return repository.findAllByOrderByPriceAsc(pageable);
    }
    public Page<Product> getAllByOrderByPriceDesc(Pageable pageable) {
        return repository.findAllByOrderByPriceDesc(pageable);
    }
    public List<Product> getByCategory(Long categoryId,Pageable pageable) {
        return repository.findAllByCategory(categoryService.getById(categoryId));
    }
    public List<Product> getByCategoryOrderByPriceAsc(Long categoryId,Pageable pageable) {
        return repository.findAllByCategoryOrderByPriceAsc(categoryService.getById(categoryId));
    }
    public List<Product> getByCategoryOrderByPriceDesc(Long categoryId , Pageable pageable) {
        return repository.findAllByCategoryOrderByPriceDesc(categoryService.getById(categoryId));
    }
    public Product getById(Long ID) {
        return repository.findById(ID).orElse(null);
    }

    public Product update(Product product) {
        if(product.getQuantity() <= 0) {
            product.setIsActive(false);
        }
        return repository.save(product);
    }

    public Product save(Product product) {
        if(product.getIsActive() == null)
            product.setIsActive(true);
        if(product.getQuantity() <= 0) {
            product.setIsActive(false);
        }
        return repository.save(product);
    }

    public void deleteById(Long ID) {
        repository.deleteById(ID);
    }

}
