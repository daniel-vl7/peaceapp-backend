package com.upc.productsapi.reports.model.dto.response;

import com.upc.productsapi.reports.model.dto.enums.Type;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseDto {


    private Long reportId;
    private String description;
    private Type type;
    private String district;
    private Integer views;
    private String title;
    private LocalDate reportDate;
    private LocalTime reportTime;
    private String imagesBase64;

    public void setId(long l) {
        this.reportId = l;
    }

    public long getId() {
        return reportId;
    }
}
