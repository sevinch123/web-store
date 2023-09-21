package uz.greenwhite.webstore.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "company_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_details_id", nullable = false)
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
