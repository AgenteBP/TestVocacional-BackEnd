package com.TrabajoFinal.TestVocacional.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "recorrido")
public class Recorrido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer idResultado;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idResultado", referencedColumnName = "id",insertable=false, 
			updatable = false)
    private Resultados resultados;

    @Column(nullable = false)
    private Integer idPregunta;

    @Column(nullable = false)
    private String preguntaTexto;

    @Column(nullable = false, length = 50)
    private String valorSeleccionado;

    @Column(nullable = false)
    private boolean active = true;
}
