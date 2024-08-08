package com.TrabajoFinal.TestVocacional.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "puntajes_de_resultados")
public class PuntajesDeResultados {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer idResultado;

    @Column(nullable = false)
    private Double lc;

    @Column(nullable = false)
    private Double pc;

    @Column(nullable = false)
    private Double ief;

    @Column(nullable = false)
    private Double ic;
    
    @Column(nullable = false)
    private Double tw;

    @Column(nullable = false)
    private Double tr;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idResultado", referencedColumnName = "id",insertable=false, 
			updatable = false)
    private Resultados resultados;
}
