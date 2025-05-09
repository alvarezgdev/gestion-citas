package com.hospital.gestion_citas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hospital.gestion_citas.model.Cita;
import com.hospital.gestion_citas.model.Consultorio;
import com.hospital.gestion_citas.model.Doctor;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Integer> {

    boolean existsByConsultorioAndHorarioAndCanceladaFalse(Consultorio consultorio, Date horario);
    
    boolean existsByDoctorAndHorarioAndCanceladaFalse(Doctor doctor, Date horario);

    @Query(value = """
            SELECT * FROM citas cita
            LEFT JOIN consultorios cons ON cita.id_consultorio = cons.id_consultorio 
            LEFT JOIN doctores doc ON cita.id_doctor = doc.id_doctor 
            WHERE cons.id_consultorio = :idConsultorio 
            AND doc.id_doctor = :idDoctor 
            AND cita.horario_consulta BETWEEN :fechaInicio AND :fechaFin
            """, nativeQuery = true)
    List<Cita> getCitasPorFiltro(@Param("idConsultorio") Integer idConsultorio,
        @Param("idDoctor") Integer idDoctor, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
}
