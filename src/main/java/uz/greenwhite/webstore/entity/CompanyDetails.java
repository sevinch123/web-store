package uz.greenwhite.webstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyDetailsId;

    private String companyName;

    private String logoPath;

    private String phone1;

    private String phone2;

    private String email;

    private String instagramUrl;

    private String telegramUrl;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String locationUrl;

}
