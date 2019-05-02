package com.hazard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hazard.model.TipoAlerta;

@Repository
public interface TipoAlertaRepository extends JpaRepository<TipoAlerta, Long> {

}