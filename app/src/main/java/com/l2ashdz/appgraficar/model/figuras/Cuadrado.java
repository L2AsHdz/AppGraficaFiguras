package com.l2ashdz.appgraficar.model.figuras;

/**
 *
 * @author asael
 */
public class Cuadrado extends Figura {
    
    private float lado;

    public Cuadrado(float lado, float posx, float posy, String color) {
        super(posx, posy, color);
        this.lado = lado;
    }

    public float getLado() {
        return lado;
    }

    public void setLado(float lado) {
        this.lado = lado;
    }
}
