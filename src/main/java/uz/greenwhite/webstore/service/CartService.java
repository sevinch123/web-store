package uz.greenwhite.webstore.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.greenwhite.webstore.entity.Cart;
import uz.greenwhite.webstore.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository repository;

    public Page<Cart> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ArrayList<Cart> getAllByToken(String token) {
        return (ArrayList<Cart>) repository.findAllByToken(token);
    }

    public boolean isExist(Cart cart) {
        return repository.findByTokenAndProduct(cart.getToken(), cart.getProduct()).isPresent();
    }

    public Cart save(Cart cart) {
        if(!isExist(cart))
            return repository.save(cart);

        return cart;
    }

    public Cart update(Cart cart) {
        if(isExist(cart))
            return repository.save(cart);

        return cart;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void delete(Cart cart) {
        deleteById(cart.getId());
    }


}
