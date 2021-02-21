package com.l2ashdz.appgraficar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.l2ashdz.appgraficar.model.Resultados;
import com.l2ashdz.appgraficar.model.figuras.Figura;
import com.l2ashdz.appgraficar.view.Lienzo;

public class GraficasActivity extends AppCompatActivity {

    ConstraintLayout cLayout;
    Resultados results;
    Lienzo lienzo;
    Button btnReportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficas);

        results = (Resultados) getIntent().getSerializableExtra("resultados");

        cLayout = findViewById(R.id.cLayout);
        lienzo = new Lienzo(this, results.getFiguras(), results.getAnimaciones(), false);
        cLayout.addView(lienzo);
    }

    public void ejecutarAnimaciones(View view) {
        cLayout.removeView(lienzo);
        lienzo = new Lienzo(this, results.getFiguras(), results.getAnimaciones(), true);
        cLayout.addView(lienzo);

        btnReportes = findViewById(R.id.verReportes);
        btnReportes.setEnabled(true);
        view.setEnabled(false);
    }

    public void verReportes(View view){
        Intent intent = new Intent(this, ReportesActivity.class);
        intent.putExtra("resultados", results);
        startActivity(intent);
    }
}