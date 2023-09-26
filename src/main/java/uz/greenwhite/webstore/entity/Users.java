package uz.greenwhite.webstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.greenwhite.webstore.enums.UserRole;
import uz.greenwhite.webstore.enums.UserStatus;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;

    private String lastName;

    private String login;

    private String password;

    @Enumerated (EnumType.ORDINAL)
    private UserRole role;

    @Enumerated (EnumType.ORDINAL)
    private UserStatus status;
}
