package com.upc.productsapi.street.model.entity;

import com.upc.productsapi.street.model.dto.enums.RiskLevel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "street")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Street {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long streetId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String district;

    /*@Column(nullable = false)
    private String serialNumber;*/

    @Column(nullable = false)
    private Integer riskLevel;

    @Enumerated(EnumType.STRING)
    public RiskLevel getRiskLevelEnum() {
        if (riskLevel < 5) {
            return RiskLevel.LOW_RISK;
        }
        else if (riskLevel < 8) {
            return RiskLevel.MODERATE_RISK;
        }
        else if (riskLevel < 11) {
            return  RiskLevel.HIGH_RISK;
        }
        else {
            return null;
        }
    }

    public void setRiskLevelEnum(RiskLevel riskLevelEnum) {

        this.riskLevel = (riskLevelEnum == RiskLevel.LOW_RISK) ? 1 : this.riskLevel;
        this.riskLevel = (riskLevelEnum == RiskLevel.MODERATE_RISK) ? 2 : this.riskLevel;
        this.riskLevel = (riskLevelEnum == RiskLevel.HIGH_RISK) ? 3 : this.riskLevel;


    }

}
