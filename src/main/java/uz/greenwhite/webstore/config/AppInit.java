package uz.greenwhite.webstore.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.greenwhite.webstore.entity.Category;
import uz.greenwhite.webstore.entity.Product;
import uz.greenwhite.webstore.entity.CompanyDetails;
import uz.greenwhite.webstore.entity.User;
import uz.greenwhite.webstore.enums.UserRole;
import uz.greenwhite.webstore.service.CategoryService;
import uz.greenwhite.webstore.service.ProductService;
import uz.greenwhite.webstore.service.CompanyDetailsService;
import uz.greenwhite.webstore.service.UserService;

@Component
@AllArgsConstructor
public class AppInit implements ApplicationRunner {
    //
    private final UserService userService;
    private final CategoryService categoryService;

    private final ProductService productService;
    private final CompanyDetailsService companyDetailsService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Category category = new Category();
        category.setCategoryName("Texnika");
        categoryService.save(category);
        category.setCategoryId(null);
        category.setCategoryName("Mebel");
        category.setIsActive(false);
        categoryService.save(category);

        Product product = new Product();
        product.setPhoto("1.jpeg");
        product.setIsActive(true);
        product.setQuantity(100);
        product.setName("Samsung");
        product.setPrice(1000L);
        product.setDescription("Made in <b> North Korea )</b>");
        product.setCategory(category);
        productService.save(product);

        Product product2 = new Product();
        product2.setIsActive(true);
        product2.setQuantity(0);
        product2.setName("Webstore");
        product2.setPrice(5000L);
        product2.setDescription("Made in <i> Green White )</i>");
        product2.setCategory(category);
        productService.save(product2);

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
            seller.setPassword("1234");
            userService.update(seller);
        }

        CompanyDetails details = new CompanyDetails();
        details.setCompanyName("Chorsu bozor");
        details.setAddress("Chorsu");
        details.setPhone1("+99897788888");
        companyDetailsService.save(details);

    }
}
