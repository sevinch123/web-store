package uz.greenwhite.webstore.controller.client;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import uz.greenwhite.webstore.entity.Category;
import uz.greenwhite.webstore.service.CategoryService;
import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;

@Controller
public class ClientController {
    private final CategoryService service;

    public ClientController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model, Pageable pageable) {
        Page<Category> page = service.getAll(pageable);
        long elements = page.getTotalElements();
        model.addAttribute("categories", page);
        model.addAttribute("elements", elements);
        return "index";
    }
    @GetMapping("/cart")
    public String cartController() {
        return "cart";
    }
    @GetMapping("/contact")
    public String contactController() {
        return "contact";
    }
    @GetMapping("/detail")
    public String detailController() {
        return "detail";
    }
    @GetMapping("/shop")
    public String shopController() {
        return "shop";
    }
    @GetMapping("/checkout")
    public String checkoutController() {
        return "checkout";
    }

}