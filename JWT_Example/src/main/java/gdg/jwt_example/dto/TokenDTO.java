package gdg.jwt_example.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TokenDTO {
    private String accessToken;
}
