package com.l2ashdz.appgraficar.model;

import com.l2ashdz.appgraficar.analizadores.lexico.Lexer;
import com.l2ashdz.appgraficar.analizadores.sintactico.Parser;
import com.l2ashdz.appgraficar.model.animaciones.Animacion;
import com.l2ashdz.appgraficar.model.errores.ErrorAnalisis;
import com.l2ashdz.appgraficar.model.figuras.Figura;
import com.l2ashdz.appgraficar.model.otros.AnimacionUsada;
import com.l2ashdz.appgraficar.model.otros.ColorUsado;
import com.l2ashdz.appgraficar.model.otros.FiguraUsada;
import com.l2ashdz.appgraficar.model.otros.Ocurrencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resultados implements Serializable {

    List<ErrorAnalisis> errores;
    List<ColorUsado> usoColores;
    List<FiguraUsada> usoFiguras;
    List<Figura> figuras;
    List<Animacion> animaciones;
    List<AnimacionUsada> usoAnimaciones;
    List<Ocurrencia> ocurrencias;

    public Resultados(Lexer lexer, Parser parser) {
        errores = new ArrayList<>();
        errores.addAll(lexer.getErrores());
        errores.addAll(parser.getErrores());

        usoColores = lexer.getUsoColores();
        usoFiguras = lexer.getUsoFiguras();
        usoFiguras.add(parser.getUsoLinea());
        figuras = parser.getFiguras();
        animaciones = parser.getAnimaciones();
        usoAnimaciones = parser.getUsoAnimaciones();

        this.ocurrencias = new ArrayList<>();
        obtenerOcurrencias(lexer);
    }

    public List<ErrorAnalisis> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorAnalisis> errores) {
        this.errores = errores;
    }

    public List<ColorUsado> getUsoColores() {
        return usoColores;
    }

    public void setUsoColores(List<ColorUsado> usoColores) {
        this.usoColores = usoColores;
    }

    public List<FiguraUsada> getUsoFiguras() {
        return usoFiguras;
    }

    public void setUsoFiguras(List<FiguraUsada> usoFiguras) {
        this.usoFiguras = usoFiguras;
    }

    public List<Figura> getFiguras() {
        return figuras;
    }

    public void setFiguras(List<Figura> figuras) {
        this.figuras = figuras;
    }

    public List<Animacion> getAnimaciones() {
        return animaciones;
    }

    public void setAnimaciones(List<Animacion> animaciones) {
        this.animaciones = animaciones;
    }

    public List<AnimacionUsada> getUsoAnimaciones() {
        return usoAnimaciones;
    }

    public void setUsoAnimaciones(List<AnimacionUsada> usoAnimaciones) {
        this.usoAnimaciones = usoAnimaciones;
    }

    public List<Ocurrencia> getOcurrencias() {
        return ocurrencias;
    }

    public void setOcurrencias(List<Ocurrencia> ocurrencias) {
        this.ocurrencias = ocurrencias;
    }

    private void obtenerOcurrencias(Lexer lex) {
        List<String> op = lex.getOperadoresYOperandos();

        for (int i = 0; i < op.size(); i++) {
            if (op.get(i).contains("+") | op.get(i).contains("-") | op.get(i).contains("*") | op.get(i).contains("/")) {
                String[] split = op.get(i).split(",");
                String ocurrencia = op.get(i - 1) + " " + split[0] + " " + op.get(i + 1);
                this.ocurrencias.add(new Ocurrencia(split[0], split[1], split[2], ocurrencia));
            }
        }
    }
}
