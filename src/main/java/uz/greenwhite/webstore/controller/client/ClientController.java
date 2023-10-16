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
    public String shopController(@RequestParam(name = "id", required = false) Long categoryId,@RequestParam(name = "order", required = false) Long productOrder,
    @RequestParam(name = "from", required = false) Long productFrom,@RequestParam(name = "to", required = false) Long productTo,  Model model, Pageable pageable) {
        model.addAttribute("filterCategoryId", categoryId);
        model.addAttribute("filterFrom", productFrom);
        model.addAttribute("filterTo", productTo);
        if(categoryId != null) {
            if(productFrom!=null&&productTo==null&&productOrder==null)model.addAttribute("products",productService.getAllByCategoryAndPriceGreaterThan(categoryId,productFrom,null));
            else if(productFrom==null&&productTo!=null&&productOrder==null)model.addAttribute("products",productService.getAllByCategoryAndPriceLessThan(categoryId,productTo,null));
            else if(productFrom!=null&&productTo!=null&&productOrder==null)model.addAttribute("products",productService.getByCategoryAndPriceBetween(categoryId,productFrom,productTo,null));
            
            else if(productFrom!=null&&productTo==null&&productOrder==0)model.addAttribute("products",productService.getAllByCategoryAndPriceGreaterThanOrderByPriceDesc(categoryId,productFrom,null));
            else if(productFrom==null&&productTo!=null&&productOrder==0)model.addAttribute("products",productService.getAllByCategoryAndPriceLessThanOrderByPriceDesc(categoryId,productTo,null));
            else if(productFrom!=null&&productTo!=null&&productOrder==0)model.addAttribute("products",productService.getByCategoryAndPriceBetweenOrderByPriceDesc(categoryId,productFrom,productTo,null));
           
            else if(productFrom!=null&&productTo==null&&productOrder==1)model.addAttribute("products",productService.getAllByCategoryAndPriceGreaterThanOrderByPriceAsc(categoryId,productFrom,null));
            else if(productFrom==null&&productTo!=null&&productOrder==1)model.addAttribute("products",productService.getAllByCategoryAndPriceLessThanOrderByPriceAsc(categoryId,productTo,null));
            else if(productFrom!=null&&productTo!=null&&productOrder==1)model.addAttribute("products",productService.getByCategoryAndPriceBetweenOrderByPriceAsc(categoryId,productFrom,productTo,null));
           
            else if(productOrder==null)model.addAttribute("products", productService.getByCategory(categoryId,null));
            else if(productOrder==1)model.addAttribute("products",productService.getByCategoryOrderByPriceAsc(categoryId,null));
            else if(productOrder==0)model.addAttribute("products",productService.getByCategoryOrderByPriceDesc(categoryId, null));
        } else { 
            if(productFrom!=null&&productTo==null&&productOrder==null)model.addAttribute("products",productService.getAllByPriceGreaterThan(productFrom,null));
            else if(productFrom==null&&productTo!=null&&productOrder==null)model.addAttribute("products",productService.getAllByPriceLessThan(productTo,null));
            else if(productFrom!=null&&productTo!=null&&productOrder==null)model.addAttribute("products",productService.getByPriceBetween(productFrom,productTo,null));
           
            else if(productFrom!=null&&productTo==null&&productOrder==0)model.addAttribute("products",productService.getAllByPriceGreaterThanOrderByPriceDesc(productFrom,null));
            else if(productFrom==null&&productTo!=null&&productOrder==0)model.addAttribute("products",productService.getAllByPriceLessThanOrderByPriceDesc(productTo,null));
            else if(productFrom!=null&&productTo!=null&&productOrder==0)model.addAttribute("products",productService.getByPriceBetweenOrderByPriceDesc(productFrom,productTo,null));
           
            else if(productFrom!=null&&productTo==null&&productOrder==1)model.addAttribute("products",productService.getAllByPriceGreaterThanOrderByPriceAsc(productFrom,null));
            else if(productFrom==null&&productTo!=null&&productOrder==1)model.addAttribute("products",productService.getAllByPriceLessThanOrderByPriceAsc(productTo,null));
            else if(productFrom!=null&&productTo!=null&&productOrder==1)model.addAttribute("products",productService.getByPriceBetweenOrderByPriceAsc(productFrom,productTo,null));
           
            else if(productOrder==null) model.addAttribute("products", productService.getAll(pageable));
            else if(productOrder==1) model.addAttribute("products", productService.getAllByOrderByPriceAsc(pageable));
            else if(productOrder==0)model.addAttribute("products", productService.getAllByOrderByPriceDesc(pageable));
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