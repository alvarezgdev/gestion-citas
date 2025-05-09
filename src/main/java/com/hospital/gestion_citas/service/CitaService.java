package com.hospital.gestion_citas.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.hospital.gestion_citas.model.Cita;
import com.hospital.gestion_citas.repository.CitaRepository;
import com.hospital.gestion_citas.utilities.ResultadoValidacion;

@Service
public class CitaService {

    private CitaRepository citaRepository;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }
    
public ResultadoValidacion validarCita(Cita cita) {
        try {
            // validar disponibilidad de consultorio
            if (citaRepository.existsByConsultorioAndHorarioAndCanceladaFalse(cita.getConsultorio(), cita.getHorario())) {
                return ResultadoValidacion.error("El consultorio no está disponible en ese horario");
            }
            
            // validar disponibilidad del doctor
            if (citaRepository.existsByDoctorAndHorarioAndCanceladaFalse(
                cita.getDoctor(), cita.getHorario())) {
                return ResultadoValidacion.error("El doctor no está disponible en ese horario");
            }
            
            citaRepository.save(cita);
            return ResultadoValidacion.exito("La cita está disponible para confirmación");
            
        } catch (Exception e) {
            return ResultadoValidacion.error("Error al validar la cita: " + e.getMessage());
        }
    }
    
    public ResultadoValidacion cancelarCita(Cita cita) {
        if(cita.isCancelada() && cita.getHorario().compareTo(new Date()) < 0){
            cita.setCancelada(true);
            citaRepository.save(cita);
            return ResultadoValidacion.exito("Cita cancelada con éxito");
        }
        return ResultadoValidacion.error("No es posible cancelar la cita");
    }

}
