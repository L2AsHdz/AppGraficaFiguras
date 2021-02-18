package com.l2ashdz.appgraficar.model.otros;

public class Ocurrencia {

    private String operador;
    private int linea;
    private int columna;
    private String ocurrencia;

    public Ocurrencia(String operador, int linea, int columna, String ocurrencia) {
        this.operador = operador;
        this.linea = linea;
        this.columna = columna;
        this.ocurrencia = ocurrencia;
    }

    public Ocurrencia() {
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
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

    public String getOcurrencia() {
        return ocurrencia;
    }

    public void setOcurrencia(String ocurrencia) {
        this.ocurrencia = ocurrencia;
    }
}
