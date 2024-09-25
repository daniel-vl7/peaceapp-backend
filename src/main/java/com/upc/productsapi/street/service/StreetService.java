package com.upc.productsapi.street.service;



import com.upc.productsapi.shared.exception.ResourceNotFoundException;
import com.upc.productsapi.street.model.dto.response.StreetResponseDto;
import com.upc.productsapi.street.model.dto.request.StreetRequestDto;
import com.upc.productsapi.street.model.entity.Street;
import com.upc.productsapi.street.repository.IStreetRepository;
import com.upc.productsapi.shared.dto.enums.EStatus;
import com.upc.productsapi.shared.dto.response.ApiResponse;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetService implements IStreetService{

    private final IStreetRepository streetRepository;
    private final ModelMapper modelMapper;

    public StreetService(IStreetRepository streetRepository, ModelMapper modelMapper) {
        this.streetRepository = streetRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<StreetResponseDto> registerStreet(StreetRequestDto streetData) {
        //dto a entity
        var street = modelMapper.map(streetData, Street.class);

        //crear el producto
        var streetSaved = streetRepository.save(street);

        //entity a dto
        var streetResponse = modelMapper.map(streetSaved, StreetResponseDto.class);

        //retornar la respuesta
        return new ApiResponse<>("Calle registrada correctamente", EStatus.SUCCESS, streetResponse);
    }

    @Override
    public ApiResponse<Object> deleteStreetById(Long streetId) {
        //buscar el producto a eliminar
        var street = streetRepository.findById(streetId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con el id: " + streetId));

        //eliminar el producto
        streetRepository.delete(street);

        //retornar la respuesta
        return new ApiResponse<>("Producto eliminado correctamente", EStatus.SUCCESS, null);
    }

    @Override
    public ApiResponse<StreetResponseDto> getStreetByStreetId(long streetId) {
        //buscar el producto
        var street = streetRepository.findByStreetId(streetId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto con el nombre: " + streetId));

        //entity a dto
        var streetResponse = modelMapper.map(street, StreetResponseDto.class);

        //retornar la respuesta
        return new ApiResponse<>("Ok", EStatus.SUCCESS, streetResponse);
    }


    @Override
    public ApiResponse<List<StreetResponseDto>> getAllStreets() {
        //buscar todos los productos
        var streets = streetRepository.findAll();

        //entity a dto
        var streetsResponse = streets.stream()
                .map(street -> modelMapper.map(street, StreetResponseDto.class))
                .toList();

        //retornar la respuesta
        return new ApiResponse<>("Ok", EStatus.SUCCESS, streetsResponse);
    }


}
