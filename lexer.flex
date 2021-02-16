package AppGraficar.analizadores.lexico;

import AppGraficar.analizadores.sintactico.sym;
import AppGraficar.model.Token;
import AppGraficar.model.errores.ErrorAnalisis;
import AppGraficar.model.errores.TipoError;
import java_cup.runtime.*;
import java.util.ArrayList;
import java.util.List;
import static AppGraficar.analizadores.sintactico.sym.*;
import AppGraficar.model.otros.ColorUsado;
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

    public List<ErrorAnalisis> getErrores(){
        return this.errores;
    }

    public List<ColorUsado> getUsoColores(){
        return this.usoColores;
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
<YYINITIAL> "circulo"               {return symbol("CIRCULO", CIRCULO);}
<YYINITIAL> "cuadrado"              {return symbol("CUADRADO", CUADRADO);}
<YYINITIAL> "rectangulo"            {return symbol("RECTANGULO", RECTANGULO);}
<YYINITIAL> "linea"                 {return symbol("LINEA", LINEA);}
<YYINITIAL> "poligono"              {return symbol("POLIGONO", POLIGONO);}

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