package com.example.jwtexample.dto;

import com.example.jwtexample.domain.Company;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CompanyInfoResponseDto {
    private Long id;
    private String name;
    private int foundingYear;

    public static CompanyInfoResponseDto from(Company company) {
        return CompanyInfoResponseDto.builder()
                .id(company.getId())
                .name(company.getName())
                .foundingYear(company.getFoundingYear())
                .build();
    }
}
