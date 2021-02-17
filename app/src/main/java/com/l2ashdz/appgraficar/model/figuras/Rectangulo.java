package com.l2ashdz.appgraficar.model.figuras;

/**
 *
 * @author asael
 */
public class Rectangulo extends Figura {
    
    private float alto;
    private float ancho;

    public Rectangulo(float alto, float ancho, float posx, float posy, String color) {
        super(posx, posy, color);
        this.alto = alto;
        this.ancho = ancho;
    }

    public float getAlto() {
        return alto;
    }

    public void setAlto(float alto) {
        this.alto = alto;
    }

    public float getAncho() {
        return ancho;
    }

    public void setAncho(float ancho) {
        this.ancho = ancho;
    }
}
