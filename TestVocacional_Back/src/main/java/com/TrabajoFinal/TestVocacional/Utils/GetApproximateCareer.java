package com.TrabajoFinal.TestVocacional.Utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.TrabajoFinal.TestVocacional.DTO.ResultAndScoreDTO;
import com.TrabajoFinal.TestVocacional.Models.PuntajesDeResultados;
import com.TrabajoFinal.TestVocacional.Models.Recorrido;

public class GetApproximateCareer {
    private static final double PERCENT_60 = 0.60;
    private static final double PERCENT_50 = 0.50;
    private static final double PERCENT_40 = 0.40;
    private static final double PERCENT_30 = 0.30;
    private static final double PERCENT_20 = 0.20;
    private static final double PERCENT_15 = 0.15;
    private static final double PERCENT_10 = 0.10;
    

    private static double lc= 0;
    private static double pc= 0;
    private static double tw= 0;
    private static double tr= 0;
    private static double ief= 0;
    private static double ic= 0;

    private static List<Double> convertStringToInteger(List<Recorrido> recorrido, List<Double> recorridoConverted){
        int size = recorrido.size();
        double valor;
        
        for (int i = 0; i < size - 2; i++) {
            valor = (Double.parseDouble(recorrido.get(i).getValorSeleccionado())) / 10.0;
            recorridoConverted.add(valor);
        }
        return recorridoConverted;

    }

    // Método para redondear un valor Double a dos decimales
    private static Double roundTwoDecimals(Double valor) {
        if (valor == null) {
            return null;
        }
        BigDecimal bd = new BigDecimal(valor).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static String determineLargestValue() {
        // Crear un mapa con los nombres de las variables y sus valores
        Map<String, Double> variables = new HashMap<>();
        variables.put("Licenciatura en Ciencia de la Computación", lc);
        variables.put("Profesorado en Ciencias de la Computación", pc);
        // variables.put("Tecnicatura Universitaria en Web", tw);
        // variables.put("Tecnicatura Universitaria en Redes de Computadoras", tr);
        variables.put("Ingeniería en Informática", ief);
        variables.put("Ingeniería en Computación", ic);

        // Encontrar la entrada con el valor más alto
        Map.Entry<String, Double> maxEntry = null;

        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        System.out.println("maxEntry.getKey() tiene despues de la funcion :" + maxEntry.getKey());
        // Devolver el nombre de la variable con el valor más alto
        return maxEntry.getKey();
    }

    public static ResultAndScoreDTO getCarrer(List<Recorrido> recorrido) {

        System.out.println("el valor seleccionado de una recorrido es " + recorrido.get(0).getValorSeleccionado());

        List<Double> recorridoConverted = new ArrayList<Double>();
        recorridoConverted = convertStringToInteger(recorrido, recorridoConverted);
        System.out.println("el valor seleccionado de una recorrido ya convertido es " + recorridoConverted.get(0));
        String result = "";
        ResultAndScoreDTO resultAndScoreDTO = new ResultAndScoreDTO();
        PuntajesDeResultados puntajesDeResultados = new PuntajesDeResultados();
        int size = recorrido.size();

        for (int i = 0; i < size -2; i++) {
            switch (recorrido.get(i).getIdPregunta()) {
                // case 1:
                //     lc = lc + (recorridoConverted.get(i) * PERCENT_30);
                //     pc = pc + (recorridoConverted.get(i) * PERCENT_30);
                //     ief = ief + (recorridoConverted.get(i) * PERCENT_40);
                //     ic = ic + (recorridoConverted.get(i) * PERCENT_30);
                //     tw = tw + (recorridoConverted.get(i) * PERCENT_40);
                //     tr = tr + (recorridoConverted.get(i) * PERCENT_20);
                //     break;
                // case 1:
                //     pc = pc + recorridoConverted.get(i);
                //     break;
                case 1:
                    // lc = lc + recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_60);
                    // pc = pc + recorridoConverted.get(i);
                    lc = lc + (recorridoConverted.get(i) * PERCENT_40);
                    pc = pc + (recorridoConverted.get(i) * PERCENT_30);
                    ief = ief + (recorridoConverted.get(i) * PERCENT_60);
                    ic = ic + (recorridoConverted.get(i) * PERCENT_40);
                    tw = tw + (recorridoConverted.get(i) * PERCENT_60);
                    break;
                case 2:
                    // lc = lc + recorridoConverted.get(i);
                    lc = lc + (recorridoConverted.get(i) * PERCENT_15);
                    ic = ic + (recorridoConverted.get(i) * PERCENT_20);
                    tr = tr + (recorridoConverted.get(i) * PERCENT_50);
                    break;
                case 3:
                    pc = pc + recorridoConverted.get(i);
                    
                    break;
                case 4:
                    lc = lc + recorridoConverted.get(i);
                    break;
                case 5:
                    ief = ief + recorridoConverted.get(i);
                    break;
                case 6:
                    ief = ief + (recorridoConverted.get(i) * PERCENT_50);
                    tw = tw + recorridoConverted.get(i);
                    break;
                case 7:
                    ic = ic + (recorridoConverted.get(i) * PERCENT_40);
                    tr = tr + recorridoConverted.get(i);
                    break;
                case 8:
                    ic = ic + recorridoConverted.get(i);
                    break;
                case 9:
                    tw = tw + recorridoConverted.get(i);
                    break;
                case 10:
                    tr = tr + recorridoConverted.get(i);
                    break;
                case 11:
                    ief = ief + recorridoConverted.get(i);
                    break;
                case 12:
                    ic = ic + recorridoConverted.get(i);
                    break;
                case 13:
                    lc = lc + recorridoConverted.get(i);
                    break;
                case 14:
                    pc = pc + recorridoConverted.get(i);
                    break;
            }
            System.out.println("los contadores tiene en la iteracion: "+ (i+1));
            System.out.println("lc: " +lc);
            System.out.println("pc: " +pc);
            System.out.println("if: " +ief);
            System.out.println("ic: " +ic);
            System.out.println("tw: " +tw);
            System.out.println("tr: " +tr);
        }

        System.out.println("los contadores tiene:");
        System.out.println("lc: " +lc);
        System.out.println("pc: " +pc);
        System.out.println("if: " +ief);
        System.out.println("ic: " +ic);
        System.out.println("tw: " +tw);
        System.out.println("tr: " +tr);

        puntajesDeResultados.setLc(roundTwoDecimals(lc));
        puntajesDeResultados.setPc(roundTwoDecimals(pc));
        puntajesDeResultados.setIef(roundTwoDecimals(ief));
        puntajesDeResultados.setIc(roundTwoDecimals(ic));
        puntajesDeResultados.setTw(roundTwoDecimals(tw));
        puntajesDeResultados.setTr(roundTwoDecimals(tr));

        resultAndScoreDTO.setPuntajesDeResultados(puntajesDeResultados);

        if(recorrido.get(size-2).getValorSeleccionado().equals("3 años")){
            if(tw > tr){
                result = "Tecnicatura Universitaria en Web";
             }
             else{
                result = "Tecnicatura Universitaria en Redes de Computadoras";
             }
        }
        else{
            if(recorrido.get(size-2).getValorSeleccionado().equals("4 años")){
                result = "Profesorado en Ciencias de la Computación";
            }
            else{
                if(recorrido.get(size-2).getValorSeleccionado().equals("5 años") && recorrido.get(size-1).getValorSeleccionado().equals("8 am")){
                    result = determineLargestValue();
                    System.out.println("Resultado tiene despues de la funcion :" + result);
                }
                else{
                    if(recorrido.get(size-1).getValorSeleccionado().equals("6 pm")){
                        if(tw > tr){
                            result = "Tecnicatura Universitaria en Web";
                         }
                         else{
                            result = "Tecnicatura Universitaria en Redes de Computadoras";
                         }
                    }
                }
            }
            
        }

        resultAndScoreDTO.setResult(result);

        lc = 0.0;
        pc = 0.0;
        ief = 0.0;
        ic = 0.0;
        tw = 0.0;
        tr = 0.0;

        return resultAndScoreDTO;
    }
}
