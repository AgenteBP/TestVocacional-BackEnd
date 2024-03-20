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

    public List<Object[]> obtenerCantidadUsuariosPorCarreras(Boolean interes) {
        return resultadoRepository.obtenerCantidadUsuariosPorCarreras(interes);
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