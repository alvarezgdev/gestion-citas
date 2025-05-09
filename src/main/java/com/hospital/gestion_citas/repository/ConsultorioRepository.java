package com.hospital.gestion_citas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.gestion_citas.model.Consultorio;

@Repository
public interface ConsultorioRepository extends JpaRepository<Consultorio, Integer> {
}
