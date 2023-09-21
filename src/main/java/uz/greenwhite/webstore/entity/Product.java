package uz.greenwhite.webstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @JoinColumn(name = "category_id")
    private long categoryId;

    private String description;

    private double price;

    private int quantity;

    private int times_sold;

}
