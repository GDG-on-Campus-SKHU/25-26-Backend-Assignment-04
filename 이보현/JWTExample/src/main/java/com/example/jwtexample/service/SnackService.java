package com.example.jwtexample.service;

import com.example.jwtexample.domain.Company;
import com.example.jwtexample.domain.Snack;
import com.example.jwtexample.dto.SnackInfoResponseDto;
import com.example.jwtexample.dto.SnackSaveRequestDto;
import com.example.jwtexample.repository.CompanyRepository;
import com.example.jwtexample.repository.ReviewRepository;
import com.example.jwtexample.repository.SnackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SnackService {

    private final SnackRepository snackRepository;
    private final CompanyRepository companyRepository;
    private final ReviewRepository reviewRepository;
    @Transactional
    public SnackInfoResponseDto saveSnack(SnackSaveRequestDto dto) {
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        Snack snack = Snack.builder()
                .name(dto.getName())
                .releaseYear(dto.getReleaseYear())
                .company(company)
                .build();

        snackRepository.save(snack);
        return SnackInfoResponseDto.from(snack);
    }

    @Transactional
    public SnackInfoResponseDto getSnack(Long snackId) {
        Snack snack = snackRepository.findById(snackId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과자입니다."));

        Double avg = reviewRepository.getAverageScoreBySnack(snackId);
        if (avg == null) avg = 0.0;
        double roundedAvg = Math.round(avg * 10) / 10.0;
        return SnackInfoResponseDto.from(snack, roundedAvg);
    }


    @Transactional
    public SnackInfoResponseDto updateSnack(Long snackId, SnackSaveRequestDto dto) {
        Snack snack = snackRepository.findById(snackId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 과자입니다."));

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));

        snack.update(dto.getName(), dto.getReleaseYear(), company);
        return SnackInfoResponseDto.from(snack);

    }

    @Transactional
    public void deleteSnack(Long snackId) {
        snackRepository.deleteById(snackId);

    }

    @Transactional
    public List<SnackInfoResponseDto> getAllSnacks() {
        return snackRepository.findAll().stream()
                .map(snack -> {
                    Double avg = reviewRepository.getAverageScoreBySnack(snack.getId());
                    if (avg == null) avg = 0.0;
                    double roundedAvg = Math.round(avg * 10) / 10.0;
                    return SnackInfoResponseDto.from(snack, roundedAvg);
                })
                .toList();
    }

}

