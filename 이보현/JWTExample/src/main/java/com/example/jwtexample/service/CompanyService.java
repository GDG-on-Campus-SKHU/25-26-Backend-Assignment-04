package com.example.jwtexample.service;


import com.example.jwtexample.domain.Company;
import com.example.jwtexample.dto.CompanyInfoResponseDto;
import com.example.jwtexample.dto.CompanySaveRequestDto;
import com.example.jwtexample.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public CompanyInfoResponseDto saveCompany(CompanySaveRequestDto dto) {
        Company company = Company.builder()
                .name(dto.getName())
                .foundingYear(dto.getFoundingYear())
                .build();

        companyRepository.save(company);
        return CompanyInfoResponseDto.from(company);
    }

    @Transactional
    public void deleteCompany(Long companyId) {
        companyRepository.deleteById(companyId);
    }

    @Transactional
    public CompanyInfoResponseDto getCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));
        return CompanyInfoResponseDto.from(company);
    }

    @Transactional
    public List<CompanyInfoResponseDto> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(CompanyInfoResponseDto::from)
                .toList();
    }
}
