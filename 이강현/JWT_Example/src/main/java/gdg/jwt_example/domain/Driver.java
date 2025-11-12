package gdg.jwt_example.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DRIVER_SERIAL", nullable = false, unique = true)
    private String serial;

    @Column(name = "DRIVER_PASSWORD", nullable = false)
    private String password;

    @Column(name = "DRIVER_NAME", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "DRIVER_ROLE", nullable = false)
    private Role role;

    @Builder
    public Driver(String name, String serial, String password, Role role) {
        this.name = name;
        this.serial = serial;
        this.password = password;
        this.role = role;
    }
}
