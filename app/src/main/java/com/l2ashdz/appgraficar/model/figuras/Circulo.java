package com.l2ashdz.appgraficar.model.figuras;

/**
 *
 * @author asael
 */
public class Circulo extends Figura {
    
    private float radio;

    public Circulo(float radio, float posx, float posy, String color) {
        super(posx, posy, color);
        this.radio = radio;
    }

    public float getRadio() {
        return radio;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }
}
