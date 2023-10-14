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

    public Page<Product> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<Product> getByCategory(Long categoryId, Pageable pageable) {
        return repository.findAllByCategory(categoryService.getById(categoryId));
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
