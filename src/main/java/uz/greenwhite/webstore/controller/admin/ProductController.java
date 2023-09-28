package uz.greenwhite.webstore.controller.admin;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.greenwhite.webstore.entity.Category;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/data/product")
public class ProductController {


    @GetMapping()
    public String listPage(Model model, Pageable pageable) {
        return "/admin/data/product/list";
    }


    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("category", new Category());
        return "/admin/data/product/add";
    }

}