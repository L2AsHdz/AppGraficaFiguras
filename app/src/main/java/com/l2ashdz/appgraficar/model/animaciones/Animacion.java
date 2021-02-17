package com.l2ashdz.appgraficar.model.animaciones;

import com.l2ashdz.appgraficar.model.figuras.Figura;

import java.io.Serializable;

/**
 *
 * @author asael
 */
public class Animacion implements Serializable {
    
    private Figura figura;
    private float posx;
    private float posy;
    private String tipoAnimacion;

    public Animacion(Figura figura, float posx, float posy, String tipoAnimacion) {
        this.figura = figura;
        this.posx = posx;
        this.posy = posy;
        this.tipoAnimacion = tipoAnimacion;
    }

    public Animacion() {
    }

    public Figura getFigura() {
        return figura;
    }

    public void setFigura(Figura figura) {
        this.figura = figura;
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

    public String getTipoAnimacion() {
        return tipoAnimacion;
    }

    public void setTipoAnimacion(String tipoAnimacion) {
        this.tipoAnimacion = tipoAnimacion;
    }
}
