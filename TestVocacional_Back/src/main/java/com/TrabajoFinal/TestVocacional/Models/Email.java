package com.TrabajoFinal.TestVocacional.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Email {
    
    private String destinatario;
    private String asunto;
    private String cuerpo;
    private int typeofPDF;
    
}
