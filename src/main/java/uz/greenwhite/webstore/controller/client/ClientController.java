package uz.greenwhite.webstore.controller.client;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import uz.greenwhite.webstore.entity.*;
import uz.greenwhite.webstore.service.*;
import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class ClientController {
    private final CategoryService categoryService;

    private final CompanyDetailsService detailsService;

    private final ProductService productService;

    private final CartService cartService;

    private final PasswordEncoder encoder;

    @GetMapping
    public String list(Model model, Pageable pageable) {
        Page<Product> productPage = productService.getAll(pageable);
        model.addAttribute("products", productPage);
        Page<Category> page = categoryService.getAll(pageable);
        long elements = page.getTotalElements();
        model.addAttribute("categories", page);
        model.addAttribute("elements", elements);
        Page<CompanyDetails> detailsPage = detailsService.getAll(pageable);
        model.addAttribute("details", detailsPage);
        return "index";
    }

    @GetMapping("/cart")
    public String cartController(Model model, Pageable pageable,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        Page<Category> page = categoryService.getAll(pageable);
        long elements = page.getTotalElements();
        model.addAttribute("categories", page);
        model.addAttribute("elements", elements);


        String tokenName = "session_token";
        String tokenValue = null;
        boolean session = false;

        if(request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(tokenName)) {
                    tokenValue = cookie.getValue();
                    session = true;
                    break;
                }
            }
        }

        if (!session) {
            tokenValue = encoder.encode(String.valueOf(Date.from(Instant.now()).getTime()));
            response.addCookie(new Cookie(tokenName, tokenValue));
        }

        model.addAttribute("cart", cartService.getAllByToken(tokenValue));
        Page<CompanyDetails> detailsPage = detailsService.getAll(pageable);
        model.addAttribute("details", detailsPage);
        return "/cart";
    }

    @GetMapping("/cart/{productId}")
    public String addProduct(@PathVariable Long productId,
                             HttpServletRequest request,
                             HttpServletResponse response) {

        Cart cart = new Cart();
        cart.setToken(getAndSetToken(request, response));
        cart.setProduct(productService.getById(productId));
        cartService.save(cart);

        return "redirect:/cart";
    }

    @GetMapping("/cart/update")
    public String updateCart(@ModelAttribute List<Cart> cart, Model model) {
        for(Cart c: cart) {
            cartService.update(c);
        }
        model.addAttribute(cart);
        return "redirect:/cart";
    }

    @GetMapping("/cart/delete/{id}")
    public String delete(@PathVariable Long id) {
        cartService.deleteById(id);

        return "redirect:/cart";
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
    public String shopController(@RequestParam(name = "id", required = false) Long categoryId,  Model model, Pageable pageable) {
        if(categoryId != null) {
            model.addAttribute("products", productService.getByCategory(categoryId, null));
        } else {
            model.addAttribute("products", productService.getAll(pageable));

        }
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

    public String getAndSetToken(HttpServletRequest request, HttpServletResponse response) {
        String tokenName = "session_token";
        String tokenValue = null;
        boolean session = false;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(tokenName)) {
                tokenValue = cookie.getValue();
                session = true;
                break;
            }
        }

        if (!session) {
            tokenValue = encoder.encode(String.valueOf(Date.from(Instant.now()).getTime()));
            response.addCookie(new Cookie(tokenName, tokenValue));
        }

        return tokenValue;
    }

}