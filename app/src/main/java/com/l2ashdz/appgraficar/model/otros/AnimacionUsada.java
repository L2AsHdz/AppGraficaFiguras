package com.l2ashdz.appgraficar.model.otros;

import java.io.Serializable;

public class AnimacionUsada implements Serializable {

    private String nombre;
    private int cantUsos;

    public AnimacionUsada(String nombre) {
        this.nombre = nombre;
        this.cantUsos = 1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantUsos() {
        return cantUsos;
    }

    public void setCantUsos(int cantUsos) {
        this.cantUsos = cantUsos;
    }

    public void aumentar(){
        this.cantUsos++;
    }
}
