package com.gdg.jwtexample.domain;

import com.gdg.jwtexample.dto.UserDto.UserRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BOOKER_NAME", nullable = false)
    private String name;

    @Column(name = "BOOKER_TEL", nullable = false, unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "BOOKER_ROLE", nullable = false)
    private Authority role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Booking> bookings = new ArrayList<>();

    @Builder
    public User(String name, String phone, Authority role) {
        this.name = name;
        this.phone = phone;
        this.role = role;
    }

    public void update(UserRequestDto userRequestDto, Authority role) {
        this.name = userRequestDto.getName();
        this.phone = userRequestDto.getPhone();
        this.role = role;
    }

    public void updateRole(Authority role) {
        this.role = role;
    }
}
