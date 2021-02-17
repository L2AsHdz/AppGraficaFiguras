package com.l2ashdz.appgraficar.model.otros;

import java.io.Serializable;

/**
 *
 * @author asael
 */
public class ColorUsado implements Serializable {
    
    private String nombreColor;
    private int cantUsos;

    public ColorUsado(String nombreColor) {
        this.nombreColor = nombreColor;
        this.cantUsos = 1;
    }

    public String getNombreColor() {
        return nombreColor;
    }

    public void setNombreColor(String nombreColor) {
        this.nombreColor = nombreColor;
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
