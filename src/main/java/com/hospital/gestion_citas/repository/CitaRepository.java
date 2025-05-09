package com.hospital.gestion_citas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hospital.gestion_citas.model.Cita;
import com.hospital.gestion_citas.model.Consultorio;
import com.hospital.gestion_citas.model.Doctor;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    List<Cita> findByDoctorAndHorarioBetween(Doctor doctor, Date inicio, Date fin);
    
    List<Cita> findByConsultorioAndHorarioBetween(Consultorio consultorio, Date inicio, Date fin);
    
    List<Cita> findByNombrePacienteAndHorarioBetween(String nombrePaciente, Date inicio, Date fin);
    
    boolean existsByConsultorioAndHorarioAndCanceladaFalse(Consultorio consultorio, Date horario);
    
    boolean existsByDoctorAndHorarioAndCanceladaFalse(Doctor doctor, Date horario);
    
    int countByDoctorAndHorarioBetweenAndCanceladaFalse(Doctor doctor, Date inicio, Date fin);
}