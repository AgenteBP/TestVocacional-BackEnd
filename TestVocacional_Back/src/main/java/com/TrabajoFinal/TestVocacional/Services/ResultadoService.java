package com.TrabajoFinal.TestVocacional.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.TrabajoFinal.TestVocacional.Models.Resultados;
import com.TrabajoFinal.TestVocacional.Repositories.ResultadoRepository;
import com.TrabajoFinal.TestVocacional.exceptions.ErrorResponse;

@Service
public class ResultadoService {
    
    @Autowired
    private ResultadoRepository resultadoRepository;

    public Page<Object[]> getAllEsResArg(String opcion, String valor, Integer edadDesde, Integer edadHasta, Boolean interes, int page, int quantityPerPage) {

        return this.resultadoRepository.getDataResident(opcion, valor, edadDesde, edadHasta, interes, PageRequest.of(page, quantityPerPage));
    }

    public Page<Object[]> getAllEsNoResArg(String opcion, String valor, Integer edadDesde, Integer edadHasta, Boolean interes, int page, int quantityPerPage) {

        return this.resultadoRepository.getDataNoResident(opcion, valor, edadDesde, edadHasta, interes, PageRequest.of(page, quantityPerPage));
    }

    public Page<Object[]> getAllSchoolInSanLuis(String opcion, String valor, Integer edadDesde, Integer edadHasta, Boolean interes, int page, int quantityPerPage) {
        return resultadoRepository.getDataSchoolInSanLuis(opcion, valor, edadDesde, edadHasta, interes, PageRequest.of(page, quantityPerPage));
    }

    public Page<Object[]> getAllSchoolInSanLuisFilterP(Boolean interes,Integer edadMinima,Integer edadMaxima,Integer anoMinimo,Integer anoMaximo,String escuela, int page, int quantityPerPage) {
        return resultadoRepository.getDataSchoolInSanLuisFilterP(interes, edadMinima, edadMaxima, anoMinimo, anoMaximo, escuela, PageRequest.of(page, quantityPerPage));
    }

    // Cartas
    
    public Long countResults() {
        return resultadoRepository.contarResultados();
    }

    public Long countResultsWithInterest() {
        return resultadoRepository.contarResultadosConInteres();
    }

    public List<Object[]> findCareerMostChosenWithInterest() {
        return resultadoRepository.encontrarCarreraMasElegidaConInteres();
    }

    public List<Object[]> findMostFrequentSchool() {
        return resultadoRepository.encontrarEscuelaMasFrecuente();
    }
    public List<Object[]> findMostFrequentSchoolCom(List<String> escuelas) {
        return resultadoRepository.encontrarEscuelasMasFrecuentesComparativo(escuelas);
    }

    // Datos para tabla tabulada
    // Cantidad en cada carrera

    public List<Object[]> getQuantityByCareerTable() {
        return resultadoRepository.obtenerCantidadPorCarreraTabulado();
    }

    // Escuelas donde se han realizado test
    public Page<Object[]> getQuantityBySchoolTable(int page, int quantityPerPage) {
        return resultadoRepository.obtenerCantidadEscuelasPaginado(PageRequest.of(page, quantityPerPage));
    }

    // Graficos
    public List<Object[]> obtenerCantidadUsuariosPorCarrerasTotal(Boolean interes,Integer edadMinima,Integer edadMaxima,Integer anoMinimo,Integer anoMaximo) {
        return resultadoRepository.obtenerCantidadUsuariosPorCarrerasTotal(
            interes, edadMinima, edadMaxima, anoMinimo, anoMaximo
        );
    }

    public List<Object[]> obtenerCantidadUsuariosPorCarrerasSanLuis(Boolean interes,Integer edadMinima,Integer edadMaxima,Integer anoMinimo,Integer anoMaximo,String escuela) {
        return resultadoRepository.obtenerCantidadUsuariosPorCarrerasSanLuis(
            interes, edadMinima, edadMaxima, anoMinimo, anoMaximo, escuela
        );
    }

    public List<Object[]> obtenerCantidadUsuariosPorCarrerasPronvicia(Boolean interes,Integer edadMinima,Integer edadMaxima,Integer anoMinimo,Integer anoMaximo,String provincia) {
        return resultadoRepository.obtenerCantidadUsuariosPorCarrerasProvincias(
            interes, edadMinima, edadMaxima, anoMinimo, anoMaximo, provincia
        );
    }

    // public Page<Object[]> getAll(int page, int quantityPerPage) {

    //    return this.resultadoRepository.findAll2(PageRequest.of(page, quantityPerPage));
    // }
    
    public Page<Object[]> buscarResultadosConFiltro(String opcion, String valor, Integer edadDesde, Integer edadHasta, Boolean interes, int page, int quantityPerPage) {
        return resultadoRepository.getDataAllForSearch(opcion, valor, edadDesde, edadHasta, interes, PageRequest.of(page, quantityPerPage));
    }

    public Resultados insert(Resultados resultados) {
        
        // Usuarios usuarios = new Usuarios();
        // usuarios.setId(resultados.getUsuarios().getId());
        // resultados.setUsuarios(usuarios);
        Resultados resultados2 = new Resultados();
        try {
            if(resultados.isInteres() == true){
                resultados2.setCarreraObtenida(resultados.getCarreraObtenida());
            }
            else{
                resultados2.setCarreraObtenida(null);
            }
            resultados.setActive(true);
            resultados2 = this.resultadoRepository.save(resultados);
            
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            throw new ErrorResponse(e.getMostSpecificCause().getMessage(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        return resultados2;
    }

}
