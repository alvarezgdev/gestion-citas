package com.hospital.gestion_citas.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private String especialidad;

    @OneToMany(mappedBy = "doctor")
    private List<Cita> citas;

}
