package com.l2ashdz.appgraficar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import com.l2ashdz.appgraficar.model.Resultados;
import com.l2ashdz.appgraficar.model.errores.ErrorAnalisis;
import com.l2ashdz.appgraficar.model.otros.ColorUsado;
import com.l2ashdz.appgraficar.view.TablaDinamica;
import com.l2ashdz.appgraficar.view.TablaErrores;
import com.l2ashdz.appgraficar.view.TablaOcurrencias;
import com.l2ashdz.appgraficar.view.TablaUsoAnimaciones;
import com.l2ashdz.appgraficar.view.TablaUsoColores;
import com.l2ashdz.appgraficar.view.TablaUsoFiguras;

import java.util.List;

public class ReportesActivity extends AppCompatActivity {

    TableLayout tableLayout;
    TextView title;
    Resultados results;
    boolean isErors;
    TablaDinamica reportTable;
    Button nextReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        tableLayout = (TableLayout) findViewById(R.id.table);
        nextReport = findViewById(R.id.btn_nextReport);
        title = findViewById(R.id.reportTitle);
        results = (Resultados) getIntent().getSerializableExtra("resultados");
        isErors = getIntent().getBooleanExtra("error", false);

        if (isErors) {
            title.setText(R.string.report_errores);
            reportTable = new TablaErrores(tableLayout, this, results.getErrores());

        } else {
            tableLayout.removeAllViews();
            title.setText(R.string.report_ocurrencias);
            reportTable = new TablaOcurrencias(tableLayout, this, results.getOcurrencias());
            nextReport.setVisibility(View.VISIBLE);
        }
        reportTable.crearTabla();
    }

    //Dependiendo del reporte que se esta visualizando en pantalla, cambia la accion y el texto
    //del boton
    public void siguienteReporte(View view) {
        String titulo = title.getText().toString();

        tableLayout.removeAllViews();
        if (titulo.contains("operadores")) {
            title.setText(R.string.report_colores);
            reportTable = new TablaUsoColores(tableLayout, this, results.getUsoColores());
        } else if (titulo.contains("colores")) {
            title.setText(R.string.report_figuras);
            reportTable = new TablaUsoFiguras(tableLayout, this, results.getUsoFiguras());
        } else if (titulo.contains("figuras")) {
            title.setText(R.string.report_animaciones);
            reportTable = new TablaUsoAnimaciones(tableLayout, this, results.getUsoAnimaciones());
            nextReport.setText(R.string.btn_volverInicio);
        } else if (titulo.contains("animaciones"))
            volverAlInicio();
        if (reportTable != null) {
            reportTable.crearTabla();
        }
    }

    private void volverAlInicio() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}