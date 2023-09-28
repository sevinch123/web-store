package uz.greenwhite.webstore.controller.admin;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.greenwhite.webstore.entity.Category;
import uz.greenwhite.webstore.entity.Product;
import uz.greenwhite.webstore.service.CategoryService;
import uz.greenwhite.webstore.service.ProductService;


@Controller
@AllArgsConstructor
@RequestMapping("/admin/data/product")
public class ProductController {

private final ProductService service;
private final CategoryService categoryService;

    @GetMapping()
    public String listPage(Model model, Pageable pageable) {
        model.addAttribute("products", service.getAll(pageable));
        return "/admin/data/product/list";
    }


    @GetMapping("/add")
    public String addPage(Model model, Pageable pageable) {
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categoryService.getAll(pageable));
        return "/admin/data/product/add";
    }
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        if (product.getName() != null && !product.getName().isEmpty()) {
            service.save(product);
        }

        return "redirect:/admin/data/product";
    }

    @GetMapping("/edit")
    public String editPage(Model model, @RequestParam("id") Long id,Pageable pageable) {
        Product product = service.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("languages", categoryService.getAll(pageable));
        return "admin/data/product/add";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product product) {
        service.update(product);
        return "redirect:/admin/data/product";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        service.delete(id);
        return "redirect:/admin/data/product";
    }

}