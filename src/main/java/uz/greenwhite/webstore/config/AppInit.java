package uz.greenwhite.webstore.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.greenwhite.webstore.entity.Users;
import uz.greenwhite.webstore.enums.UserRole;
import uz.greenwhite.webstore.repository.UserRepository;

@Component
@AllArgsConstructor
public class AppInit implements ApplicationRunner {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
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
