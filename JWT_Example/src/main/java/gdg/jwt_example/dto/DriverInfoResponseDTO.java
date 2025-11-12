package gdg.jwt_example.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DriverInfoResponseDTO {
    private Long id;
    private String serial;
    private String name;
    private String role;
}
