package uz.greenwhite.webstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.sql.Blob;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Long price;

    private String Photo;

    private Integer quantity;

    private String description;

}
