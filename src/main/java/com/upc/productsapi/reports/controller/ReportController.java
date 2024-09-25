package com.upc.productsapi.reports.controller;

import com.upc.productsapi.shared.dto.response.ApiResponse;
import com.upc.productsapi.reports.model.dto.request.ReportRequestDto;
import com.upc.productsapi.reports.model.dto.response.ReportResponseDto;
import com.upc.productsapi.reports.service.IReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

@Tag(name = "Reports")
@RestController
@RequestMapping("/api/v1/report")
public class ReportController {


    private final IReportService service;

    public ReportController(IReportService service) {
        this.service = service;
    }

    /**
     * Obtiene todos las denuncias
     * @return Lista de las denuncias
     */
    @Operation(summary = "Obtiene todas las denuncias")
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<ReportResponseDto>>> listReport() {
        var res = service.getAllReports();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una denuncia por su id")
    @GetMapping("/{reportId}")
    public ResponseEntity<ApiResponse<ReportResponseDto>> getReportByName(@PathVariable long reportId){
        var res = service.getReportByReportId(reportId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Registra una nueva denuncia
     * @param report Denuncia a registrar
     * @return Denuncia registrada
     */

    @Operation(summary = "Registra una nueva reporte")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<ReportResponseDto>> registerReport(@RequestBody ReportRequestDto report) {
        var res = service.registerReport(report);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/district/{district}")
    public ResponseEntity<ApiResponse<List<ReportResponseDto>>> getReportByDistrict(@PathVariable String district) {
        var res = service.getReportByDistrict(district);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene denuncias por tipo")
    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse<List<ReportResponseDto>>> getReportByType(@PathVariable Integer type) {
        ApiResponse<List<ReportResponseDto>> res = service.getReportByType(type);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene denuncias por distrito")
    @GetMapping("/date/{date:\\d{4}-\\d{2}-\\d{2}}")
    public ResponseEntity<ApiResponse<List<ReportResponseDto>>> getComplaintsByLocalDate(@PathVariable LocalDate date) {
        ApiResponse<List<ReportResponseDto>> res = service.getReportByLocalDate(date);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     *
     * Elimina una denuncia por su ID
     * @param reportId ID de la denuncia
     * @return Respuesta
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')") //solo los administradores pueden acceder a este endpoint
    @Operation(summary = "Elimina una denuncias por su ID (ADMIN)")
    @DeleteMapping("/delete/{reportId}")
    public ResponseEntity<ApiResponse<Object>> deleteReport(@PathVariable("reportId") Long reportId) {
        var res = service.deleteReportById(reportId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
