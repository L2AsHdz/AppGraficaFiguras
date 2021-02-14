package analizador;

import java_cup.runtime.*;
import static analizador.sym.*;
%%

%class Lexer
%public
%cup
%unicode
%line
%column

%{
  
    private Symbol symbol(int type){
        return new Symbol(type, new Token(yyline, yycolumn));
    }

    private Symbol symbol(int type, String value){
        return new Symbol(type, new Token(yyline, yycolumn, value));
    }

%}

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
<YYINITIAL> "circulo"               {return symbol(CIRCULO);}
<YYINITIAL> "cuadrado"              {return symbol(CUADRADO);}
<YYINITIAL> "rectangulo"            {return symbol(RECTANGULO);}
<YYINITIAL> "linea"                 {return symbol(LINEA);}
<YYINITIAL> "poligono"              {return symbol(POLIGONO);}

//Colores
<YYINITIAL> "negro"                 {return symbol(NEGRO, yytext());}
<YYINITIAL> "azul"                  {return symbol(AZUL, yytext());}
<YYINITIAL> "rojo"                  {return symbol(ROJO, yytext());}
<YYINITIAL> "verde"                 {return symbol(VERDE, yytext());}
<YYINITIAL> "amarillo"              {return symbol(AMARILLO, yytext());}
<YYINITIAL> "naranja"               {return symbol(NARANJA, yytext());}
<YYINITIAL> "morado"                {return symbol(MORADO, yytext());}
<YYINITIAL> "cafe"                  {return symbol(CAFE, yytext());}

<YYINITIAL> {

    "+"                             {return symbol(SUMA);}
    "-"                             {return symbol(RESTA);}
    "*"                             {return symbol(MULT);}
    "/"                             {return symbol(DIV);}
    "("                             {return symbol(PA);}
    ")"                             {return symbol(PC);}
    ","                             {return symbol(COMA);}

    {NUMERO}                        {return symbol(NUMERO, yytext());}
    {ESPACIO}                       {/*Ignorar*/}

}

[^]                                 {System.out.println("Error encontrado: " + yytext());}