package com.TrabajoFinal.TestVocacional.DTO;

import com.TrabajoFinal.TestVocacional.Models.PuntajesDeResultados;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultAndScoreDTO {
    
    public PuntajesDeResultados puntajesDeResultados;
    private String result;
}