package uz.greenwhite.webstore.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.greenwhite.webstore.entity.User;
import uz.greenwhite.webstore.enums.UserRole;
import uz.greenwhite.webstore.service.SellerService;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/data/seller")
public class SellerController {

    private final SellerService service;

    @GetMapping()
    public String listPage(Model model, Pageable pageable) {
        model.addAttribute("seller", service.getAll(pageable));
        return "/admin/data/seller/list";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("seller", new User());
        model.addAttribute("ROLE_SELLER", UserRole.SELLER);
        model.addAttribute("ROLE_MODERATOR", UserRole.MODERATOR);
        return "/admin/data/seller/add";
    }

    @PostMapping("/add")
    public String addSeller(@ModelAttribute User seller) {
        service.save(seller);
        return "redirect:/admin/data/seller";
    }

    @GetMapping("/edit")
    public String editPage(Model model, @RequestParam("id") Long id) {
        model.addAttribute("seller", service.getById(id));
        return "/admin/data/seller/add";
    }

    @PostMapping("/edit")
    public String editSeller(@ModelAttribute User seller) {
        service.update(seller);
        return "redirect:/admin/data/seller";
    }

    @GetMapping("delete/{id}")
    public String deleteSeller(@PathVariable Long id) {
        try {
            service.deleteById(id);
        } catch (RuntimeException e) {
            User seller = service.getById(id);
            seller.setIsActive(false);
            service.update(seller);
        }
        return "redirect:/admin/data/seller";
    }
}
