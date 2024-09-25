package com.upc.productsapi.reports.service;

import com.upc.productsapi.reports.model.entity.Report;
import com.upc.productsapi.shared.dto.response.ApiResponse;
import com.upc.productsapi.reports.model.dto.request.ReportRequestDto;
import com.upc.productsapi.reports.model.dto.response.ReportResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface IReportService {

    /**
     * Método que se encarga de registrar una denuncias
     * @param reportData datos de lq denuncias a registrar
     * @return ApiResponse con los datos de la denuncia registrada
     */
    ApiResponse<ReportResponseDto> registerReport(ReportRequestDto reportData);

    /**
     * Método que se encarga de eliminar una denuncias por su id
     */
    ApiResponse<Object> deleteReportById(Long reportId);

    /**
     * Método que se encarga de obtener una denuncias por su id
     * @return ApiResponse con los datos de la denuncia
     */
    ApiResponse<ReportResponseDto> getReportByReportId(long id);

    /**
     * Método que se encarga de obtener todos las denuncias
     * @return ApiResponse con la lista de denuncias
     */
    ApiResponse<List<ReportResponseDto>> getAllReports();
    /**
     * Método que se encarga de obtener todos las denuncias por distrito
     * @return ApiResponse con la lista de denuncias
     */


    ApiResponse<List<ReportResponseDto>> getReportByDistrict(String district);

    /**
     * Método que se encarga de obtener todos las denuncias por Tipo
     * @return ApiResponse con la lista de denuncias
     */

    ApiResponse<List<ReportResponseDto>> getReportByType(Integer type);

    /**
     * Método que se encarga de obtener todos las denuncias por dia
     * @return ApiResponse con la lista de denuncias
     */
    ApiResponse<List<ReportResponseDto>> getReportByLocalDate(LocalDate date);
    List<Report> getReportsByUsername(String username);


}
