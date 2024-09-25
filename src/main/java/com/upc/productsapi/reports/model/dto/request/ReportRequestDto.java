package com.upc.productsapi.reports.model.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequestDto {


    @NotBlank(message = "El titulo de la denuncia no puede estar vacía")
    private String title;

    @NotBlank(message = "La descripcion de la denuncia no puede estar vacía")
    private String description;

    @Min(value = 1, message = "El Tipo de denuncia tiene que tener al menos 1 numero (1:ROBO, 2:INCIDENETE VEHICULAR, 3:ARNA DE FUEGO, 4:EMERGENCIA")
    private Integer type;

    @NotBlank(message = "El distrito de la denuncia no puede estar vacía")
    private String district;



}
