package uz.greenwhite.webstore.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AppInit implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
