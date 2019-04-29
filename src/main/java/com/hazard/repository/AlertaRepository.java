package com.hazard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hazard.model.Alerta;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {

}
