package com.l2ashdz.appgraficar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

import com.l2ashdz.appgraficar.model.Resultados;
import com.l2ashdz.appgraficar.model.figuras.Figura;
import com.l2ashdz.appgraficar.view.Lienzo;

public class GraficasActivity extends AppCompatActivity {

    ConstraintLayout cLayout;
    Resultados results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficas);

        results = (Resultados) getIntent().getSerializableExtra("resultados");

        cLayout = findViewById(R.id.cLayout);
        Lienzo lienzo = new Lienzo(this, results.getFiguras());
        cLayout.addView(lienzo);
    }
}