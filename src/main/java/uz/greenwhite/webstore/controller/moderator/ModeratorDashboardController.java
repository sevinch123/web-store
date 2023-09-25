package uz.greenwhite.webstore.controller.moderator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("moderator")
public class ModeratorDashboardController {

    @GetMapping()
    public String dashboard() {
        return "moderator/dashboard";
    }
}