package uz.greenwhite.webstore.config;

import lombok.AllArgsConstructor;

import java.io.File;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.greenwhite.webstore.entity.CompanyDetails;
import uz.greenwhite.webstore.entity.Users;
import uz.greenwhite.webstore.enums.UserRole;
import uz.greenwhite.webstore.repository.CompanyDetailsRepository;
import uz.greenwhite.webstore.entity.Category;
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

    private final CompanyDetailsRepository companyRepository;
    private final CategoryService categoryService;

    @Override
    public void run(ApplicationArguments args) throws Exception { 
        CompanyDetails details = new CompanyDetails();
        details.setCompanyName("Anor");
        details.setAddress("Komolon");
        details.setEmail("anor@gmail.com");
        details.setInstagramUrl("https://www.w3schools.com/");
        details.setTelegramUrl("https://www.w3schools.com/");
        details.setLocationUrl("https://www.google.com/maps/place/%D0%A2%D0%B0%D1%88%D0%BA%D0%B5%D0%BD%D1%82/@41.2825974,69.2793667,11z/data=!3m1!4b1!4m6!3m5!1s0x38ae8b0cc379e9c3:0xa5a9323b4aa5cb98!8m2!3d41.2994958!4d69.2400734!16zL20vMGZzbXk?entry=ttu");
        details.setPhone1("+998 99 128-02-121");
        details.setPhone2("+998 97 251-54-254");
        companyRepository.save(details);
        
        Category category = new Category();
        category.setCategoryName("Texnika");
        categoryService.save(category);
        category.setCategoryId(null);
        category.setCategoryName("Mebel");
        category.setIsActive(false);
        categoryService.save(category);
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
