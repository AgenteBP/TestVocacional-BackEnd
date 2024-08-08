package com.TrabajoFinal.TestVocacional.DTO;

import java.util.List;
import com.TrabajoFinal.TestVocacional.Models.Recorrido;
import com.TrabajoFinal.TestVocacional.Models.Resultados;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoDTO {
    
    private Resultados resultados;
    private List<Recorrido> recorrido;
}
