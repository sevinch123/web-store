package uz.greenwhite.webstore.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.greenwhite.webstore.entity.Category;
import uz.greenwhite.webstore.entity.User;
import uz.greenwhite.webstore.enums.UserRole;
import uz.greenwhite.webstore.service.CategoryService;
import uz.greenwhite.webstore.service.UserService;

@Component
@AllArgsConstructor
public class AppInit implements ApplicationRunner {
    //
    private final UserService userService;
    private final PasswordEncoder encoder;
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

        if (userService.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setFirstName("Ilon");
            admin.setLastName("Mask");
            admin.setRole(UserRole.MODERATOR);
            admin.setUsername("admin");
            admin.setPassword("123");
            admin.setIsActive(true);
            userService.save(admin);
        }

        if (userService.findByUsername("seller").isEmpty()) {
            User seller = new User();
            seller.setFirstName("Stive");
            seller.setLastName("Jobs");
            seller.setRole(UserRole.SELLER);
            seller.setUsername("seller");
            seller.setPassword("123");
            seller.setIsActive(true);
            userService.save(seller);
        }


    }
}
