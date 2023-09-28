package uz.greenwhite.webstore.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.greenwhite.webstore.entity.CompanyDetails;
import uz.greenwhite.webstore.service.CompanyDetailsService;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/data/companyDetails")
public class CompanyDetailsController {
    
    private final CompanyDetailsService service;

    @GetMapping()
    public String listPage(Model model, Pageable pageable) {
        model.addAttribute("list", service.getAll(pageable));
        return "admin/data/companyDetails/list";
    }
    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("companyDetails", new CompanyDetails());
        return "/admin/data/companyDetails/add";
    }

    @PostMapping("/add")
    public String addCompanyDetails(@ModelAttribute CompanyDetails details) {
        service.save(details);
        return "redirect:/admin/data/companyDetails";
    }

    @GetMapping("/edit")
    public String editPage(Model model, @RequestParam("id") Long id) {
        model.addAttribute("companyDetails", service.getById(id));
        return "admin/data/companyDetails/add";
    }

    @PostMapping("/edit")
    public String editCompanyDetails(@ModelAttribute CompanyDetails details) {
        service.update(details);
        return "redirect:admin/data/companyDetails";
    }
}