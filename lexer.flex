package com.l2ashdz.appgraficar.analizadores.lexico;

import com.l2ashdz.appgraficar.analizadores.sintactico.sym;
import com.l2ashdz.appgraficar.model.Token;
import com.l2ashdz.appgraficar.model.errores.ErrorAnalisis;
import com.l2ashdz.appgraficar.model.errores.TipoError;
import com.l2ashdz.appgraficar.model.otros.ColorUsado;
import com.l2ashdz.appgraficar.model.otros.FiguraUsada;

import java.util.ArrayList;
import java.util.List;

import java_cup.runtime.Symbol;

import static com.l2ashdz.appgraficar.analizadores.sintactico.sym.*;
%%

%class Lexer
%public
%cup
%unicode
%line
%column

%{
    private List<ErrorAnalisis> errores = new ArrayList();
    private List<ColorUsado> usoColores = new ArrayList();
    private List<FiguraUsada> usoFiguras = new ArrayList();

    public List<ErrorAnalisis> getErrores(){
        return this.errores;
    }

    public List<ColorUsado> getUsoColores(){
        return this.usoColores;
    }

    public List<FiguraUsada> getUsoFiguras(){
        return this.usoFiguras;
    }
  
    private Symbol symbol(String name, int type){
        return new Symbol(type, new Token(name, yyline, yycolumn));
    }

    private Symbol symbol(String name, int type, String lexema){
        return new Symbol(type, new Token(name, yyline, yycolumn, lexema));
    }

    private void addLexicError(){
        String descripcion = "El simbolo no pertenece al lenguaje";
        errores.add(new ErrorAnalisis(yytext(), yyline+1, yycolumn+1, TipoError.LEXICO, descripcion));
    }

    private void aumentarColorUsado(String color){
        if(usoColores.isEmpty()){
            usoColores.add(new ColorUsado(color));
        } else {
            boolean add = true;
            
            for (ColorUsado c : usoColores) {
                if (c.getNombreColor().equals(color)) {
                    c.aumentar();
                    add = false;
                }
            }
            
            if (add) {
                usoColores.add(new ColorUsado(color));
            }
        }
    }

    private void aumentarFiguraUsada(String figura){
        if(usoFiguras.isEmpty()){
            usoFiguras.add(new FiguraUsada(figura));
        } else {
            boolean add = true;
            
            for (FiguraUsada f : usoFiguras) {
                if (f.getNombreFigura().equals(figura)) {
                    f.aumentar();
                    add = false;
                }
            }
            
            if (add) {
                usoFiguras.add(new FiguraUsada(figura));
            }
        }
    }

%}

SALTO = \n|\r|\r\n
ESPACIO = {SALTO} | [ \t\f]
NUMERO = 0|([1-9][0-9]*)(\.(0|([0-9]*[1-9])))?

%%

//Palabras reservadas
<YYINITIAL> "graficar"              {return symbol("GRAFICAR", GRAFICAR);}
<YYINITIAL> "animar"                {return symbol("ANIMAR", ANIMAR);}
<YYINITIAL> "objeto"                {return symbol("OBJETO", OBJETO);}
<YYINITIAL> "anterior"              {return symbol("ANTERIOR", ANTERIOR);}
<YYINITIAL> "curva"                 {return symbol("CURVA", CURVA);}

//Figuras
<YYINITIAL> "circulo"               {
                                        aumentarFiguraUsada("Circulo");
                                        return symbol("CIRCULO", CIRCULO);
                                    }

<YYINITIAL> "cuadrado"              {
                                        aumentarFiguraUsada("Cuadrado");
                                        return symbol("CUADRADO", CUADRADO);
                                    }
                                    
<YYINITIAL> "rectangulo"            {
                                        aumentarFiguraUsada("Rectangulo");
                                        return symbol("RECTANGULO", RECTANGULO);
                                    }

<YYINITIAL> "linea"                 {return symbol("LINEA", LINEA);}

<YYINITIAL> "poligono"              {
                                        aumentarFiguraUsada("Poligono");
                                        return symbol("POLIGONO", POLIGONO);
                                    }

//Colores
<YYINITIAL> "negro"                 {
                                        aumentarColorUsado("Negro");
                                        return symbol("NEGRO", NEGRO);
                                    }

<YYINITIAL> "azul"                  {
                                        aumentarColorUsado("Azul");
                                        return symbol("AZUL", AZUL);
                                    }

<YYINITIAL> "rojo"                  {
                                        aumentarColorUsado("Rojo");
                                        return symbol("ROJO", ROJO);
                                    }
                                    
<YYINITIAL> "verde"                 {
                                        aumentarColorUsado("Verde");
                                        return symbol("VERDE", VERDE);
                                    }

<YYINITIAL> "amarillo"              {
                                        aumentarColorUsado("Amarillo");
                                        return symbol("AMARILLO", AMARILLO);
                                    }

<YYINITIAL> "naranja"               {
                                        aumentarColorUsado("Naranja");
                                        return symbol("NARANJA", NARANJA);
                                    }

<YYINITIAL> "morado"                {
                                        aumentarColorUsado("Morado");
                                        return symbol("MORADO", MORADO);
                                    }

<YYINITIAL> "cafe"                  {
                                        aumentarColorUsado("Cafe");
                                        return symbol("CAFE", CAFE);
                                    }


<YYINITIAL> {

    "+"                             {return symbol("SUMA", SUMA);}
    "-"                             {return symbol("RESTA", RESTA);}
    "*"                             {return symbol("MULTIPLICACION", MULT);}
    "/"                             {return symbol("DIVISION", DIV);}
    "("                             {return symbol("PAREN_ABRE", PA);}
    ")"                             {return symbol("PAREN_CIERRE", PC);}
    ","                             {return symbol("COMA", COMA);}

    {NUMERO}                        {return symbol("NUMERO", NUMERO, yytext());}
    {ESPACIO}                       {/*Ignorar*/}

}

[^]                                 {addLexicError();}