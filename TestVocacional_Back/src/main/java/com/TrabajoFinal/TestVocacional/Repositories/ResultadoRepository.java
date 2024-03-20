package com.TrabajoFinal.TestVocacional.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.TrabajoFinal.TestVocacional.Models.Resultados;

public interface ResultadoRepository extends JpaRepository<Resultados, Integer>{

    // @Query("SELECT u.email, r.fecha, u.esResidenteArg, u.provincia, r.carreraObtenida FROM Resultados r JOIN r.usuarios u WHERE r.active = true AND u.esResidenteArg = true")
    // Page<Object[]> getDataResident(Pageable pageable);

    @Query("SELECT u.email, u.edad, r.fecha, u.esResidenteArg, u.provincia, r.carreraObtenida FROM Resultados r JOIN r.usuarios u WHERE r.active = true AND u.esResidenteArg = true " +
    "AND (:opcion IS NULL OR " +
    "(:opcion = 'email' AND u.email LIKE %:valor%) OR " +
    "(:opcion = 'carreraObtenida' AND r.carreraObtenida LIKE %:valor%)) AND " +
    "u.edad >= :edadDesde AND u.edad <= :edadHasta AND r.interes = :interes")
    Page<Object[]> getDataResident(@Param("opcion") String opcion, @Param("valor") String valor,  @Param("edadDesde") Integer edadDesde, @Param("edadHasta") Integer edadHasta, @Param("interes") Boolean interes, Pageable pageable);

    // @Query("SELECT u.email, r.fecha, u.esResidenteArg, u.paisOrigen, r.carreraObtenida FROM Resultados r JOIN r.usuarios u WHERE r.active = true AND u.esResidenteArg = false")
    // Page<Object[]> getDataNoResident(Pageable pageable);
    
    @Query("SELECT u.email, u.edad, r.fecha, u.esResidenteArg, u.paisOrigen, r.carreraObtenida FROM Resultados r JOIN r.usuarios u WHERE r.active = true AND u.esResidenteArg = false " +
    "AND (:opcion IS NULL OR " +
    "(:opcion = 'email' AND u.email LIKE %:valor%) OR " +
    "(:opcion = 'carreraObtenida' AND r.carreraObtenida LIKE %:valor%))AND " +
    "u.edad >= :edadDesde AND u.edad <= :edadHasta AND r.interes = :interes")
    Page<Object[]> getDataNoResident(@Param("opcion") String opcion, @Param("valor") String valor,  @Param("edadDesde") Integer edadDesde, @Param("edadHasta") Integer edadHasta, @Param("interes") Boolean interes, Pageable pageable);

    @Query("SELECT u.email, u.edad, u.provincia, u.schoolInSanLuis, r.fecha, r.carreraObtenida FROM Resultados r JOIN r.usuarios u WHERE r.active = true AND u.provincia = 'San Luis' " +
    " AND (:opcion IS NULL OR " +
    "(:opcion = 'email' AND u.email LIKE %:valor%) OR " +
    "(:opcion = 'carreraObtenida' AND r.carreraObtenida LIKE %:valor%)) AND " +
    "u.edad >= :edadDesde AND u.edad <= :edadHasta AND r.interes = :interes")
    Page<Object[]> getDataSchoolInSanLuis( @Param("opcion") String opcion, @Param("valor") String valor, @Param("edadDesde") Integer edadDesde, @Param("edadHasta") Integer edadHasta, @Param("interes") Boolean interes, Pageable pageable);

    // @Query(nativeQuery = true, value =
    //         "SELECT carrera_obtenida AS tipoCarrera, COUNT(*) AS cantidad " +
    //         "FROM resultados " +
    //         "WHERE carrera_obtenida IN ('Ingeniería en Informática', 'Ingeniería en Computación', 'Licenciatura en Ciencia de la Computación', 'Profesorado en Ciencias de la Computación', 'Tecnicatura Web', 'Tecnicatura Universitaria en Redes de Computadoras') " +
    //         "GROUP BY carrera_obtenida") 
    // List<Object[]> obtenerCantidadUsuariosPorCarreras();
    @Query(nativeQuery = true, value =
        "SELECT carrera_obtenida AS tipoCarrera, COUNT(*) AS cantidad " +
        "FROM resultados " +
        "WHERE interes = :interes AND carrera_obtenida IN ('Ingeniería en Informática', 'Ingeniería en Computación', 'Licenciatura en Ciencia de la Computación', 'Profesorado en Ciencias de la Computación', 'Tecnicatura Web', 'Tecnicatura Universitaria en Redes de Computadoras') " +
        "GROUP BY carrera_obtenida")
    List<Object[]> obtenerCantidadUsuariosPorCarreras(@Param("interes") Boolean interes);


    // @Query(nativeQuery = true, value =
    //     "SELECT carrera_obtenida AS tipoCarrera, COUNT(*) AS cantidad " +
    //     "FROM resultados " +
    //     "WHERE interes = false AND carrera_obtenida IN ('Ingeniería en Informática', 'Ingeniería en Computación', 'Licenciatura en Ciencia de la Computación', 'Profesorado en Ciencias de la Computación', 'Tecnicatura Web', 'Tecnicatura Universitaria en Redes de Computadoras') " +
    //     "GROUP BY carrera_obtenida")
    // List<Object[]> obtenerCantidadUsuariosPorCarrerasNoI();

    // @Query("SELECT u.email, u.edad, r.fecha, r.carreraObtenida FROM Resultados r JOIN r.usuarios u WHERE r.active = true " +
    // "AND (:opcion IS NULL OR " +
    // "(:opcion = 'email' AND u.email LIKE %:valor%) OR " +
    // "(:opcion = 'carreraObtenida' AND r.carreraObtenida LIKE %:valor%))")
    // Page<Object[]> getDataAllForSearch(@Param("opcion") String opcion, @Param("valor") String valor, Pageable pageable);

    @Query("SELECT u.email, u.edad, r.fecha, r.carreraObtenida FROM Resultados r JOIN r.usuarios u WHERE r.active = true " +
    "AND (:opcion IS NULL OR " +
    "(:opcion = 'email' AND u.email LIKE %:valor%) OR " +
    "(:opcion = 'carreraObtenida' AND r.carreraObtenida LIKE %:valor%)) AND " +
    "u.edad >= :edadDesde AND u.edad <= :edadHasta AND r.interes = :interes")
    Page<Object[]> getDataAllForSearch( @Param("opcion") String opcion, @Param("valor") String valor, @Param("edadDesde") Integer edadDesde, @Param("edadHasta") Integer edadHasta, @Param("interes") Boolean interes, Pageable pageable);
    // @Query("SELECT u.email, u.edad, r.fecha, r.carreraObtenida FROM Resultados r JOIN r.usuarios u WHERE r.active = true")
    // Page<Object[]> findAll2(Pageable pageable);

    Page<Resultados> findAllByActiveTrue(Pageable pageable);

}
