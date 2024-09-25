package com.upc.productsapi.notifications.model.dto.response;

import com.upc.productsapi.notifications.model.dto.enums.Leakage;
import com.upc.productsapi.notifications.model.entity.Notification;
import com.upc.productsapi.reports.model.entity.Report;
import com.upc.productsapi.users.model.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDto {

    private Long id;
    private String title;
    private Boolean isRead;
    private Long reportId;
    private Long userId;

    // Constructor que acepta un título
    public NotificationResponseDto(String title) {
        this.title = title;
    }

    // Getters y setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
