package com.hospital.gestion_citas.utilities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultadoValidacion {
    private boolean valido;
    private String mensaje;
    private String tipoMensaje;

    public ResultadoValidacion(boolean valido, String mensaje, String tipoMensaje) {
        this.valido = valido;
        this.mensaje = mensaje;
        this.tipoMensaje = tipoMensaje;
    }

    public static ResultadoValidacion exito(String mensaje) {
        return new ResultadoValidacion(true, mensaje, "exito");
    }
    
    public static ResultadoValidacion error(String mensaje) {
        return new ResultadoValidacion(false, mensaje, "error");
    }
}