package com.upc.productsapi.reports.model.entity;

import com.upc.productsapi.reports.model.dto.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
@Table(name = "reports")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false)
    private Integer type;

    @Column(nullable = false)
    private Integer views = 0;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate reportDate = LocalDate.now(); // Inicializa con la fecha actual

    @Column(nullable = false)
    private LocalTime reportTime = LocalTime.now(); // Inicializa con la hora actual

    @Column(nullable = false)
    private String imagesBase64;

    @Enumerated(EnumType.STRING)
    public Type getTypeEnum() {

        if (type == 1) {
            return Type.ROBBERY;
        }
        else if (type == 2) {
            return Type.VEHICULAR_INCIDENT;
        }
        else if (type == 3) {
            return  Type.SHOOTING;
        }
        else if (type == 4) {
            return  Type.EMERGENCY;
        }
        else {
            return null;
        }
    }

    public void setTypeEnum(Type typeEnum) {

        this.type = (typeEnum == Type.ROBBERY) ? 1 : this.type;
        this.type = (typeEnum == Type.VEHICULAR_INCIDENT) ? 2 : this.type;
        this.type = (typeEnum == Type.SHOOTING) ? 3 : this.type;
        this.type = (typeEnum == Type.EMERGENCY) ? 4 : this.type;

    }

    public void setComplaintDate(LocalDate reportDate) {
        this.reportDate = reportDate != null ? reportDate : LocalDate.now();
    }

    public void setComplaintTime(LocalTime reportTime) {
        this.reportTime = reportTime != null ? reportTime : LocalTime.now();
    }

    public Long getId() {
        return this.reportId;
    }

    public void setId(long l) {

        this.reportId = l;

    }
}
