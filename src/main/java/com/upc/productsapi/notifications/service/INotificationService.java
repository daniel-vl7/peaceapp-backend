package com.upc.productsapi.notifications.service;


import com.upc.productsapi.notifications.model.entity.Notification;
import com.upc.productsapi.reports.model.entity.Report;
import com.upc.productsapi.shared.dto.response.ApiResponse;
import com.upc.productsapi.notifications.model.dto.request.NotificationRequestDto;
import com.upc.productsapi.notifications.model.dto.response.NotificationResponseDto;

import java.util.List;

public interface INotificationService {

    //ApiResponse<List<NotificationResponseDto>> getNotificationsByUserDistrict(Long userId);

    //ApiResponse<List<NotificationResponseDto>> getNotificationsByUser(Long userId);

    List<Notification> getNotificationsByUsername(String username);

    List<Notification> getNotificationsByDistrict(String district);

    ApiResponse<Object> deleteNotificationById(Long notificationId);

}
