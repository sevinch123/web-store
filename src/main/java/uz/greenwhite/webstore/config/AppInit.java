package uz.greenwhite.webstore.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.greenwhite.webstore.entity.*;
import uz.greenwhite.webstore.enums.OrderStatus;
import uz.greenwhite.webstore.enums.UserRole;
import uz.greenwhite.webstore.service.*;
import com.github.javafaker.Faker;

@Component
@AllArgsConstructor
public class AppInit implements ApplicationRunner {
    //
    private final UserService userService;
    private final CategoryService categoryService;

    private final ProductService productService;
    private final CompanyDetailsService companyDetailsService;
    private final OrderService orderService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Category category1 = new Category();
        category1.setCategoryName("Mebel");
        category1.setIsActive(false);
        categoryService.save(category1);
        Faker faker =new Faker();

        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            category.setCategoryName(faker.book().genre());
            categoryService.save(category);
            
            for (int j = 0; j < 5; j++) {
                Product product = new Product();
                product.setName(faker.commerce().productName());
                product.setPrice((long)(faker.number().numberBetween(10000,1000000)));
                product.setCategory(category); 
                product.setPhoto("jpg");
                product.setQuantity( faker.number().randomDigit());
                product.setDescription(faker.lorem().paragraph());
                productService.save(product);
            }
        }
       
            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());    
            user.setRole(UserRole.SELLER);
            user.setUsername(faker.name().username());
            user.setPassword(faker.internet().password());
            userService.save(user);

        User admin = new User();
        if (userService.findByUsername("admin").isEmpty()) {
            admin.setFirstName(faker.name().firstName());
            admin.setLastName(faker.name().lastName());   
            admin.setRole(UserRole.MODERATOR);
            admin.setUsername("admin");
            admin.setPassword("123");
            admin.setIsActive(true);
            userService.save(admin);
        }

        if (userService.findByUsername("seller").isEmpty()) {
            User seller = new User();
            seller.setFirstName(faker.name().firstName());
            seller.setLastName(faker.name().lastName()); 
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

        for(int i=0;i<10;i++){
        Orders order = new Orders();
        order.setFirstName(faker.name().firstName());
        order.setLastName(faker.name().lastName());
        order.setPhoneNumber(faker.phoneNumber().cellPhone());

        OrderStatus[] statuses = OrderStatus.values();
        int randomIndex = faker.random().nextInt(statuses.length);
        OrderStatus randomStatus = statuses[randomIndex];
        order.setStatus(randomStatus);

        order.setModifiedBy(admin);
        orderService.save(order);
        }
        }
    }
