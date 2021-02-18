package com.l2ashdz.appgraficar.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.l2ashdz.appgraficar.model.otros.ColorUsado;

import java.util.List;

public class TablaUsoColores extends TablaDinamica {

    private List<ColorUsado> usoColores;

    public TablaUsoColores(TableLayout tableLayout, Context context, List<ColorUsado> usoColores) {
        super(tableLayout, context);
        this.usoColores = usoColores;
    }

    @Override
    public void crearTabla() {
        crearHeader(new String[]{"Color", "Uso"});
        agregarFilas();
    }

    @Override
    protected void agregarFilas() {
        usoColores.forEach(c -> {
            nuevaFila();
            agregarCelda(c.getNombreColor());
            agregarCelda(String.valueOf(c.getCantUsos()));
            getTableLayout().addView(getTableRow());
        });
    }


}
