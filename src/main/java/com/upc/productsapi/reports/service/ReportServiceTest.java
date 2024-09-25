package com.upc.productsapi.reports.service;


import com.upc.productsapi.reports.model.dto.request.ReportRequestDto;
import com.upc.productsapi.reports.model.dto.response.ReportResponseDto;
import com.upc.productsapi.reports.model.entity.Report;
import com.upc.productsapi.reports.respository.IReportRepository;
import com.upc.productsapi.shared.dto.enums.EStatus;
import com.upc.productsapi.shared.dto.response.ApiResponse;
import com.upc.productsapi.shared.exception.ResourceNotFoundException;
import com.upc.productsapi.users.model.entity.User;
import com.upc.productsapi.users.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ReportServiceTest {

    @Mock
    private IReportRepository reportRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterReport() {
        // Arrange: Configuramos los datos y los comportamientos esperados
        ReportRequestDto requestDto = new ReportRequestDto();
        requestDto.setTitle("New Report");

        Report report = new Report();
        report.setId(1L);

        when(modelMapper.map(requestDto, Report.class)).thenReturn(report);
        when(reportRepository.save(report)).thenReturn(report);

        ReportResponseDto responseDto = new ReportResponseDto();
        responseDto.setId(1L);
        when(modelMapper.map(report, ReportResponseDto.class)).thenReturn(responseDto);

        // Act: Llamamos al método que vamos a probar
        ApiResponse<ReportResponseDto> response = reportService.registerReport(requestDto);

        // Assert: Verificamos que los resultados sean los esperados
        assertEquals(EStatus.SUCCESS, response.getStatus());
        assertEquals("Calle registrada correctamente", response.getMessage());
        assertNotNull(response.getData());
        assertEquals(1L, response.getData().getId());

        verify(reportRepository, times(1)).save(report);
    }

    @Test
    public void testDeleteReportById() {
        // Arrange
        Long reportId = 1L;
        Report report = new Report();
        report.setId(reportId);

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));

        // Act
        ApiResponse<Object> response = reportService.deleteReportById(reportId);

        // Assert
        assertEquals(EStatus.SUCCESS, response.getStatus());
        assertEquals("Producto eliminado correctamente", response.getMessage());

        verify(reportRepository, times(1)).findById(reportId);
        verify(reportRepository, times(1)).delete(report);
    }

    @Test
    public void testDeleteReportById_NotFound() {
        // Arrange
        Long reportId = 1L;

        when(reportRepository.findById(reportId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> reportService.deleteReportById(reportId));
    }

    @Test
    public void testGetReportByReportId() {
        // Arrange
        long reportId = 1L;
        Report report = new Report();
        report.setId(reportId);

        when(reportRepository.findByReportId(reportId)).thenReturn(Optional.of(report));

        ReportResponseDto responseDto = new ReportResponseDto();
        responseDto.setId(reportId);
        when(modelMapper.map(report, ReportResponseDto.class)).thenReturn(responseDto);

        // Act
        ApiResponse<ReportResponseDto> response = reportService.getReportByReportId(reportId);

        // Assert
        assertEquals(EStatus.SUCCESS, response.getStatus());
        assertNotNull(response.getData());
        assertEquals(reportId, response.getData().getId());

        verify(reportRepository, times(1)).findByReportId(reportId);
    }

    @Test
    public void testGetReportByReportId_NotFound() {
        // Arrange
        long reportId = 1L;

        when(reportRepository.findByReportId(reportId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> reportService.getReportByReportId(reportId));
    }

    @Test
    public void testGetAllReports() {
        // Arrange
        List<Report> reports = List.of(new Report(), new Report());
        when(reportRepository.findAll()).thenReturn(reports);

        List<ReportResponseDto> reportResponseDtos = reports.stream()
                .map(r -> new ReportResponseDto())
                .toList();
        when(modelMapper.map(any(Report.class), eq(ReportResponseDto.class))).thenReturn(new ReportResponseDto());

        // Act
        ApiResponse<List<ReportResponseDto>> response = reportService.getAllReports();

        // Assert
        assertEquals(EStatus.SUCCESS, response.getStatus());
        assertEquals(2, response.getData().size());

        verify(reportRepository, times(1)).findAll();
    }

    @Test
    public void testGetReportByDistrict_NotFound() {
        // Arrange
        String district = "UnknownDistrict";

        when(reportRepository.findByDistrict(district)).thenReturn(List.of());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> reportService.getReportByDistrict(district));
    }

    @Test
    public void testGetReportsByUsername() {
        // Arrange
        String username = "john_doe";
        User user = new User();
        user.setUsername(username);
        user.setDistrict("District1");

        when(userService.findByUsername(username)).thenReturn(user);
        when(reportRepository.findByDistrict("District1")).thenReturn(List.of(new Report()));

        // Act
        List<Report> reports = reportService.getReportsByUsername(username);

        // Assert
        assertFalse(reports.isEmpty());
        verify(userService, times(1)).findByUsername(username);
        verify(reportRepository, times(1)).findByDistrict("District1");
    }
}