package com.l2ashdz.appgraficar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.l2ashdz.appgraficar.controller.Resultados;
import com.l2ashdz.appgraficar.model.animaciones.Animacion;
import com.l2ashdz.appgraficar.model.errores.ErrorAnalisis;
import com.l2ashdz.appgraficar.model.figuras.Circulo;
import com.l2ashdz.appgraficar.model.figuras.Cuadrado;
import com.l2ashdz.appgraficar.model.figuras.Figura;
import com.l2ashdz.appgraficar.model.figuras.Linea;
import com.l2ashdz.appgraficar.model.figuras.Poligono;
import com.l2ashdz.appgraficar.model.figuras.Rectangulo;
import com.l2ashdz.appgraficar.model.otros.ColorUsado;
import com.l2ashdz.appgraficar.model.otros.FiguraUsada;

import java.util.List;

public class ResultadoActivity extends AppCompatActivity {

    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        txtResult = findViewById(R.id.txt_resultado);
        Resultados results = (Resultados) getIntent().getSerializableExtra("resultados");

        graficar(results);
    }

    private void graficar(Resultados results){

        List<ErrorAnalisis> errores = results.getErrores();
        List<ColorUsado> colores = results.getUsoColores();
        List<FiguraUsada> usoFiguras = results.getUsoFiguras();
        List<Figura> figuras = results.getFiguras();
        List<Animacion> animaciones = results.getAnimaciones();

        errores.forEach(e -> txtResult.append(e.getDescripcion()));

        colores.forEach(e -> txtResult.append("\n\n" + e.getNombreColor() + " - " + e.getCantUsos()));

        usoFiguras.forEach(f -> txtResult.append("\n\n" + f.getNombreFigura() + " - " + f.getCantUsos()));

        figuras.forEach(f -> {
            if (f instanceof Circulo) {
                Circulo c = (Circulo) f;
                txtResult.append("\n\nGraficando circulo color " + c.getColor());
                txtResult.append("\nposx: " + c.getPosx() + " - posy: " + c.getPosy() + " - radio: " + c.getRadio());
            } else if (f instanceof Cuadrado) {
                Cuadrado c = (Cuadrado) f;
                txtResult.append("\n\nGraficando cuadrado color " + c.getColor());
                txtResult.append("\nposx: " + c.getPosx() + " - posy: " + c.getPosy() + " - lado: " + c.getLado());
            } else if (f instanceof Rectangulo) {
                Rectangulo r = (Rectangulo) f;
                txtResult.append("\n\nGraficando rectangulo color " + r.getColor());
                txtResult.append("\nposx: " + r.getPosx() + " - posy: " + r.getPosy() + " - altura: " + r.getAlto() + " - ancho: " + r.getAncho());
            } else if (f instanceof Linea) {
                Linea l = (Linea) f;
                txtResult.append("\n\nGraficando linea color " + l.getColor());
                txtResult.append("\nposx: " + l.getPosx() + " - posy: " + l.getPosy() + "posx2: " + l.getPosx2() + " - posy2: " + l.getPosy2());
            } else if (f instanceof Poligono) {
                Poligono p = (Poligono) f;
                txtResult.append("\n\nGraficando poligono color " + p.getColor());
                txtResult.append("\nposx: " + p.getPosx() + " - posy: " + p.getPosy() + " - alto: " + p.getAlto() + " - ancho: " + p.getAncho() + " - cant lados: " + p.getCantLados());
            }
        });

        animaciones.forEach(a -> txtResult.append("Se animara " + a.getFigura().getClass() + " color: "+ a.getFigura().getColor() +", tipo de animacion: " + a.getTipoAnimacion()));
    }

    public void regresar(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}