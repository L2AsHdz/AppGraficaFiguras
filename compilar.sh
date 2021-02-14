#!/bin/bash

echo Compilando Lexer...
jflex lexer.flex
echo ---------------------

echo Compilando Parser...
cup -parser Parser -symbols sym parser.cup

mv Lexer.java Parser.java sym.java /home/asael/NetBeansProjects/Pruebajflexycup/src/main/java/analizador/
