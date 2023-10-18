package uz.greenwhite.webstore.controller.client;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.greenwhite.webstore.entity.*;
import uz.greenwhite.webstore.service.*;
import org.springframework.ui.Model;
import org.springframework.data.domain.Pageable;
import uz.greenwhite.webstore.util.CookieUtil;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Controller
@AllArgsConstructor
public class ClientController {
    private final CategoryService categoryService;

    private final CompanyDetailsService detailsService;

    private final ProductService productService;

    private final CartService cartService;

    private final PasswordEncoder encoder;

    private final OrderService orderService;

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

    // list
    @GetMapping("/cart")
    public String cartController(@ModelAttribute(name = "carts") ArrayList<Cart> carts, Model model, Pageable pageable,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        Page<Category> page = categoryService.getAll(pageable);
        long elements = page.getTotalElements();
        model.addAttribute("categories", page);
        model.addAttribute("elements", elements);
        if(carts != null) {
            for(Cart cart: carts) {
                cartService.update(cart);
            }
        }
        model.addAttribute("carts", cartService.getAllByToken(getAndSetToken(request, response)));



        String tokenName = "session_token";
        String tokenValue = null;
        boolean session = false;

        if (request.getCookies() != null) {
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

        model.addAttribute("carts", cartService.getAllByToken(tokenValue));
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

        return "redirect:/product/" + productService.getById(productId).getName() + "-" + productId;
    }


    //delete
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

    @GetMapping("/product/{productName}-{productId}")
    public String detailController(@PathVariable String productName, @PathVariable Long productId, Model model, Pageable pageable) {
        Page<Category> page = categoryService.getAll(pageable);
        //long elements = page.getTotalElements();
        Product productById = productService.getById(productId);
        if (productById == null) {
            return "error";
        }
        //model.addAttribute("elements", elements);
        model.addAttribute("product", productById);
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
        model.addAttribute("order", new Orders());
        model.addAttribute("categories", page);
        model.addAttribute("elements", elements);
        Page<CompanyDetails> detailsPage = detailsService.getAll(pageable);
        model.addAttribute("details", detailsPage);
        return "checkout";
    }

    @PostMapping("/checkout")
    public String placeOrder(@ModelAttribute(name = "order") Orders order,  HttpServletRequest request) {
        orderService.saveNewOrder(order, Objects.requireNonNull(CookieUtil.getCookie("session_token", request)).getValue());

        return "redirect:/shop";
    }


    public String getAndSetToken(HttpServletRequest request, HttpServletResponse response) {
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

        return tokenValue;
    }

}