package com.upc.productsapi.reports.service;

import com.upc.productsapi.reports.respository.IReportRepository;
import com.upc.productsapi.shared.dto.enums.EStatus;
import com.upc.productsapi.shared.dto.response.ApiResponse;
import com.upc.productsapi.reports.model.dto.request.ReportRequestDto;
import com.upc.productsapi.reports.model.dto.response.ReportResponseDto;
import com.upc.productsapi.reports.model.entity.Report;
import com.upc.productsapi.shared.exception.ResourceNotFoundException;
import com.upc.productsapi.users.model.entity.User;
import com.upc.productsapi.users.repository.IUserRepository;
import com.upc.productsapi.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService implements IReportService {


    private final IReportRepository reportRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public ReportService(IReportRepository reportRepository, ModelMapper modelMapper, UserService userService) {
        this.reportRepository = reportRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public ApiResponse<ReportResponseDto> registerReport(ReportRequestDto streetData) {
        //dto a entity
        var report = modelMapper.map(streetData, Report.class);

        //crear el producto
        var complaintsSaved = reportRepository.save(report);

        //entity a dto
        var complaintsResponse = modelMapper.map(complaintsSaved, ReportResponseDto.class);

        //retornar la respuesta
        return new ApiResponse<>("Calle registrada correctamente", EStatus.SUCCESS, complaintsResponse);
    }

    @Override
    public ApiResponse<Object> deleteReportById(Long complaintsId) {
        //buscar el producto a eliminar
        var street = reportRepository.findById(complaintsId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con el id: " + complaintsId));

        //eliminar el producto
        reportRepository.delete(street);

        //retornar la respuesta
        return new ApiResponse<>("Producto eliminado correctamente", EStatus.SUCCESS, null);
    }

    @Override
    public ApiResponse<ReportResponseDto> getReportByReportId(long reportId) {
        //buscar el producto
        var report = reportRepository.findByReportId(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con el nombre: " + reportId));

        //entity a dto
        var reportResponse = modelMapper.map(report, ReportResponseDto.class);

        //retornar la respuesta
        return new ApiResponse<>("Ok", EStatus.SUCCESS, reportResponse);
    }

    @Override
    public ApiResponse<List<ReportResponseDto>> getAllReports() {

        var report = reportRepository.findAll();

        var reportResponse = report.stream()
                .map(reports -> modelMapper.map(reports, ReportResponseDto.class))
                .toList();

        return new ApiResponse<>("Ok", EStatus.SUCCESS, reportResponse);
    }
    @Override
    public ApiResponse<List<ReportResponseDto>> getReportByDistrict(String district) {
        List<Report> reports = reportRepository.findByDistrict(district);

        if (reports.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró ningún reporte en el distrito: " + district);
        }

        List<ReportResponseDto> reportResponse = reports.stream()
                .map(report -> modelMapper.map(report, ReportResponseDto.class))
                .toList();

        return new ApiResponse<>("Ok", EStatus.SUCCESS, reportResponse);
    }

    @Override
    public ApiResponse<List<ReportResponseDto>> getReportByType(Integer type) {

        List<Report> reports = reportRepository.findByType(type);

        if (reports.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró ningún reporte con : " + type);
        }

        List<ReportResponseDto> reportResponse = reports.stream()
                .map(report -> modelMapper.map(report, ReportResponseDto.class))
                .toList();

        return new ApiResponse<>("Ok", EStatus.SUCCESS, reportResponse);
    }

    @Override
    public ApiResponse<List<ReportResponseDto>> getReportByLocalDate(LocalDate date) {

        List<Report> reports = reportRepository.findByReportDate(date);

        if (reports.isEmpty()) {
            throw new ResourceNotFoundException("No se encontró ningún reporte con las fecha: " + date);
        }

        List<ReportResponseDto> reportResponse = reports.stream()
                .map(report -> modelMapper.map(report, ReportResponseDto.class))
                .toList();

        return new ApiResponse<>("Ok", EStatus.SUCCESS, reportResponse);
    }

    @Override
    public List<Report> getReportsByUsername(String username) {
        // Obtener el usuario por su nombre de usuario o ID
        User user = userService.findByUsername(username);

        // Obtener el distrito del usuario
        String userDistrict = user.getDistrict();

        // Buscar todos los reportes basados en el distrito del usuario
        return reportRepository.findByDistrict(userDistrict);
    }
}

