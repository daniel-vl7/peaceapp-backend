package com.upc.productsapi.street.model.dto.response;

import com.upc.productsapi.street.model.dto.enums.RiskLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreetResponseDto {


    private Long streetId;
    private String name;
    private String district;
    /*private String serialNumber;*/
    private RiskLevel riskLevel;

}
