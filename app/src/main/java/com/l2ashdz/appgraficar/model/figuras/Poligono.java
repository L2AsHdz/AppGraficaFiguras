package com.l2ashdz.appgraficar.model.figuras;

/**
 *
 * @author asael
 */
public class Poligono extends Figura {
    
    private float alto;
    private float ancho;
    private int cantLados;

    public Poligono(float alto, float ancho, int cantLados, float posx, float posy, String color) {
        super(posx, posy, color);
        this.alto = alto;
        this.ancho = ancho;
        this.cantLados = cantLados;
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

    public int getCantLados() {
        return cantLados;
    }

    public void setCantLados(int cantLados) {
        this.cantLados = cantLados;
    }
}
