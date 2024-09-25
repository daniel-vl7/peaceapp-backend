package com.upc.productsapi.street.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StreetRequestDto {

    @NotBlank(message = "La calle del denunciada no puede estar vacía")
    private String name;

    @NotBlank(message = "El distrito de la calle no puede estar vacía")
    private String district;

    /*@NotBlank(message = "no puede estar vacío")
    private String district;*/

    @Min(value = 1, message = "El nivel de riesgo debe ser al menos 1 (Bajo Riesgo)")
    private Integer riskLevel;


}
