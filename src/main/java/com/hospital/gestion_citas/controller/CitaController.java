package com.hospital.gestion_citas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hospital.gestion_citas.model.Cita;
import com.hospital.gestion_citas.service.CitaService;
import com.hospital.gestion_citas.utilities.ResultadoValidacion;

@Controller
@RequestMapping("/citas")
public class CitaController {
    
    private CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }
    
    @PostMapping("/validar")
    public ResultadoValidacion validarCita(@ModelAttribute Cita cita, BindingResult result) {
        
        ResultadoValidacion resultado = citaService.validarCita(cita);
        return resultado;
    }
}
