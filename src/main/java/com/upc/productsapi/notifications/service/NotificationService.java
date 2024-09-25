package com.upc.productsapi.notifications.service;

import com.upc.productsapi.notifications.repository.INotificationRepository;
import com.upc.productsapi.shared.dto.enums.EStatus;
import com.upc.productsapi.shared.dto.response.ApiResponse;
import com.upc.productsapi.notifications.model.entity.Notification;
import com.upc.productsapi.shared.exception.ResourceNotFoundException;
import com.upc.productsapi.users.model.entity.User;
import com.upc.productsapi.users.repository.IUserRepository;
import com.upc.productsapi.users.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService implements INotificationService {


    private final INotificationRepository notificationRepository;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    public NotificationService(INotificationRepository notificationRepository, IUserRepository userRepository, ModelMapper modelMapper) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Notification> getNotificationsByUsername(String username) {
        // Obtener el usuario por su nombre de usuario
        User user = userService.findByUsername(username);

        // Obtener el distrito del usuario
        String userDistrict = user.getDistrict();

        // Buscar todas las notificaciones basadas en el distrito del usuario
        return notificationRepository.findAllByDistrict(userDistrict);
    }
    @Override
    public List<Notification> getNotificationsByDistrict(String district) {
        return notificationRepository.findAllByDistrict(district);
    }

    @Override
    public ApiResponse<Object> deleteNotificationById(Long notificationId) {
        //buscar el producto a eliminar
        var notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con el id: " + notificationId));

        //eliminar el producto
        notificationRepository.delete(notification);

        //retornar la respuesta
        return new ApiResponse<>("Producto eliminado correctamente", EStatus.SUCCESS, null);
    }


}
