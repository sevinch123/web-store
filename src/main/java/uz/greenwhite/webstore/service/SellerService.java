//package uz.greenwhite.webstore.service;
//
//import lombok.AllArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import uz.greenwhite.webstore.entity.User;
//import uz.greenwhite.webstore.repository.UserRepository;
//
//@Service
//@AllArgsConstructor
//public class SellerService {
//    private final UserRepository repository;
//
//    private final PasswordEncoder encoder;
//
//    public Page<User> getAll(Pageable pageable) {
//        return repository.findAll(pageable);
//    }
//
//    public User getById(Long id) {
//        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
//    }
//
//    public User save(User seller) {
//        if (seller.getUserId() != null)
//            throw new RuntimeException("ID should be a null");
//
//        if (seller.getIsActive() == null) seller.setIsActive(true);
//
//        seller.setPassword(encoder.encode(seller.getPassword()));
//        return repository.save(seller);
//    }
//
//    public User update(User seller){
//        if(seller.getUserId() == null)
//            throw new RuntimeException("Id shouldn't be null");
//
//        return repository.save(seller);
//    }
//
//    public void deleteById(Long id) {
//        repository.deleteById(id);
//    }
//}
