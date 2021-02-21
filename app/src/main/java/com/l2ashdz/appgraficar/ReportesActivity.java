package com.l2ashdz.appgraficar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TextView;

import com.l2ashdz.appgraficar.model.Resultados;
import com.l2ashdz.appgraficar.model.errores.ErrorAnalisis;
import com.l2ashdz.appgraficar.model.otros.ColorUsado;
import com.l2ashdz.appgraficar.view.TablaDinamica;
import com.l2ashdz.appgraficar.view.TablaErrores;
import com.l2ashdz.appgraficar.view.TablaUsoColores;

import java.util.List;

public class ReportesActivity extends AppCompatActivity {

    TableLayout tableLayout;
    TextView title;
    Resultados results;
    List<ColorUsado> usoColores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        tableLayout = (TableLayout) findViewById(R.id.table);
        title = findViewById(R.id.reportTitle);
        results = (Resultados) getIntent().getSerializableExtra("resultados");
        title.setText(R.string.report_errores);
        TablaDinamica tablaErrores = new TablaErrores(tableLayout, this, results.getErrores());
        tablaErrores.crearTabla();
            /*TablaDinamica tablaColores = new TablaUsoColores(tableLayout, getApplicationContext(), usoColores);
            tablaColores.crearTabla();*/
    }

}