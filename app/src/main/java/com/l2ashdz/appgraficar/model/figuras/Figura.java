package com.l2ashdz.appgraficar.model.figuras;

/**
 *
 * @author asael
 */
public class Figura {
    
    private float posx;
    private float posy;
    private String color;

    public Figura(float posx, float posy, String color) {
        this.posx = posx;
        this.posy = posy;
        this.color = color;
    }

    public float getPosx() {
        return posx;
    }

    public void setPosx(float posx) {
        this.posx = posx;
    }

    public float getPosy() {
        return posy;
    }

    public void setPosy(float posy) {
        this.posy = posy;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
