#!/bin/bash

echo Compilando Lexer...
jflex lexer.flex
echo ---------------------

echo Compilando Parser...
cup -parser Parser -symbols sym parser.cup

mv Lexer.java /home/asael/AndroidStudioProjects/AppGraficar/app/src/main/java/com/l2ashdz/appgraficar/analizadores/lexico/
mv Parser.java sym.java /home/asael/AndroidStudioProjects/AppGraficar/app/src/main/java/com/l2ashdz/appgraficar/analizadores/sintactico/
