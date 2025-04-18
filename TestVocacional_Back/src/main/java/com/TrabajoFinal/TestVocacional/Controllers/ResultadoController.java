package com.TrabajoFinal.TestVocacional.Controllers;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.TrabajoFinal.TestVocacional.DTO.CareerMetricsDTO;
import com.TrabajoFinal.TestVocacional.DTO.ResultadoDTO;
import com.TrabajoFinal.TestVocacional.Services.ResultadoService;
import com.TrabajoFinal.TestVocacional.Urls.UrlFront;
import com.TrabajoFinal.TestVocacional.Utils.ExcelGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = {UrlFront.urlLocal, UrlFront.urlNetlify}, exposedHeaders = "Content-Disposition")
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

    @GetMapping("/resultados/schoolInSanLuisFP")
    public Page<Object[]> getAllSchoolInSanLuisFilterP(@RequestParam Map<String, String> map) {

        // Obtener parámetros de paginación
        int page = map.containsKey("page") ? Integer.parseInt(map.get("page")) : DEFAULT_PAGE_NUMBER;
        int quantityPerPage = map.containsKey("quantity") ? Integer.parseInt(map.get("quantity")) : DEFAULT_QUANTITY_PER_PAGE;

        // Obtener parámetros adicionales para la búsqueda
        boolean interes = Boolean.parseBoolean(map.getOrDefault("interes", "true"));
        Integer edadMinima = map.containsKey("edadMinima") ? Integer.parseInt(map.get("edadMinima")) : 16;
        Integer edadMaxima = map.containsKey("edadMaxima") ? Integer.parseInt(map.get("edadMaxima")) : 100;
        Integer anoMinimo = map.containsKey("anoMinimo") ? Integer.parseInt(map.get("anoMinimo")) : 2023;
        Integer anoMaximo = map.containsKey("anoMaximo") ? Integer.parseInt(map.get("anoMaximo")) : anoActual;
        String escuelaParam = map.get("escuela");
        // String escuela = "null".equals(escuelaParam) ? null : escuelaParam;
        String escuela = "Todas las escuelas".equalsIgnoreCase(escuelaParam) || "null".equalsIgnoreCase(escuelaParam) ? null : escuelaParam;

        System.out.println("las escuelas tienen "+escuelaParam);
        Page<Object[]> resultados = resultadoService.getAllSchoolInSanLuisFilterP(interes, edadMinima, edadMaxima, anoMinimo, anoMaximo, escuela, page, quantityPerPage);

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

    // Datos para tabla tabulada
    // Cantidad en cada carrera

    @GetMapping("/resultados/quantityByCareerTable")
    public List<Object[]> getQuantityByCareerTableController() {
        return resultadoService.getQuantityByCareerTable();
    }

    // Escuelas donde se han realizado test
    @GetMapping("/resultados/quantityBySchoolTable")
    public ResponseEntity<Page<Object[]>> getQuantityBySchoolTableController(@RequestParam Map<String, String> map) {
        int page = map.containsKey("page") ? Integer.parseInt(map.get("page")) : 0;
        int quantityPerPage = map.containsKey("quantity") ? Integer.parseInt(map.get("quantity")) : 10;

        Page<Object[]> escuelas = resultadoService.getQuantityBySchoolTable(page, quantityPerPage);
        return new ResponseEntity<>(escuelas, HttpStatus.OK);
    }

    // Alumnos que han hechos test con seguimiento
    @GetMapping("/resultados/tour")
    public ResponseEntity<Page<Object[]>> getTableTourController(@RequestParam Map<String, String> map) {
        int page = map.containsKey("page") ? Integer.parseInt(map.get("page")) : 0;
        int quantityPerPage = map.containsKey("quantity") ? Integer.parseInt(map.get("quantity")) : 10;

        Page<Object[]> escuelas = resultadoService.getTableTour(page, quantityPerPage);
        return new ResponseEntity<>(escuelas, HttpStatus.OK);
    }

    // Alumnos de escuelas en San Luis que han hechos test con seguimiento
    @GetMapping("/resultados/tracking")
    public ResponseEntity<List<Object[]> > getTableTrackingController(@RequestParam Map<String, String> map) {
        int idResultado = Integer.parseInt(map.get("idResultado"));

        List<Object[]>  escuelas = resultadoService.getTableTracking(idResultado);
        return new ResponseEntity<>(escuelas, HttpStatus.OK);
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
        String provinciaParam = map.get("provincia");
        // String provincia = "null".equals(provinciaParam) ? null : provinciaParam;
        String provincia = "Todas las provincias".equalsIgnoreCase(provinciaParam) || "null".equalsIgnoreCase(provinciaParam) ? null : provinciaParam;

        Integer modo = Integer.parseInt(map.get("modo"));

        List<Object[]> resultados = new ArrayList<>();

        switch (modo) {
            case 1:
                resultados = resultadoService.obtenerCantidadUsuariosPorCarrerasTotal(interes, edadMinima, edadMaxima, anoMinimo, anoMaximo);
                break;
            case 2:
                resultados = resultadoService.obtenerCantidadUsuariosPorCarrerasSanLuis(interes, edadMinima, edadMaxima, anoMinimo, anoMaximo, escuela);
                break;
            case 3:
                resultados = resultadoService.obtenerCantidadUsuariosPorCarrerasPronvicia(interes, edadMinima, edadMaxima, anoMinimo, anoMaximo, provincia);
                break;
           
        }



        // System.out.println("la edad minimaaaaaaaaaaaaaaaaaaa tiene "+ edadMinima);
        // System.out.println("la edad maximaaaaaaaaaaaaaaaaaaa tiene "+ edadMaxima);
        // System.out.println("la ano minimaaaaaaaaaaaaaaaaaaa tiene "+ anoMinimo);
        // System.out.println("la ano maximaaaaaaaaaaaaaaaaaaa tiene "+ anoMaximo);
        return resultados;
    }

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

    // Generador de excel
    @GetMapping("/resultados/excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=metricas_carreras.xlsx";
        response.setHeader(headerKey, headerValue);

        List<CareerMetricsDTO> listMetric = resultadoService.generateExcel();
        ExcelGenerator excelGenerator = new ExcelGenerator();
        Workbook workbook = excelGenerator.generarExcel(listMetric);

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    // @GetMapping("/resultados/excel")
    // public ResponseEntity<InputStreamResource> exportExcel() throws IOException {
    //     List<CareerMetricsDTO> listMetric = resultadoService.generateExcel();
    //     ExcelGenerator excelGenerator = new ExcelGenerator();
    //     Workbook workbook = excelGenerator.generarExcel(listMetric);
        
    //     ByteArrayOutputStream out = new ByteArrayOutputStream();
    //     workbook.write(out);
    //     workbook.close();
        
    //     ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        
    //     HttpHeaders headers = new HttpHeaders();
    //     headers.add("Content-Disposition", "attachment; filename=metricas_carreras.xlsx");
        
    //     return ResponseEntity.ok()
    //         .headers(headers)
    //         .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
    //         .body(new InputStreamResource(in));
    // }


    @PostMapping(value = "/resultados")
    public ResponseEntity<ResultadoDTO> postUsuarios(@RequestBody ResultadoDTO resultadoDTO) {
        System.out.println("el id Usuario tieneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee "+ resultadoDTO.getResultados().getIdUsuario());
        ResultadoDTO resultadoDTOInsertado = resultadoService.insert(resultadoDTO);
        
        // Verifica si la inserción fue exitosa
        if (resultadoDTOInsertado != null && resultadoDTOInsertado.getResultados().getId() != null) {
            // Devuelve el usuario con la ID generada y un código de estado 201 (CREATED)
            return new ResponseEntity<>(resultadoDTOInsertado, HttpStatus.CREATED);
        } else {
            // Si la inserción falla, puedes devolver un código de estado 500 (INTERNAL_SERVER_ERROR)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/resultadosU/{id}")
    public ResponseEntity<?> updateUsuarios(@PathVariable  Integer id, @RequestBody Map<String, Object> payload){

        Boolean interes = (Boolean) payload.get("interes");

        if(resultadoService.update(id, interes)){
            return new ResponseEntity<String>("Resultado actualizado", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }
    }
}
