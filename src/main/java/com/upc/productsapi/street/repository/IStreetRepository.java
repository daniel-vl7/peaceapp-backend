package com.upc.productsapi.street.repository;

import com.upc.productsapi.street.model.entity.Street;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IStreetRepository extends JpaRepository<Street, Long> {


    Optional<Street> findByStreetId(long streetId);

}
