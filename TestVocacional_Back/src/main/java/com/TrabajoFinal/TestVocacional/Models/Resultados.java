package com.TrabajoFinal.TestVocacional.Models;

import java.sql.Date;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "resultados")
public class Resultados {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true, length = 120)
    private String carreraObtenida;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idUsuario", referencedColumnName = "id",insertable=false, 
			updatable = false)
    private Usuarios usuarios;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(nullable = true)
    private boolean interes;

    @Column(nullable = false)
    private boolean saveTest;

    @Column(nullable = false)
    private boolean active = true;
    
    public Resultados(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarreraObtenida() {
        return carreraObtenida;
    }

    public void setCarreraObtenida(String carreraObtenida) {
        this.carreraObtenida = carreraObtenida;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isInteres() {
        return interes;
    }

    public void setInteres(boolean interes) {
        this.interes = interes;
    }

    public boolean getSaveTest() {
        return saveTest;
    }

    public void setSaveTest(boolean saveTest) {
        this.saveTest = saveTest;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    

    

}
