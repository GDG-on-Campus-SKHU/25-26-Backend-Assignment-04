package gdg.jwt_example.service;

import gdg.jwt_example.domain.Driver;
import gdg.jwt_example.domain.Role;
import gdg.jwt_example.dto.DriverInfoResponseDTO;
import gdg.jwt_example.dto.DriverSignUpDTO;
import gdg.jwt_example.dto.TokenDTO;
import gdg.jwt_example.jwt.TokenProvider;
import gdg.jwt_example.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public TokenDTO signUp(DriverSignUpDTO driverSignUpDTO) {
        Driver driver = driverRepository.save(Driver.builder()
                .serial(driverSignUpDTO.getSerial())
                .password(passwordEncoder.encode(driverSignUpDTO.getPassword()))
                .name(driverSignUpDTO.getName())
                .role(Role.DRIVER)
                .build());

        String accessToken = tokenProvider.createAccessToken(driver);

        return TokenDTO.builder()
                .accessToken(accessToken)
                .build();
    }

    public DriverInfoResponseDTO findDriverByPrincipal(Principal principal) {
        Long driverId = Long.parseLong(principal.getName());

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 사용자입니다."));

        return DriverInfoResponseDTO.builder()
                .id(driver.getId())
                .serial(driver.getSerial())
                .name(driver.getName())
                .role(driver.getRole().name())
                .build();
    }
}
