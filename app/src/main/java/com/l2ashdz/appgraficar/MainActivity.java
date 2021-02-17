package com.l2ashdz.appgraficar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.l2ashdz.appgraficar.analizadores.lexico.Lexer;
import com.l2ashdz.appgraficar.analizadores.sintactico.Parser;
import com.l2ashdz.appgraficar.controller.Resultados;

import java.io.StringReader;

public class MainActivity extends AppCompatActivity {

    private EditText text;
    private StringReader in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.text_code);
    }

    public void limpiar(View view) {
        text.setText("");
    }

    public void analizar(View view) {

        in = new StringReader(text.getText().toString());
        Lexer lexer = new Lexer(in);
        Parser parser = new Parser(lexer);

        try {
            parser.parse();
            enviarDatos(lexer, parser);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }

    private void enviarDatos(Lexer lexer, Parser parser){
        Resultados results = new Resultados(lexer, parser);

        Intent intent = new Intent(this, ResultadoActivity.class);
        intent.putExtra("resultados", results);
        startActivity(intent);
    }
}