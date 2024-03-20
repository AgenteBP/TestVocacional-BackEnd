package com.TrabajoFinal.TestVocacional.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuarios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 40)
    private String email;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false)
    private Boolean esResidenteArg;

    @Column(nullable = true, length = 100)
    private String provincia;

    @Column(nullable = true, length = 100)
    private String schoolInSanLuis;

    @Column(nullable = true, length = 40)
    private String paisOrigen;

    @Column(nullable = false)
    private boolean active = true;

    public Usuarios(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Boolean getEsResidenteArg() {
        return esResidenteArg;
    }

    public void setEsResidenteArg(Boolean esResidenteArg) {
        this.esResidenteArg = esResidenteArg;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getSchoolInSanLuis() {
        return schoolInSanLuis;
    }

    public void setSchoolInSanLuis(String schoolInSanLuis) {
        this.schoolInSanLuis = schoolInSanLuis;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    
}
