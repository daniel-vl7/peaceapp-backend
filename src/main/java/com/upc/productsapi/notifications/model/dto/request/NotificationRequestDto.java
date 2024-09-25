package com.upc.productsapi.notifications.model.dto.request;


import com.upc.productsapi.reports.model.entity.Report;
import com.upc.productsapi.users.model.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.validation.constraints.Min;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDto {
    @NotBlank(message = "El snapshotId no puede estar vacío")
    private Long notificationId;

    @NotBlank(message = "El message no puede estar vacío")
    private String message;

    @NotNull(message = "La si se leyo no puede ser nula")
    private Boolean is_read;



}
