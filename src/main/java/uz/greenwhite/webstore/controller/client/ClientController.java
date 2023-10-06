package uz.greenwhite.webstore.controller.client;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import uz.greenwhite.webstore.entity.*;
import uz.greenwhite.webstore.service.*;
import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;

@Controller
public class ClientController {
    private final CategoryService categoryService;
    private final CompanyDetailsService detailsService;

    public ClientController(CategoryService categoryService,CompanyDetailsService detailsService) {
        this.categoryService = categoryService;
        this.detailsService=detailsService;
    }

    @GetMapping
    public String list(Model model, Pageable pageable) {
        Page<Category> page = categoryService.getAll(pageable);
        long elements = page.getTotalElements(); 
        model.addAttribute("categories", page);
        model.addAttribute("elements", elements);
        Page<CompanyDetails> detailsPage = detailsService.getAll(pageable);
        model.addAttribute("details", detailsPage);
        return "index";
    }
    @GetMapping("/cart")
    public String cartController(Model model, Pageable pageable) {
        Page<Category> page = categoryService.getAll(pageable);
        long elements = page.getTotalElements(); 
        model.addAttribute("categories", page);
        model.addAttribute("elements", elements);
        Page<CompanyDetails> detailsPage = detailsService.getAll(pageable);
        model.addAttribute("details", detailsPage);
        return "cart";
    }
    @GetMapping("/contact")
    public String contactController(Model model, Pageable pageable) {
        Page<Category> page = categoryService.getAll(pageable);
        long elements = page.getTotalElements(); 
        model.addAttribute("categories", page);
        model.addAttribute("elements", elements);
        Page<CompanyDetails> detailsPage = detailsService.getAll(pageable);
        model.addAttribute("details", detailsPage);
        return "contact";
    }
    @GetMapping("/detail")
    public String detailController(Model model, Pageable pageable) {
        Page<Category> page = categoryService.getAll(pageable);
        long elements = page.getTotalElements(); 
        model.addAttribute("categories", page);
        model.addAttribute("elements", elements);
        Page<CompanyDetails> detailsPage = detailsService.getAll(pageable);
        model.addAttribute("details", detailsPage);
        return "detail";
    }
    @GetMapping("/shop")
    public String shopController(Model model, Pageable pageable) {
        Page<Category> page = categoryService.getAll(pageable);
        long elements = page.getTotalElements(); 
        model.addAttribute("categories", page);
        model.addAttribute("elements", elements);
        Page<CompanyDetails> detailsPage = detailsService.getAll(pageable);
        model.addAttribute("details", detailsPage);
        return "shop";
    }
    @GetMapping("/checkout")
    public String checkoutController(Model model, Pageable pageable) {
        Page<Category> page = categoryService.getAll(pageable);
        long elements = page.getTotalElements(); 
        model.addAttribute("categories", page);
        model.addAttribute("elements", elements);
        Page<CompanyDetails> detailsPage = detailsService.getAll(pageable);
        model.addAttribute("details", detailsPage);
        return "checkout";
    }

}