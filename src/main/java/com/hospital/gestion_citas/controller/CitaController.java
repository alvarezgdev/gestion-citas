package com.hospital.gestion_citas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hospital.gestion_citas.service.CitaService;

@Controller
@RequestMapping("/citas")
public class CitaController {
    
    private CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }
    
    @PostMapping("/validar")
    public String validarCita(@ModelAttribute Cita cita, BindingResult result,
                            RedirectAttributes redirectAttributes) {
        
        ResultadoValidacion resultado = citaService.validarCita(cita);
        
        redirectAttributes.addFlashAttribute("mensaje", resultado.getMensaje());
        redirectAttributes.addFlashAttribute("tipoMensaje", resultado.getTipoMensaje());
        
        if (resultado.isValido()) {
            redirectAttributes.addFlashAttribute("citaTemporal", cita);
        }
        
        return "redirect:/citas/nueva";
    }
}
