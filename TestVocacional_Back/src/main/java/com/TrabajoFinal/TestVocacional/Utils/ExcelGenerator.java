package com.TrabajoFinal.TestVocacional.Utils;

import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.TrabajoFinal.TestVocacional.DTO.CareerMetricsDTO;

public class ExcelGenerator {
    
    public Workbook generarExcel(List<CareerMetricsDTO> datos) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Datos");

        // Crear la fila de cabecera
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Email");
        headerRow.createCell(1).setCellValue("Edad");
        headerRow.createCell(2).setCellValue("Fecha");
        headerRow.createCell(3).setCellValue("Escuelas en San Luis");
        headerRow.createCell(4).setCellValue("Carrera obtenida");
        headerRow.createCell(5).setCellValue("Pregunta 1");
        headerRow.createCell(6).setCellValue("Pregunta 2");
        headerRow.createCell(7).setCellValue("Pregunta 3");
        headerRow.createCell(8).setCellValue("Pregunta 4");
        headerRow.createCell(9).setCellValue("Pregunta 5");
        headerRow.createCell(10).setCellValue("Pregunta 6");
        headerRow.createCell(11).setCellValue("Pregunta 7");
        headerRow.createCell(12).setCellValue("Pregunta 8");
        headerRow.createCell(13).setCellValue("Pregunta 9");
        headerRow.createCell(14).setCellValue("Pregunta 10");
        headerRow.createCell(15).setCellValue("Pregunta 11");
        headerRow.createCell(16).setCellValue("Pregunta 12");
        headerRow.createCell(17).setCellValue("Pregunta 13");
        headerRow.createCell(18).setCellValue("Pregunta 14");
        headerRow.createCell(19).setCellValue("Pregunta 15");
        headerRow.createCell(20).setCellValue("Pregunta 16");
        headerRow.createCell(21).setCellValue("LC");
        headerRow.createCell(22).setCellValue("PC");
        headerRow.createCell(23).setCellValue("IEF");
        headerRow.createCell(24).setCellValue("IC");
        headerRow.createCell(25).setCellValue("TW");
        headerRow.createCell(26).setCellValue("TR");

        // Agrega más celdas según los campos de tu entidad

        // Llenar el resto de filas con los datos
        int rowNum = 1;
        for (CareerMetricsDTO dato : datos) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dato.getEmail());
            row.createCell(1).setCellValue(dato.getEdad());
            row.createCell(2).setCellValue(dato.getFecha());
            row.createCell(3).setCellValue(dato.getSchool_in_san_luis());
            row.createCell(4).setCellValue(dato.getCarrera_obtenida());
            row.createCell(5).setCellValue(dato.getPregunta_1());
            row.createCell(6).setCellValue(dato.getPregunta_2());
            row.createCell(7).setCellValue(dato.getPregunta_3());
            row.createCell(8).setCellValue(dato.getPregunta_4());
            row.createCell(9).setCellValue(dato.getPregunta_5());
            row.createCell(10).setCellValue(dato.getPregunta_6());
            row.createCell(11).setCellValue(dato.getPregunta_7());
            row.createCell(12).setCellValue(dato.getPregunta_8());
            row.createCell(13).setCellValue(dato.getPregunta_9());
            row.createCell(14).setCellValue(dato.getPregunta_10());
            row.createCell(15).setCellValue(dato.getPregunta_12());
            row.createCell(16).setCellValue(dato.getPregunta_12());
            row.createCell(17).setCellValue(dato.getPregunta_13());
            row.createCell(18).setCellValue(dato.getPregunta_14());
            row.createCell(19).setCellValue(dato.getPregunta_15());
            row.createCell(20).setCellValue(dato.getPregunta_16());
            row.createCell(21).setCellValue(dato.getLc());
            row.createCell(22).setCellValue(dato.getPc());
            row.createCell(23).setCellValue(dato.getIef());
            row.createCell(24).setCellValue(dato.getIc());
            row.createCell(25).setCellValue(dato.getTw());
            row.createCell(26).setCellValue(dato.getTr());

        }

        return workbook;
    }
}
