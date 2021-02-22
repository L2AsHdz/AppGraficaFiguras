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
    private List<String> operadoresYOperandos = new ArrayList();

    public List<ErrorAnalisis> getErrores(){
        return this.errores;
    }

    public List<ColorUsado> getUsoColores(){
        return this.usoColores;
    }

    public List<FiguraUsada> getUsoFiguras(){
        return this.usoFiguras;
    }

    public List<String> getOperadoresYOperandos(){
        return this.operadoresYOperandos;
    }

    private Symbol symbol(int type){
        return new Symbol(type, new Token(yyline, yycolumn, yytext()));
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

%eofval{
    return new Symbol(EOF, new Token(-1,-1, "Fin de linea"));
%eofval}

SALTO = \n|\r|\r\n
ESPACIO = {SALTO} | [ \t\f]
NUMERO = 0|([1-9][0-9]*)(\.(0|([0-9]*[1-9])))?

%%

//Palabras reservadas
<YYINITIAL> "graficar"              {return symbol(GRAFICAR);}
<YYINITIAL> "animar"                {return symbol(ANIMAR);}
<YYINITIAL> "objeto"                {return symbol(OBJETO);}
<YYINITIAL> "anterior"              {return symbol(ANTERIOR);}
<YYINITIAL> "curva"                 {return symbol(CURVA);}

//Figuras
<YYINITIAL> "circulo"               {
                                        aumentarFiguraUsada("Circulo");
                                        return symbol(CIRCULO);
                                    }

<YYINITIAL> "cuadrado"              {
                                        aumentarFiguraUsada("Cuadrado");
                                        return symbol(CUADRADO);
                                    }
                                    
<YYINITIAL> "rectangulo"            {
                                        aumentarFiguraUsada("Rectangulo");
                                        return symbol(RECTANGULO);
                                    }

<YYINITIAL> "linea"                 {return symbol(LINEA);}

<YYINITIAL> "poligono"              {
                                        aumentarFiguraUsada("Poligono");
                                        return symbol(POLIGONO);
                                    }

//Colores
<YYINITIAL> "negro"                 {
                                        aumentarColorUsado("Negro");
                                        return symbol(NEGRO);
                                    }

<YYINITIAL> "azul"                  {
                                        aumentarColorUsado("Azul");
                                        return symbol(AZUL);
                                    }

<YYINITIAL> "rojo"                  {
                                        aumentarColorUsado("Rojo");
                                        return symbol(ROJO);
                                    }
                                    
<YYINITIAL> "verde"                 {
                                        aumentarColorUsado("Verde");
                                        return symbol(VERDE);
                                    }

<YYINITIAL> "amarillo"              {
                                        aumentarColorUsado("Amarillo");
                                        return symbol(AMARILLO);
                                    }

<YYINITIAL> "naranja"               {
                                        aumentarColorUsado("Naranja");
                                        return symbol(NARANJA);
                                    }

<YYINITIAL> "morado"                {
                                        aumentarColorUsado("Morado");
                                        return symbol(MORADO);
                                    }

<YYINITIAL> "cafe"                  {
                                        aumentarColorUsado("Cafe");
                                        return symbol(CAFE);
                                    }


<YYINITIAL> {

    "+"                             {
                                        operadoresYOperandos.add(yytext()+","+(yyline+1)+","+(yycolumn+1));
                                        return symbol(SUMA);
                                    }
                                    
    "-"                             {
                                        operadoresYOperandos.add(yytext()+","+(yyline+1)+","+(yycolumn+1));
                                        return symbol(RESTA);
                                    }
                                    
    "*"                             {
                                        operadoresYOperandos.add(yytext()+","+(yyline+1)+","+(yycolumn+1));
                                        return symbol(MULTIPLICACION);
                                    }
                                    
    "/"                             {
                                        operadoresYOperandos.add(yytext()+","+(yyline+1)+","+(yycolumn+1));
                                        return symbol(DIVISION);
                                    }
                                    
    "("                             {
                                        operadoresYOperandos.add(yytext());
                                        return symbol(PAREN_APERTURA);
                                    }
                                    
    ")"                             {
                                        operadoresYOperandos.add(yytext());
                                        return symbol(PAREN_CIERRE);
                                    }
                                    
    ","                             {return symbol(COMA);}
                                    

    {NUMERO}                        {
                                        operadoresYOperandos.add(yytext());
                                        return symbol(NUMERO);
                                    }
    {ESPACIO}                       {/*Ignorar*/}

}

[^]                                 {addLexicError();}