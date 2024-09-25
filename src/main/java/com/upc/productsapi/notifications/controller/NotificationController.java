package com.upc.productsapi.notifications.controller;

import com.upc.productsapi.notifications.model.dto.response.NotificationResponseDto;
import com.upc.productsapi.notifications.model.entity.Notification;
import com.upc.productsapi.notifications.service.NotificationService;
import com.upc.productsapi.reports.model.entity.Report;
import com.upc.productsapi.reports.service.ReportService;
import com.upc.productsapi.security.jwt.provider.JwtTokenProvider;
import com.upc.productsapi.shared.dto.enums.EStatus;
import com.upc.productsapi.shared.dto.response.ApiResponse;
import com.upc.productsapi.users.model.entity.User;
import com.upc.productsapi.users.repository.IUserRepository;
import com.upc.productsapi.users.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@Tag(name = "Notification")
@RestController
@RequestMapping("/api/notifications")
@SecurityRequirement(name = "JwtScheme")
public class NotificationController {

    private final NotificationService notificationService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final ReportService reportService;

    public NotificationController(NotificationService notificationService, UserService userService, JwtTokenProvider jwtTokenProvider, ReportService reportService) {
        this.notificationService = notificationService;
        this.userService = userService;
        this.reportService = reportService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private IUserRepository userRepository;

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<ApiResponse<Object>> deleteNotificationById(@PathVariable Long notificationId) {
        ApiResponse<Object> response = notificationService.deleteNotificationById(notificationId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @SecurityRequirement(name = "JwtScheme")
    @GetMapping("/current")
    public ResponseEntity<List<Notification>> getNotifications() {
        // Obtener el nombre de usuario desde el contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // El nombre de usuario debería estar aquí

        // Obtener las notificaciones basadas en el nombre de usuario
        List<Notification> notifications = notificationService.getNotificationsByUsername(username);

        // Verificar si hay notificaciones
        if (notifications.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(notifications);
    }

}
