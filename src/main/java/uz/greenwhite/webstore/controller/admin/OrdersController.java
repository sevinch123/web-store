package uz.greenwhite.webstore.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.greenwhite.webstore.entity.Orders;
import uz.greenwhite.webstore.enums.OrderStatus;
import uz.greenwhite.webstore.service.OrderService;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/data/orders")
public class OrdersController {

    private final OrderService service;

    @GetMapping()
    public String listPage(@RequestParam(name = "status", required = false) OrderStatus orderStatus,@RequestParam(name = "filterOrders", required = false) Long filterOrders,Model model, Pageable pageable) {
    model.addAttribute("orderStatus",orderStatus);
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
    public String addPage(Model model) {
        model.addAttribute("orders", new Orders());
        return "/admin/data/orders/add";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute Orders orders) {
        service.save(orders);
        return "redirect:/admin/data/orders";
    }

    @GetMapping("/edit")
    public String editPage(Model model, @RequestParam("id") Long id) {
        model.addAttribute("orders", service.getById(id));
        return "/admin/data/orders/add";
    }

    @PostMapping("/edit")
    public String editOrder(@ModelAttribute Orders orders) {
        service.update(orders);
        return "redirect:/admin/data/orders";
    }

    @GetMapping("delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
            service.deleteById(id);
        return "redirect:/admin/data/orders";
    }
}