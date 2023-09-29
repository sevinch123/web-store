package uz.greenwhite.webstore.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.greenwhite.webstore.entity.Category;
import uz.greenwhite.webstore.entity.Users;
import uz.greenwhite.webstore.enums.UserRole;
import uz.greenwhite.webstore.repository.CategoryRepository;
import uz.greenwhite.webstore.repository.UserRepository;
import uz.greenwhite.webstore.service.CategoryService;

import java.io.File;

@Component
@AllArgsConstructor
public class AppInit implements ApplicationRunner {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder encoder;
    private final CategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Category category = new Category();
        category.setCategoryName("Texnika");
        categoryService.save(category);
        category.setCategoryId(null);
        category.setCategoryName("Mebel");
        category.setIsActive(false);
        categoryService.save(category);
//        new File("FILE").mkdir();
//        if (userRepository.findByUsername("admin").isEmpty()) {
//            Users admin = new Users();
//            admin.setRole(UserRole.MODERATOR);
//            admin.setFirstName("Administrator");
//            admin.setLogin("admin123");
//            admin.setPassword(encoder.encode("Admin123!"));
//
//            userRepository.save(admin);
//        }

//        if (userRepository.findByUsername("user").isEmpty()) {
//            User user = new User();
//            user.setRole(UserRole.USER);
//            user.setActive(false);
//            user.setVerificationCode(1234);
//            user.setFirstName("User");
//            user.setUsername("user123");
//            user.setPassword(encoder.encode("User123!"));
//
//            userRepository.save(user);
//        }
    }
}
