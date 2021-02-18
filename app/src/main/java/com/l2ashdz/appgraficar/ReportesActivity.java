package com.l2ashdz.appgraficar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;

import com.l2ashdz.appgraficar.model.Resultados;
import com.l2ashdz.appgraficar.model.otros.ColorUsado;
import com.l2ashdz.appgraficar.view.TablaDinamica;
import com.l2ashdz.appgraficar.view.TablaUsoColores;

import java.util.List;

public class ReportesActivity extends AppCompatActivity {

    TableLayout tableLayout;
    Resultados results;
    List<ColorUsado> usoColores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        tableLayout = (TableLayout) findViewById(R.id.table);
        results = (Resultados) getIntent().getSerializableExtra("results");
        usoColores = results.getUsoColores();

        TablaDinamica tablaColores = new TablaUsoColores(tableLayout, getApplicationContext(), usoColores);
        tablaColores.crearTabla();
    }
}