package com.upc.productsapi.notifications.repository;

import com.upc.productsapi.notifications.model.dto.response.NotificationResponseDto;
import com.upc.productsapi.notifications.model.entity.Notification;
import com.upc.productsapi.shared.dto.response.ApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface INotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserId(Long userId);

    List<Notification> findAllByDistrict(String district);


}
