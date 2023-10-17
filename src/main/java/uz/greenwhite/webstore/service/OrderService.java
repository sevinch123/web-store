package uz.greenwhite.webstore.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.greenwhite.webstore.entity.Orders;
import uz.greenwhite.webstore.repository.OrderRepository;
import uz.greenwhite.webstore.enums.OrderStatus;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public List<Orders> getAllOrdersByStatus(OrderStatus status){
        return repository.findAllByStatus(status);
    }

    public List<Orders> getAllByStatusOrderByCreatedOnAsc(OrderStatus status) {
        return repository.findAllByStatusOrderByCreatedOnAsc(status);
    }

    public List<Orders> getAllByStatusOrderByCreatedOnDesc(OrderStatus status) {
        return repository.findAllByStatusOrderByCreatedOnDesc(status);
    }

    public Page<Orders> getAllByOrderByCreatedOnAsc(Pageable pageable) {
        return repository.findAllByOrderByCreatedOnAsc(pageable);
    }

    public Page<Orders> getAllByOrderByCreatedOnDesc(Pageable pageable) {
        return repository.findAllByOrderByCreatedOnDesc(pageable);
    }

    public Page<Orders> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Orders getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    public Orders save(Orders orders) {
        if(orders.getOrderId() != null)
            throw new RuntimeException("Id should be a null");
        return repository.save(orders);
    }

    public Orders update(Orders orders) {
        if(orders.getOrderId() == null)
            throw new RuntimeException("Id shouldn't be null");

        return repository.save(orders);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void delete(Orders orders) {
        deleteById(orders.getOrderId());
    }
}
