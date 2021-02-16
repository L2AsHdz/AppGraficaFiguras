#!/bin/bash

echo Compilando Lexer...
jflex lexer.flex
echo ---------------------

echo Compilando Parser...
cup -parser Parser -symbols sym parser.cup

mv Lexer.java /home/asael/NetBeansProjects/Pruebajflexycup/src/main/java/AppGraficar/analizadores/lexico/
mv Parser.java sym.java /home/asael/NetBeansProjects/Pruebajflexycup/src/main/java/AppGraficar/analizadores/sintactico/
