package com.l2ashdz.appgraficar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.l2ashdz.appgraficar.analizadores.lexico.Lexer;
import com.l2ashdz.appgraficar.analizadores.sintactico.Parser;
import com.l2ashdz.appgraficar.model.Resultados;
import com.l2ashdz.appgraficar.view.TablaDinamica;
import com.l2ashdz.appgraficar.view.TablaErrores;

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

    //Si no hay errores inicia la actividad de graficas, si hay muestra el reporte de errores
    private void enviarDatos(Lexer lexer, Parser parser){
        Resultados results = new Resultados(lexer, parser);

        if(results.getErrores().isEmpty()){
            Intent intent = new Intent(this, GraficasActivity.class);
            intent.putExtra("resultados", results);
            startActivity(intent);
        } else {
            //reporte de errores
            Toast.makeText(this, "Se encontraron errores", Toast.LENGTH_SHORT).show();
            results.getErrores().forEach(e -> System.out.println(e.getLexema()));
            Intent intent = new Intent(this, ReportesActivity.class);
            intent.putExtra("resultados", results);
            intent.putExtra("error", true);
            startActivity(intent);

        }

    }
}