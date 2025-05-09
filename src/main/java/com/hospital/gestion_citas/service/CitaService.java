package com.hospital.gestion_citas.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hospital.gestion_citas.model.Cita;
import com.hospital.gestion_citas.model.Consultorio;
import com.hospital.gestion_citas.model.Doctor;
import com.hospital.gestion_citas.repository.CitaRepository;
import com.hospital.gestion_citas.repository.ConsultorioRepository;
import com.hospital.gestion_citas.repository.DoctorRepository;
import com.hospital.gestion_citas.utilities.ResultadoValidacion;

@Service
public class CitaService {

    private CitaRepository citaRepository;
    private DoctorRepository doctorRepository;
    private ConsultorioRepository consultorioRepository;

    public CitaService(CitaRepository citaRepository, DoctorRepository doctorRepository, ConsultorioRepository consultorioRepository) {
        this.citaRepository = citaRepository;
        this.doctorRepository = doctorRepository;
        this.consultorioRepository = consultorioRepository;
    }
    
public ResultadoValidacion validarCita(Cita cita) {
        try {
            // Validar disponibilidad de consultorio
            if (citaRepository.existsByConsultorioAndHorarioAndCanceladaFalse(cita.getConsultorio(), cita.getHorario())) {
                return ResultadoValidacion.error("El consultorio no está disponible en ese horario");
            }
            
            // Validar disponibilidad del doctor
            if (citaRepository.existsByDoctorAndHorarioAndCanceladaFalse(
                cita.getDoctor(), cita.getHorario())) {
                return ResultadoValidacion.error("El doctor no está disponible en ese horario");
            }
            
            // Resto de validaciones...
            
            return ResultadoValidacion.exito("La cita está disponible para confirmación");
            
        } catch (Exception e) {
            return ResultadoValidacion.error("Error al validar la cita: " + e.getMessage());
        }
    }
    
    public void cancelarCita(Integer id) {
        citaRepository.findById(id).ifPresent(cita -> {
            cita.setCancelada(true);
            citaRepository.save(cita);
        });
    }
    
    // Otros métodos necesarios
}
