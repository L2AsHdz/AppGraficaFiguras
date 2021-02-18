package com.l2ashdz.appgraficar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

import com.l2ashdz.appgraficar.model.figuras.Figura;
import com.l2ashdz.appgraficar.view.Lienzo;

import java.util.ArrayList;
import java.util.List;

public class GraficasActivity extends AppCompatActivity {

    ConstraintLayout cLayout;
    List<Figura> figuras = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficas);

        cLayout = findViewById(R.id.cLayout);
        Lienzo lienzo = new Lienzo(this, figuras);
        cLayout.addView(lienzo);
    }
}