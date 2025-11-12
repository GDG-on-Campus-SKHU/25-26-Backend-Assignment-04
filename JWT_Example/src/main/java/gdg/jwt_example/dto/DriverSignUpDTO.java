package gdg.jwt_example.dto;

import lombok.Getter;

@Getter
public class DriverSignUpDTO {
    private String serial;
    private String password;
    private String name;
}
