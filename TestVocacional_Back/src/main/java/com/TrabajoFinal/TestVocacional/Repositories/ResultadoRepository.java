package com.TrabajoFinal.TestVocacional.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.TrabajoFinal.TestVocacional.Models.Resultados;

public interface ResultadoRepository extends JpaRepository<Resultados, Integer>{

    @Query("SELECT u.email, u.edad, r.fecha, u.esResidenteArg, u.provincia, r.carreraObtenida FROM Resultados r JOIN r.usuarios u WHERE r.active = true AND u.esResidenteArg = true " +
    "AND (:opcion IS NULL OR " +
    "(:opcion = 'email' AND u.email LIKE %:valor%) OR " +
    "(:opcion = 'carreraObtenida' AND r.carreraObtenida LIKE %:valor%)) AND " +
    "u.edad >= :edadDesde AND u.edad <= :edadHasta AND r.interes = :interes")
    Page<Object[]> getDataResident(@Param("opcion") String opcion, @Param("valor") String valor,  @Param("edadDesde") Integer edadDesde, @Param("edadHasta") Integer edadHasta, @Param("interes") Boolean interes, Pageable pageable);
    
    @Query("SELECT u.email, u.edad, r.fecha, u.esResidenteArg, u.paisOrigen, r.carreraObtenida FROM Resultados r JOIN r.usuarios u WHERE r.active = true AND u.esResidenteArg = false " +
    "AND (:opcion IS NULL OR " +
    "(:opcion = 'email' AND u.email LIKE %:valor%) OR " +
    "(:opcion = 'carreraObtenida' AND r.carreraObtenida LIKE %:valor%))AND " +
    "u.edad >= :edadDesde AND u.edad <= :edadHasta AND r.interes = :interes")
    Page<Object[]> getDataNoResident(@Param("opcion") String opcion, @Param("valor") String valor,  @Param("edadDesde") Integer edadDesde, @Param("edadHasta") Integer edadHasta, @Param("interes") Boolean interes, Pageable pageable);

    @Query("SELECT u.email, u.edad, u.provincia, u.schoolInSanLuis, r.fecha, r.carreraObtenida, r.id FROM Resultados r JOIN r.usuarios u WHERE r.active = true AND u.provincia = 'San Luis' " +
    " AND (:opcion IS NULL OR " +
    "(:opcion = 'email' AND u.email LIKE %:valor%) OR " +
    "(:opcion = 'schoolInSanLuis' AND u.schoolInSanLuis LIKE %:valor%) OR " +
    "(:opcion = 'carreraObtenida' AND r.carreraObtenida LIKE %:valor%)) AND " +
    "u.edad >= :edadDesde AND u.edad <= :edadHasta AND r.interes = :interes")
    Page<Object[]> getDataSchoolInSanLuis( @Param("opcion") String opcion, @Param("valor") String valor, @Param("edadDesde") Integer edadDesde, @Param("edadHasta") Integer edadHasta, @Param("interes") Boolean interes, Pageable pageable);

    @Query(nativeQuery = true, value =
        "SELECT u.email, u.edad, u.provincia, u.school_in_san_luis, r.fecha, r.carrera_obtenida, r.id FROM resultados r JOIN usuarios u WHERE r.active = true AND u.provincia = 'San Luis' AND r.interes = :interes " +
        "AND (u.edad >= :edadMinima AND u.edad <= :edadMaxima) " +
        "AND (YEAR(r.fecha) >= :anoMinimo AND YEAR(r.fecha) <= :anoMaximo) " +
        // "AND (:escuela IS NULL OR (u.provincia = 'San Luis' AND u.school_in_san_luis = :escuela)) " +
        "AND ((:escuela IS NULL AND u.provincia = 'San Luis') OR (u.school_in_san_luis = :escuela)) ")
    Page<Object[]> getDataSchoolInSanLuisFilterP(@Param("interes") Boolean interes, 
        @Param("edadMinima") Integer edadMinima,
        @Param("edadMaxima") Integer edadMaxima,
        @Param("anoMinimo") Integer anoMinimo,
        @Param("anoMaximo") Integer anoMaximo,
        @Param("escuela") String escuela,
        Pageable pageable);

    

    // Cartas
    @Query("SELECT COUNT(r) FROM Resultados r")
    Long contarResultados();

    @Query(
        "SELECT COUNT(r) FROM Resultados r WHERE r.interes = true")
    Long contarResultadosConInteres();

    @Query(value = "SELECT carrera_obtenida AS tipoCarrera, COUNT(*) AS cantidad " +
            "FROM resultados r " +
            "WHERE r.interes = true " +
            "AND carrera_obtenida IN ('Ingeniería en Informática', 'Ingeniería en Computación', " +
            "'Licenciatura en Ciencia de la Computación', 'Profesorado en Ciencias de la Computación', " +
            "'Tecnicatura Universitaria en Web', 'Tecnicatura Universitaria en Redes de Computadoras') " +
            "GROUP BY carrera_obtenida " +
            "HAVING COUNT(*) = (" +
            "   SELECT MAX(contador) " +
            "   FROM (" +
            "       SELECT COUNT(*) AS contador " +
            "       FROM resultados " +
            "       WHERE interes = true " +
            "       AND carrera_obtenida IN (" +
            "           'Ingeniería en Informática', 'Ingeniería en Computación', " +
            "           'Licenciatura en Ciencia de la Computación', 'Profesorado en Ciencias de la Computación', " +
            "           'Tecnicatura Universitaria en Web', 'Tecnicatura Universitaria en Redes de Computadoras' " +
            "       ) " +
            "       GROUP BY carrera_obtenida" +
            "   ) AS max_ocurrencias" +
            ") " +
            "ORDER BY carrera_obtenida",
            nativeQuery = true)
    List<Object[]> encontrarCarreraMasElegidaConInteres();

    @Query(
    "SELECT u.schoolInSanLuis, COUNT(*) AS cantidad FROM Resultados r " +
    "JOIN Usuarios u ON r.idUsuario = u.id " + // Realizar un JOIN con la tabla Usuarios
    "WHERE u.schoolInSanLuis IS NOT NULL " + // Filtrar valores no nulos
    "GROUP BY u.schoolInSanLuis " +
    "ORDER BY COUNT(*) DESC"
    )
    List<Object[]> encontrarEscuelaMasFrecuente();

    @Query(
    "SELECT u.schoolInSanLuis, COUNT(*) AS cantidad FROM Resultados r " +
    "JOIN Usuarios u ON r.idUsuario = u.id " + // Realizar un JOIN con la tabla Usuarios
    "WHERE u.schoolInSanLuis IS NOT NULL " + // Filtrar valores no nulos
    "AND u.schoolInSanLuis IN :escuelas " + // Filtrar por escuelas específicas
    "GROUP BY u.schoolInSanLuis " +
    "ORDER BY COUNT(*) DESC"
    )
    List<Object[]> encontrarEscuelasMasFrecuentesComparativo(@Param("escuelas") List<String> escuelas);

    // Datos para tabla tabulada
    // Cantidad en cada carrera
    @Query(value = "SELECT tipoCarrera, COALESCE(cantidad, 0) AS cantidad " +
        "FROM (SELECT 'Ingeniería en Informática' AS tipoCarrera " +
        "      UNION SELECT 'Ingeniería en Computación' " +
        "      UNION SELECT 'Licenciatura en Ciencia de la Computación' " +
        "      UNION SELECT 'Profesorado en Ciencias de la Computación' " +
        "      UNION SELECT 'Tecnicatura Universitaria en Web' " +
        "      UNION SELECT 'Tecnicatura Universitaria en Redes de Computadoras') carreras " +
        "LEFT JOIN (SELECT carrera_obtenida, COUNT(*) AS cantidad " +
        "           FROM resultados r " +
        "           WHERE r.interes = true AND " +
        "           carrera_obtenida IN ('Ingeniería en Informática', 'Ingeniería en Computación', " +
        "           'Licenciatura en Ciencia de la Computación', 'Profesorado en Ciencias de la Computación', " +
        "           'Tecnicatura Universitaria en Web', 'Tecnicatura Universitaria en Redes de Computadoras') " +
        "           GROUP BY carrera_obtenida) cantidades " +
        "ON carreras.tipoCarrera = cantidades.carrera_obtenida", nativeQuery = true)
    List<Object[]> obtenerCantidadPorCarreraTabulado();

    // Escuelas donde se han realizado test
    @Query(nativeQuery = true, value = "SELECT u.school_in_san_luis, COUNT(*) AS cantidad " +
                   "FROM usuarios u " +
                   "WHERE u.provincia = 'San Luis' " +
                   "GROUP BY u.school_in_san_luis " +
                   "ORDER BY COUNT(*) DESC"
                   )
    Page<Object[]> obtenerCantidadEscuelasPaginado(Pageable pageable);

    // Alumnos que han hechos test con seguimiento
    @Query(value = "SELECT u.email, u.edad, r.fecha, u.pais_origen, u.provincia, " +
            "u.school_in_san_luis, r.carrera_obtenida, GROUP_CONCAT(rec.id_pregunta ORDER BY rec.id) AS id_preguntas, " +
            "GROUP_CONCAT(rec.opcion_seleccionada ORDER BY rec.id) AS opciones_seleccionadas " +
            "FROM usuarios u " +
            "JOIN resultados r ON u.id = r.id_usuario " +
            "JOIN recorrido rec ON r.id = rec.id_resultado " +
            "WHERE r.interes = true AND r.save_test = true " +
            "GROUP BY u.email, u.edad, r.fecha, u.pais_origen, u.provincia, " +
            "u.school_in_san_luis, r.carrera_obtenida",
            countQuery = "SELECT count(*) FROM resultados",
            nativeQuery = true)
    Page<Object[]> obtenerRecorridoDeTest(Pageable pageable);

    // Alumnos de escuelas en San Luis que han hechos test con seguimiento
    @Query(value = "SELECT rec.id_pregunta, rec.opcion_seleccionada " +
            "FROM recorrido rec " +
            "WHERE rec.active = true AND rec.id_resultado = :idResultado ",
            nativeQuery = true)
    List<Object[]> obtenerSeguimientoDeTest(@Param("idResultado")int idResultado);
    
    // Graficos
    @Query(nativeQuery = true, value =
        "SELECT carrera_obtenida AS tipoCarrera, COUNT(*) AS cantidad FROM resultados r JOIN usuarios u ON r.id_usuario = u.id " +
        "WHERE (r.interes = :interes) " + 
        "AND (u.edad >= :edadMinima AND u.edad <= :edadMaxima) " +
        "AND (YEAR(r.fecha) >= :anoMinimo AND YEAR(r.fecha) <= :anoMaximo) " +
        // "AND (u.provincia IS NOT NULL AND u.pais_origen IS NOT NULL) " +
        "AND carrera_obtenida IN ('Ingeniería en Informática', 'Ingeniería en Computación', 'Licenciatura en Ciencia de la Computación', 'Profesorado en Ciencias de la Computación', 'Tecnicatura Universitaria en Web', 'Tecnicatura Universitaria en Redes de Computadoras') " +
        "GROUP BY carrera_obtenida")
    List<Object[]> obtenerCantidadUsuariosPorCarrerasTotal(
        @Param("interes") Boolean interes,
        @Param("edadMinima") Integer edadMinima,
        @Param("edadMaxima") Integer edadMaxima,
        @Param("anoMinimo") Integer anoMinimo,
        @Param("anoMaximo") Integer anoMaximo
    );

    @Query(nativeQuery = true, value =
        "SELECT carrera_obtenida AS tipoCarrera, COUNT(*) AS cantidad FROM resultados r JOIN usuarios u ON r.id_usuario = u.id " +
        "WHERE (r.interes = :interes) " + 
        "AND (u.edad >= :edadMinima AND u.edad <= :edadMaxima) " +
        "AND (YEAR(r.fecha) >= :anoMinimo AND YEAR(r.fecha) <= :anoMaximo) " +
        // "AND (:escuela IS NULL OR (u.provincia = 'San Luis' AND u.school_in_san_luis = :escuela)) " +
        "AND ((:escuela IS NULL AND u.provincia = 'San Luis') OR (u.school_in_san_luis = :escuela)) " +
        "AND carrera_obtenida IN ('Ingeniería en Informática', 'Ingeniería en Computación', 'Licenciatura en Ciencia de la Computación', 'Profesorado en Ciencias de la Computación', 'Tecnicatura Universitaria en Web', 'Tecnicatura Universitaria en Redes de Computadoras') " +
        "GROUP BY carrera_obtenida")
    List<Object[]> obtenerCantidadUsuariosPorCarrerasSanLuis(
        @Param("interes") Boolean interes,
        @Param("edadMinima") Integer edadMinima,
        @Param("edadMaxima") Integer edadMaxima,
        @Param("anoMinimo") Integer anoMinimo,
        @Param("anoMaximo") Integer anoMaximo,
        @Param("escuela") String escuela
    );

    @Query(nativeQuery = true, value =
        "SELECT carrera_obtenida AS tipoCarrera, COUNT(*) AS cantidad FROM resultados r JOIN usuarios u ON r.id_usuario = u.id " +
        "WHERE (r.interes = :interes) " + 
        "AND (u.edad >= :edadMinima AND u.edad <= :edadMaxima) " +
        "AND (YEAR(r.fecha) >= :anoMinimo AND YEAR(r.fecha) <= :anoMaximo) " +
        // "AND (:provincia IS NULL OR (u.pais_origen IS NULL AND u.provincia = :provincia)) " +
        // "AND (:provincia IS NULL u.provincia IS NOT NULL OR (u.provincia = :provincia)) " +
        "AND ((:provincia IS NULL AND u.provincia IS NOT NULL) OR (u.provincia = :provincia)) " +
        "AND carrera_obtenida IN ('Ingeniería en Informática', 'Ingeniería en Computación', 'Licenciatura en Ciencia de la Computación', 'Profesorado en Ciencias de la Computación', 'Tecnicatura Universitaria en Web', 'Tecnicatura Universitaria en Redes de Computadoras') " +
        "GROUP BY carrera_obtenida")
    List<Object[]> obtenerCantidadUsuariosPorCarrerasProvincias(
        @Param("interes") Boolean interes,
        @Param("edadMinima") Integer edadMinima,
        @Param("edadMaxima") Integer edadMaxima,
        @Param("anoMinimo") Integer anoMinimo,
        @Param("anoMaximo") Integer anoMaximo,
        @Param("provincia") String provincia
    );

    @Query("SELECT u.email, u.edad, r.fecha, r.carreraObtenida FROM Resultados r JOIN r.usuarios u WHERE r.active = true " +
    "AND (:opcion IS NULL OR " +
    "(:opcion = 'email' AND u.email LIKE %:valor%) OR " +
    "(:opcion = 'carreraObtenida' AND r.carreraObtenida LIKE %:valor%)) AND " +
    "u.edad >= :edadDesde AND u.edad <= :edadHasta AND r.interes = :interes")
    Page<Object[]> getDataAllForSearch( @Param("opcion") String opcion, @Param("valor") String valor, @Param("edadDesde") Integer edadDesde, @Param("edadHasta") Integer edadHasta, @Param("interes") Boolean interes, Pageable pageable);

    Page<Resultados> findAllByActiveTrue(Pageable pageable);

}
