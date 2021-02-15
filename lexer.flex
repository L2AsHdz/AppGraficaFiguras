package analizador;

import java_cup.runtime.*;
import static analizador.sym.*;
import java.util.ArrayList;
import java.util.List;
%%

%class Lexer
%public
%cup
%unicode
%line
%column

%{
    private List<ErrorAnalisis> errores = new ArrayList();

    public List<ErrorAnalisis> getErrores(){
        return this.errores;
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
<YYINITIAL> "negro"                 {return symbol("NEGRO", NEGRO);}
<YYINITIAL> "azul"                  {return symbol("AZUL", AZUL);}
<YYINITIAL> "rojo"                  {return symbol("ROJO", ROJO);}
<YYINITIAL> "verde"                 {return symbol("VERDE", VERDE);}
<YYINITIAL> "amarillo"              {return symbol("AMARILLO", AMARILLO);}
<YYINITIAL> "naranja"               {return symbol("NARANJA", NARANJA);}
<YYINITIAL> "morado"                {return symbol("MORADO", MORADO);}
<YYINITIAL> "cafe"                  {return symbol("CAFE", CAFE);}

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