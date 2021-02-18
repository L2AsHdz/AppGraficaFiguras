package com.l2ashdz.appgraficar.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public abstract class TablaDinamica {

    private TableLayout tableLayout;
    private Context context;
    private TableRow tableRow;
    private TextView celda;

    public TablaDinamica(TableLayout tableLayout, Context context) {
        this.tableLayout = tableLayout;
        this.context = context;
        this.tableRow = tableRow;
        this.celda = celda;
    }

    public abstract void crearTabla();

    protected void nuevaFila() {
        tableRow = new TableRow(context);
    }

    protected void nuevaCelda() {
        celda = new TextView(context);
        celda.setGravity(Gravity.CENTER);
        celda.setTextSize(25);
    }

    protected void crearHeader(String[] headers) {
        nuevaFila();

        for (int i = 0; i < headers.length; i++) {
            nuevaCelda();
            celda.setText(headers[i]);
            celda.setTextColor(Color.WHITE);
            celda.setBackgroundColor(Color.BLACK);
            tableRow.addView(celda, parametros());
        }
        tableLayout.addView(tableRow);
    }

    protected abstract void agregarFilas();

    protected void agregarCelda(String dato) {
        nuevaCelda();
        celda.setText(dato);
        celda.setTextColor(Color.BLACK);
        celda.setBackgroundColor(Color.GRAY);
        tableRow.addView(celda, parametros());
    }

    protected TableRow.LayoutParams parametros() {
        TableRow.LayoutParams parametros = new TableRow.LayoutParams();
        parametros.setMargins(1, 1, 1, 1);
        parametros.weight = 1;
        return parametros;
    }

    public TableLayout getTableLayout() {
        return tableLayout;
    }

    public TableRow getTableRow() {
        return tableRow;
    }
}
