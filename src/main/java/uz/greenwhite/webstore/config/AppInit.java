package uz.greenwhite.webstore.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.greenwhite.webstore.entity.Category;
import uz.greenwhite.webstore.entity.CompanyDetails;
import uz.greenwhite.webstore.entity.User;
import uz.greenwhite.webstore.enums.UserRole;
import uz.greenwhite.webstore.repository.CompanyDetailsRepository;
import uz.greenwhite.webstore.repository.UserRepository;
import uz.greenwhite.webstore.service.CategoryService;
import uz.greenwhite.webstore.service.CompanyDetailsService;

@Component
@AllArgsConstructor
public class AppInit implements ApplicationRunner {
    //
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final CategoryService categoryService;
    private final CompanyDetailsService detailsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CompanyDetails details=new CompanyDetails();
        details.setAddress("Komolon");
        details.setCompanyName("Anor");
        details.setEmail("anor@gmail.com");
        details.setInstagramUrl("https://www.w3schools.com/");
        details.setLocationUrl("https://www.w3schools.com/");
        details.setTelegramUrl("https://www.w3schools.com/");
        details.setPhone1("99 251-51-01");
        details.setPhone2("90 120-51-01");
        detailsService.save(details);

        Category category = new Category();
        category.setCategoryName("Texnika");
        categoryService.save(category);
        category.setCategoryId(null);
        category.setCategoryName("Mebel");
        category.setIsActive(false);
        categoryService.save(category);

       if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setFirstName("Ilon");
            admin.setFirstName("Mask");
            admin.setRole(UserRole.MODERATOR);
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("123"));
            admin.setIsActive(true);
            userRepository.save(admin);
        }

        if (userRepository.findByUsername("seller").isEmpty()) {
            User seller = new User();
            seller.setFirstName("Stive");
            seller.setFirstName("Jobs");
            seller.setRole(UserRole.SELLER);
            seller.setUsername("seller");
            seller.setPassword(encoder.encode("123"));
            seller.setIsActive(true);
            userRepository.save(seller);
        }


    }
}