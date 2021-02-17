package com.l2ashdz.appgraficar.model.otros;

/**
 *
 * @author asael
 */
public class FiguraUsada {
    
    private String nombreFigura;
    private int cantUsos;

    public FiguraUsada(String nombreFigura) {
        this.nombreFigura = nombreFigura;
        this.cantUsos = 1;
    }

    public String getNombreFigura() {
        return nombreFigura;
    }

    public void setNombreFigura(String nombreFigura) {
        this.nombreFigura = nombreFigura;
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
