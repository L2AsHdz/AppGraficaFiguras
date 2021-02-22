package com.l2ashdz.appgraficar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.l2ashdz.appgraficar.model.Resultados;
import com.l2ashdz.appgraficar.model.figuras.Figura;
import com.l2ashdz.appgraficar.view.Lienzo;

public class GraficasActivity extends AppCompatActivity {

    LinearLayout cLayout;
    Resultados results;
    Lienzo lienzo;
    Button btnReportes;
    Button btnAnimar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficas);

        results = (Resultados) getIntent().getSerializableExtra("resultados");

        btnAnimar = findViewById(R.id.btn_Animar);
        btnReportes = findViewById(R.id.verReportes);
        cLayout = findViewById(R.id.layout);
        lienzo = new Lienzo(this, results.getFiguras(), results.getAnimaciones(), false);
        cLayout.addView(lienzo);
        if (results.getAnimaciones().isEmpty()){
            btnAnimar.setEnabled(false);
            btnReportes.setEnabled(true);
        }
    }

    public void ejecutarAnimaciones(View view) {
        cLayout.removeView(lienzo);
        lienzo = new Lienzo(this, results.getFiguras(), results.getAnimaciones(), true);
        cLayout.addView(lienzo);

        btnReportes.setEnabled(true);
        view.setEnabled(false);
    }

    public void verReportes(View view){
        Intent intent = new Intent(this, ReportesActivity.class);
        intent.putExtra("resultados", results);
        startActivity(intent);
    }
}