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

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "logo_path")
    private String logoPath;

    @Column(name = "phone_1")
    private String phone1;

    @Column(name = "phone_2")
    private String phone2;

    @Column(name = "email")
    private String email;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "telegramUrl")
    private String telegramUrl;

    @Column(name = "address", columnDefinition = "TEXT")
    private String address;

    @Column(name = "location_url")
    private String locationUrl;

}
