package com.l2ashdz.appgraficar.model.errores;

import java.io.Serializable;

/**
 *
 * @author asael
 */
public class ErrorAnalisis implements Serializable {

    private String lexema;
    private int linea;
    private int columna;
    private TipoError tipoError;
    private String descripcion;

    public ErrorAnalisis(String lexema, int linea, int columna, TipoError tipoError, String descripcion) {
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
        this.tipoError = tipoError;
        this.descripcion = descripcion;
    }

    public ErrorAnalisis() {
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public TipoError getTipoError() {
        return tipoError;
    }

    public void setTipoError(TipoError tipoError) {
        this.tipoError = tipoError;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
