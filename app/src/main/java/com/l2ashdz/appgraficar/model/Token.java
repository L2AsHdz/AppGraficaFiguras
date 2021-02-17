package com.l2ashdz.appgraficar.model;

/**
 *
 * @author asael
 */
public class Token {

    private String name;
    private int linea;
    private int columna;
    private String lexema;

    public Token(String id, int linea, int columna, String lexema) {
        this.name = id;
        this.linea = linea + 1;
        this.columna = columna + 1;
        this.lexema = lexema;
    }

    public Token(String id, int linea, int columna) {
        this.name = id;
        this.linea = linea + 1;
        this.columna = columna + 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLexema() {
        return lexema;
    }
    
    public float getNumero() {
        float numero;
        
        try {
            numero = Float.parseFloat(lexema);
        } catch (NumberFormatException e) {
            numero = -1;
            e.printStackTrace(System.out);
        }
        return numero;
    }

    public void setLexema(String valor) {
        this.lexema = valor;
    }
}
