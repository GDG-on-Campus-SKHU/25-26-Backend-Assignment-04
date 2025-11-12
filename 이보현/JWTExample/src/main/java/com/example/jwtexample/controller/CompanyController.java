package com.example.jwtexample.controller;

import com.example.jwtexample.dto.CompanyInfoResponseDto;
import com.example.jwtexample.dto.CompanySaveRequestDto;
import com.example.jwtexample.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyInfoResponseDto> saveCompany(@RequestBody CompanySaveRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(companyService.saveCompany(dto));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyInfoResponseDto> getCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(companyService.getCompany(companyId));
    }

    @GetMapping
    public ResponseEntity<List<CompanyInfoResponseDto>> getAllCompanies() {
        List<CompanyInfoResponseDto> companyList = companyService.getAllCompanies();
        return ResponseEntity.ok(companyList);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long companyId) {
        companyService.deleteCompany(companyId);
        return ResponseEntity.noContent().build();
    }
}