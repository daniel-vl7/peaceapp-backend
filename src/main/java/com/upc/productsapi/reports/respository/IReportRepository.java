package com.upc.productsapi.reports.respository;

import com.upc.productsapi.reports.model.dto.enums.Type;
import com.upc.productsapi.reports.model.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IReportRepository extends JpaRepository<Report, Long> {

    Optional<Report> findByReportId (long complaintsId);

    List<Report> findByDistrict(String district);

    List<Report> findByType (Integer type);

    List<Report> findByReportDate (LocalDate localDate);



}



