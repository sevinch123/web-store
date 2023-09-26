package uz.greenwhite.webstore.controller.admin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/category-list")
    public String categoryListController() {
        return "admin/data/product-category/list";
    }
    @GetMapping("/admin")
    public String adminController() {
        return "admin/admin";
    }

}
