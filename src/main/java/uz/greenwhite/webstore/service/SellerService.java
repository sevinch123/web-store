package uz.greenwhite.webstore.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.greenwhite.webstore.entity.Users;
import uz.greenwhite.webstore.enums.UserStatus;
import uz.greenwhite.webstore.repository.SellerRepository;

@Service
@AllArgsConstructor
public class SellerService {
    private final SellerRepository repository;

    public Page<Users> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Users getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Users save(Users seller) {
        if (seller.getUserId() != null)
            throw new RuntimeException("ID should be a null");

        if (seller.getStatus() == UserStatus.PASSIVE) seller.setStatus(UserStatus.ACTIVE);

        return repository.save(seller);
    }

    public Users update(Users seller){
        if(seller.getUserId() == null)
            throw new RuntimeException("Id shouldn't be null");

        return repository.save(seller);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
