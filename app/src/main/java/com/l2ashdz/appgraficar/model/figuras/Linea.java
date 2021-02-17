package com.l2ashdz.appgraficar.model.figuras;

/**
 *
 * @author asael
 */
public class Linea extends Figura {
    
    private float posx2;
    private float posy2;

    public Linea(float posx2, float posy2, float posx, float posy, String color) {
        super(posx, posy, color);
        this.posx2 = posx2;
        this.posy2 = posy2;
    }

    public float getPosx2() {
        return posx2;
    }

    public void setPosx2(float posx2) {
        this.posx2 = posx2;
    }

    public float getPosy2() {
        return posy2;
    }

    public void setPosy2(float posy2) {
        this.posy2 = posy2;
    }
}
