package com.upc.productsapi.street.service;

import com.upc.productsapi.street.model.dto.request.StreetRequestDto;
import com.upc.productsapi.street.model.dto.response.StreetResponseDto;
import com.upc.productsapi.shared.dto.response.ApiResponse;

import java.util.List;

public interface IStreetService {

    /**
     * Método que se encarga de registrar un producto
     * @param streetData datos del producto a registrar
     * @return ApiResponse con los datos del producto registrado
     */
    ApiResponse<StreetResponseDto> registerStreet(StreetRequestDto streetData);

    /**
     * Método que se encarga de eliminar un producto por su id
     */
    ApiResponse<Object> deleteStreetById(Long StreetId);

    /**
     * Método que se encarga de obtener un producto por su nombre
     * @return ApiResponse con los datos del producto
     */
    ApiResponse<StreetResponseDto> getStreetByStreetId(long id);

    /**
     * Método que se encarga de obtener todos los productos
     * @return ApiResponse con la lista de productos
     */
    ApiResponse<List<StreetResponseDto>> getAllStreets();


}
