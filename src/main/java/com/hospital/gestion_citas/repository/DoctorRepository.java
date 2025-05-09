package com.hospital.gestion_citas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.gestion_citas.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
