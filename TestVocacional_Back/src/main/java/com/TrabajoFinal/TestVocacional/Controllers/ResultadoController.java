package com.TrabajoFinal.TestVocacional.Controllers;

import java.util.List;
import java.util.Map;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.TrabajoFinal.TestVocacional.Models.Resultados;
import com.TrabajoFinal.TestVocacional.Services.ResultadoService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ResultadoController {
    private final int DEFAULT_PAGE_NUMBER = 0;
    private final int DEFAULT_QUANTITY_PER_PAGE = 10;
    int anoActual = Calendar.getInstance().get(Calendar.YEAR);
    
    @Autowired
    private ResultadoService resultadoService;

    @GetMapping(value = "/resultados/esRes")
    public Page<Object[]> getAllEsResidentArg(@RequestParam Map<String, String> map) {
        
        // Page<Object[]> page;

        // if (!map.containsKey("page") && !map.containsKey("quantity")) {
        //     page = this.resultadoService.getAllEsResArg(this.DEFAULT_PAGE_NUMBER,
        //             this.DEFAULT_QUANTITY_PER_PAGE);
        // } else if (map.containsKey("page") && !map.containsKey("quantity")) {
        //     page = this.resultadoService.getAllEsResArg(Integer.parseInt(map.get("page")),
        //             this.DEFAULT_QUANTITY_PER_PAGE);
        // } else if (!map.containsKey("page") && map.containsKey("quantity")) {
        //     page = this.resultadoService.getAllEsResArg(this.DEFAULT_PAGE_NUMBER,
        //             Integer.parseInt(map.get("quantity")));
        // } else {
        //     page = this.resultadoService.getAllEsResArg(Integer.parseInt(map.get("page")),
        //             Integer.parseInt(map.get("quantity")));
        // }

        // Obtener parámetros de paginación
        int page = map.containsKey("page") ? Integer.parseInt(map.get("page")) : DEFAULT_PAGE_NUMBER;
        int quantityPerPage = map.containsKey("quantity") ? Integer.parseInt(map.get("quantity")) : DEFAULT_QUANTITY_PER_PAGE;

        // Obtener parámetros adicionales para la búsqueda
        String opcion = map.get("opcion");
        String valor = map.get("valor");
        boolean interes = Boolean.parseBoolean(map.getOrDefault("interes", "true"));
        Integer edadDesde = map.containsKey("edadDesde") ? Integer.parseInt(map.get("edadDesde")) : 16;
        Integer edadHasta = map.containsKey("edadHasta") ? Integer.parseInt(map.get("edadHasta")) : 100;

        Page<Object[]> resultados = resultadoService.getAllEsResArg(opcion, valor, edadDesde, edadHasta, interes, page, quantityPerPage);

        return resultados;
    }

    @GetMapping(value = "/resultados/esNoRes")
    public Page<Object[]> getAllEsNoResidentArg(@RequestParam Map<String, String> map) {
        
        // Page<Object[]> page;

        // if (!map.containsKey("page") && !map.containsKey("quantity")) {
        //     page = this.resultadoService.getAllEsNoResArg(this.DEFAULT_PAGE_NUMBER,
        //             this.DEFAULT_QUANTITY_PER_PAGE);
        // } else if (map.containsKey("page") && !map.containsKey("quantity")) {
        //     page = this.resultadoService.getAllEsNoResArg(Integer.parseInt(map.get("page")),
        //             this.DEFAULT_QUANTITY_PER_PAGE);
        // } else if (!map.containsKey("page") && map.containsKey("quantity")) {
        //     page = this.resultadoService.getAllEsNoResArg(this.DEFAULT_PAGE_NUMBER,
        //             Integer.parseInt(map.get("quantity")));
        // } else {
        //     page = this.resultadoService.getAllEsNoResArg(Integer.parseInt(map.get("page")),
        //             Integer.parseInt(map.get("quantity")));
        // }
        // return page;

        // Obtener parámetros de paginación
        int page = map.containsKey("page") ? Integer.parseInt(map.get("page")) : DEFAULT_PAGE_NUMBER;
        int quantityPerPage = map.containsKey("quantity") ? Integer.parseInt(map.get("quantity")) : DEFAULT_QUANTITY_PER_PAGE;

        // Obtener parámetros adicionales para la búsqueda
        String opcion = map.get("opcion");
        String valor = map.get("valor");
        boolean interes = Boolean.parseBoolean(map.getOrDefault("interes", "true"));
        Integer edadDesde = map.containsKey("edadDesde") ? Integer.parseInt(map.get("edadDesde")) : 16;
        Integer edadHasta = map.containsKey("edadHasta") ? Integer.parseInt(map.get("edadHasta")) : 100;

        Page<Object[]> resultados = resultadoService.getAllEsNoResArg(opcion, valor, edadDesde, edadHasta, interes, page, quantityPerPage);

        return resultados;
    }

    @GetMapping("/resultados/schoolInSanLuis")
    public Page<Object[]> getAllSchoolInSanLuis(@RequestParam Map<String, String> map) {

        // Obtener parámetros de paginación
        int page = map.containsKey("page") ? Integer.parseInt(map.get("page")) : DEFAULT_PAGE_NUMBER;
        int quantityPerPage = map.containsKey("quantity") ? Integer.parseInt(map.get("quantity")) : DEFAULT_QUANTITY_PER_PAGE;

        // Obtener parámetros adicionales para la búsqueda
        String opcion = map.get("opcion");
        String valor = map.get("valor");
        boolean interes = Boolean.parseBoolean(map.getOrDefault("interes", "true"));
        Integer edadDesde = map.containsKey("edadDesde") ? Integer.parseInt(map.get("edadDesde")) : 16;
        Integer edadHasta = map.containsKey("edadHasta") ? Integer.parseInt(map.get("edadHasta")) : 100;

        Page<Object[]> resultados = resultadoService.getAllSchoolInSanLuis(opcion, valor, edadDesde, edadHasta, interes, page, quantityPerPage);

        // return new ResponseEntity<>(resultados, HttpStatus.OK);
        return resultados;
    }

    // Cartas
    @GetMapping("/resultados/count")
    public Long countResults() {
        return resultadoService.countResults();
    }

    @GetMapping("/resultados/countWithInterest")
    public Long countResultsWithInterest() {
        return resultadoService.countResultsWithInterest();
    }

    @GetMapping("/resultados/mostChosenCareer")
    public List<Object[]> findCareerMostChosenWithInterest() {
        return resultadoService.findCareerMostChosenWithInterest();
    }

    @GetMapping("/resultados/mostFrequentSchool")
    public List<Object[]> findMostFrequentSchool() {
        return resultadoService.findMostFrequentSchool();
    }
    @GetMapping("/resultados/mostFrequentSchoolCom")
    public List<Object[]> findMostFrequentSchoolCom(@RequestParam List<String> escuelas) {
        return resultadoService.findMostFrequentSchoolCom(escuelas);
    }

    // Graficos

    @GetMapping("/resultados/viewGraph")
    public List<Object[]> getCantForCarreras(@RequestParam Map<String, String> map) {


        // Obtener parámetros adicionales para la búsqueda
        boolean interes = Boolean.parseBoolean(map.getOrDefault("interes", "true"));
        Integer edadMinima = map.containsKey("edadMinima") ? Integer.parseInt(map.get("edadMinima")) : 16;
        Integer edadMaxima = map.containsKey("edadMaxima") ? Integer.parseInt(map.get("edadMaxima")) : 100;
        Integer anoMinimo = map.containsKey("anoMinimo") ? Integer.parseInt(map.get("anoMinimo")) : 2023;
        Integer anoMaximo = map.containsKey("anoMaximo") ? Integer.parseInt(map.get("anoMaximo")) : anoActual;
        String escuelaParam = map.get("escuela");
        // String escuela = "null".equals(escuelaParam) ? null : escuelaParam;
        String escuela = "Todas las escuelas".equalsIgnoreCase(escuelaParam) || "null".equalsIgnoreCase(escuelaParam) ? null : escuelaParam;

        // System.out.println("la edad minimaaaaaaaaaaaaaaaaaaa tiene "+ edadMinima);
        // System.out.println("la edad maximaaaaaaaaaaaaaaaaaaa tiene "+ edadMaxima);
        // System.out.println("la ano minimaaaaaaaaaaaaaaaaaaa tiene "+ anoMinimo);
        // System.out.println("la ano maximaaaaaaaaaaaaaaaaaaa tiene "+ anoMaximo);
        return resultadoService.obtenerCantidadUsuariosPorCarreras(interes, edadMinima, edadMaxima, anoMinimo, anoMaximo, escuela);
    }


    

    // @GetMapping(value = "/resultados/viewAll")
    // public Page<Object[]> getAllResultados(@RequestParam Map<String, String> map) {
        
    //     Page<Object[]> page;

    //     if (!map.containsKey("page") && !map.containsKey("quantity")) {
    //         page = this.resultadoService.getAll(this.DEFAULT_PAGE_NUMBER,
    //                 this.DEFAULT_QUANTITY_PER_PAGE);
    //     } else if (map.containsKey("page") && !map.containsKey("quantity")) {
    //         page = this.resultadoService.getAll(Integer.parseInt(map.get("page")),
    //                 this.DEFAULT_QUANTITY_PER_PAGE);
    //     } else if (!map.containsKey("page") && map.containsKey("quantity")) {
    //         page = this.resultadoService.getAll(this.DEFAULT_PAGE_NUMBER,
    //                 Integer.parseInt(map.get("quantity")));
    //     } else {
    //         page = this.resultadoService.getAll(Integer.parseInt(map.get("page")),
    //                 Integer.parseInt(map.get("quantity")));
    //     }
    //     return page;
    // }

    @GetMapping("/resultados/viewAll")
    public Page<Object[]> getAllResultados(@RequestParam Map<String, String> map) {

        // Obtener parámetros de paginación
        int page = map.containsKey("page") ? Integer.parseInt(map.get("page")) : DEFAULT_PAGE_NUMBER;
        int quantityPerPage = map.containsKey("quantity") ? Integer.parseInt(map.get("quantity")) : DEFAULT_QUANTITY_PER_PAGE;

        // Obtener parámetros adicionales para la búsqueda
        String opcion = map.get("opcion");
        String valor = map.get("valor");
        boolean interes = Boolean.parseBoolean(map.getOrDefault("interes", "true"));
        Integer edadDesde = map.containsKey("edadDesde") ? Integer.parseInt(map.get("edadDesde")) : 16;
        Integer edadHasta = map.containsKey("edadHasta") ? Integer.parseInt(map.get("edadHasta")) : 100;

        Page<Object[]> resultados;
        
        resultados = resultadoService.buscarResultadosConFiltro(opcion, valor, edadDesde, edadHasta, interes, page, quantityPerPage);

        return resultados;
    }


    @PostMapping(value = "/resultados")
    public ResponseEntity<Resultados> postUsuarios(@RequestBody Resultados resultados) {
        System.out.println("el id Usuario tieneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee "+ resultados.getIdUsuario());
        Resultados resultadoInsertado = resultadoService.insert(resultados);
        
        // Verifica si la inserción fue exitosa
        if (resultadoInsertado != null && resultadoInsertado.getId() != null) {
            // Devuelve el usuario con la ID generada y un código de estado 201 (CREATED)
            return new ResponseEntity<>(resultadoInsertado, HttpStatus.CREATED);
        } else {
            // Si la inserción falla, puedes devolver un código de estado 500 (INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
