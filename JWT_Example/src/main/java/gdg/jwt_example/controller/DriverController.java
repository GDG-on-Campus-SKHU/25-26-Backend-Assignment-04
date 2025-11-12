package gdg.jwt_example.controller;

import gdg.jwt_example.dto.DriverInfoResponseDTO;
import gdg.jwt_example.dto.DriverSignUpDTO;
import gdg.jwt_example.dto.TokenDTO;
import gdg.jwt_example.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("driver")
public class DriverController {
    private final DriverService driverService;

    @PostMapping("signup")
    public ResponseEntity<TokenDTO> signUp(@RequestBody DriverSignUpDTO driverSignUpDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.signUp(driverSignUpDTO));
    }

    @GetMapping
    public ResponseEntity<DriverInfoResponseDTO> getDriverInfo(Principal principal) {
        return ResponseEntity.ok(driverService.findDriverByPrincipal(principal));
    }
}
