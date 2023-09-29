package uz.greenwhite.webstore.service;

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

    public Page<Product> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    public Product getById(Long ID) {
        return repository.findById(ID).orElse(null);
    }

    public void update(Product product) {
        repository.save(product);
    }

    public void save(Product product) {
        if(product.getIsActive() == null)
            product.setIsActive(true);
        repository.save(product);
    }

    public void delete(Long ID) {
        repository.deleteById(ID);
    }

}
