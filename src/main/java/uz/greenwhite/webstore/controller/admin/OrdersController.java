package uz.greenwhite.webstore.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.greenwhite.webstore.entity.Orders;
import uz.greenwhite.webstore.enums.OrderStatus;
import uz.greenwhite.webstore.service.*;
import uz.greenwhite.webstore.util.CookieUtil;
import org.springframework.security.core.Authentication;
import uz.greenwhite.webstore.entity.User;
import uz.greenwhite.webstore.service.UserService;

import java.security.Principal;
import java.util.Optional;
import java.util.Objects;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/data/orders")
public class OrdersController {

    private final OrderService service;
    private final OrderItemService orderItemService;
    private final UserService userService;

    @GetMapping()
    public String listPage(@RequestParam(name = "status", required = false) OrderStatus orderStatus, Principal principal,@RequestParam(name = "filterOrders", required = false) Long filterOrders,Model model, Pageable pageable) {
    model.addAttribute("orderStatus",orderStatus);
    Optional<User> data = userService.findByUsername(principal.getName());
    model.addAttribute("name", data.isPresent() ? data.get().getFirstName() : "User");
        if(orderStatus==null){
        if(filterOrders==null)
         model.addAttribute("orders", service.getAll(pageable));
         else if(filterOrders==1) model.addAttribute("orders",service.getAllByOrderByCreatedOnAsc(pageable));
         else if(filterOrders==0) model.addAttribute("orders",service.getAllByOrderByCreatedOnDesc(pageable));
        }
        else{
        if(filterOrders==null) model.addAttribute("orders",service.getAllOrdersByStatus(orderStatus)); 
        else if(filterOrders==1) model.addAttribute("orders",service.getAllByStatusOrderByCreatedOnAsc(orderStatus));
        else if(filterOrders==0) model.addAttribute("orders",service.getAllByStatusOrderByCreatedOnDesc(orderStatus));
        }
        return "/admin/data/orders/list";
    }

    @GetMapping("/add")
    public String addPage(Model model, Principal principal) {
        model.addAttribute("orders", new Orders());
        return "/admin/data/orders/edit";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Orders orders, HttpServletRequest request) {
        service.saveNewOrder(orders, Objects.requireNonNull(CookieUtil.getCookie("session_token", request)).getValue());
        return "redirect:/admin/data/orders";
    }

    @GetMapping("/edit")
    public String editPage(Model model, @RequestParam("id") Long id, Principal principal) {
        Optional<User> data = userService.findByUsername(principal.getName());
        model.addAttribute("name", data.isPresent() ? data.get().getFirstName() : "User");
        model.addAttribute("orders", service.getById(id));
        model.addAttribute("orderItems",orderItemService.getAllByOrders(id,null));
        return "/admin/data/orders/edit";
    }

    @PostMapping("/edit")
    public String editOrder(@ModelAttribute Orders orders, Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElse(null);
        if(user != null) {
            orders.setModifiedBy(user);
            service.update(orders);
        }
        return "redirect:/admin/data/orders";
    }

    @GetMapping("delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
            service.deleteById(id);
        return "redirect:/admin/data/orders";
    }
}