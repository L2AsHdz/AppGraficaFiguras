package com.l2ashdz.appgraficar.controller;

import com.l2ashdz.appgraficar.analizadores.lexico.Lexer;
import com.l2ashdz.appgraficar.analizadores.sintactico.Parser;
import com.l2ashdz.appgraficar.model.animaciones.Animacion;
import com.l2ashdz.appgraficar.model.errores.ErrorAnalisis;
import com.l2ashdz.appgraficar.model.figuras.Figura;
import com.l2ashdz.appgraficar.model.otros.ColorUsado;
import com.l2ashdz.appgraficar.model.otros.FiguraUsada;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Resultados implements Serializable {

    List<ErrorAnalisis> errores;
    List<ColorUsado> usoColores;
    List<FiguraUsada> usoFiguras;
    List<Figura> figuras;
    List<Animacion> animaciones;

    public Resultados(Lexer lexer, Parser parser) {
        errores = new ArrayList<>();
        errores.addAll(lexer.getErrores());
        errores.addAll(parser.getErrores());

        usoColores = lexer.getUsoColores();
        usoFiguras = lexer.getUsoFiguras();
        figuras = parser.getFiguras();
        animaciones = parser.getAnimaciones();
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
}
