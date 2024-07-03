package com.TrabajoFinal.TestVocacional.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.TrabajoFinal.TestVocacional.Models.Recorrido;

public class GetApproximateCareer {
    private static final double PERCENT_60 = 0.60;
    private static final double PERCENT_50 = 0.50;
    private static final double PERCENT_40 = 0.40;
    private static final double PERCENT_30 = 0.30;
    private static final double PERCENT_20 = 0.20;
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

    private static String determineLargestValue() {
        // Crear un mapa con los nombres de las variables y sus valores
        Map<String, Double> variables = new HashMap<>();
        variables.put("Licenciatura en Ciencia de la Computación", lc);
        variables.put("Profesorado en Ciencias de la Computación", pc);
        variables.put("Tecnicatura Universitaria en Web", tw);
        variables.put("Tecnicatura Universitaria en Redes de Computadoras", tr);
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

    public static String getCarrer(List<Recorrido> recorrido) {

        System.out.println("el valor seleccionado de una recorrido es " + recorrido.get(0).getValorSeleccionado());

        List<Double> recorridoConverted = new ArrayList<Double>();
        recorridoConverted = convertStringToInteger(recorrido, recorridoConverted);
        System.out.println("el valor seleccionado de una recorrido ya convertido es " + recorridoConverted.get(0));
        String result = "";
        int size = recorrido.size();

        for (int i = 0; i < size -2; i++) {
            switch (recorrido.get(i).getIdPregunta()) {
                case 1:
                    pc = pc + recorridoConverted.get(i);
                    break;
                case 2:
                    // lc = lc + recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_60);
                    pc = pc + recorridoConverted.get(i);
                    break;
                case 3:
                    lc = lc + recorridoConverted.get(i);
                    break;
                case 4:
                    lc = lc + recorridoConverted.get(i);
                    break;
                case 5:
                    lc = lc + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_60));
                    pc = pc + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_60));
                    ief = ief + recorridoConverted.get(i);
                    ic = ic + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_20));
                    tw = tw + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_20));
                    tr = tr + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_20));
                    break;
                case 6:
                    // lc = lc + recorridoConverted.get(i) ;
                    // pc = pc + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_30));
                    // ief = ief + recorridoConverted.get(i);
                    // ic = ic + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_20));
                    // tw = tw + recorridoConverted.get(i);
                    // tr = tr + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_40));
                    break;
                case 7:
                    ief = ief + recorridoConverted.get(i);
                    break;
                case 8:
                    ief = ief + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_10));
                    tw = tw + recorridoConverted.get(i);
                    break;
                case 9:
                    lc = lc + recorridoConverted.get(i);
                    pc = pc + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_40));
                    ief = ief + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_40));
                    ic = ic + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_40));
                    tw = tw + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_40));
                    break;
                case 10:
                    ief = ief + recorridoConverted.get(i);
                    ic = ic + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_50));
                    break;
                case 11:
                    lc = lc + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_60));
                    pc = pc + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_60));
                    ief = ief + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_60));
                    ic = ic + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_30));
                    // tw = tw + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_40));
                    tr = tr + recorridoConverted.get(i);
                    break;
                case 12:
                    ic = ic + recorridoConverted.get(i);
                    break;
                case 13:
                    ic = ic + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_40));
                    tr = tr + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_40));
                    break;
                case 14:
                    lc = lc + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_20));
                    ic = ic + (recorridoConverted.get(i) - (recorridoConverted.get(i) * PERCENT_20));
                    // tr = tr + recorridoConverted.get(i);
                    break;
                case 15:
                    tw = tw + recorridoConverted.get(i);
                    break;
                case 16:
                    tr = tr + recorridoConverted.get(i);
                    break;
                case 17:
                    ief = ief + recorridoConverted.get(i);
                    break;
                case 18:
                    ic = ic + recorridoConverted.get(i);
                    break;
                case 19:
                    lc = lc + recorridoConverted.get(i);
                    break;
                case 20:
                    pc = pc + recorridoConverted.get(i);
                    break;
            }
        }

        System.out.println("los contadores tiene:");
        System.out.println("lc: " +lc);
        System.out.println("pc: " +pc);
        System.out.println("if: " +ief);
        System.out.println("ic: " +ic);
        System.out.println("tw: " +tw);
        System.out.println("tr: " +tr);

        if(recorrido.get(size-2).getValorSeleccionado().equals("3 años")){
            if(tw > tr){
                result = "Tecnicatura Universitaria en Web";
             }
             else{
                result = "Tecnicatura Universitaria en Redes de Computadoras";
             }
        }
        else{
            if(recorrido.get(size-2).getValorSeleccionado().equals("4/5 años") && recorrido.get(size-1).getValorSeleccionado().equals("8 am")){
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

        return result;
    }
}
