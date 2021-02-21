package com.l2ashdz.appgraficar.view;

import android.content.Context;
import android.widget.TableLayout;

import com.l2ashdz.appgraficar.model.otros.AnimacionUsada;

import java.util.List;

public class TablaUsoAnimaciones extends TablaDinamica {

    List<AnimacionUsada> usoAnimaciones;

    public TablaUsoAnimaciones(TableLayout tableLayout, Context context, List<AnimacionUsada> usoAnimaciones) {
        super(tableLayout, context);
        this.usoAnimaciones = usoAnimaciones;
    }

    @Override
    public void crearTabla() {
        crearHeader(new String[]{"Animacion", "Cant. Usos"});
        agregarFilas();
    }

    @Override
    protected void agregarFilas() {
        usoAnimaciones.forEach(a -> {
            nuevaFila();
            agregarCelda(a.getNombre());
            agregarCelda(String.valueOf(a.getCantUsos()));
            getTableLayout().addView(getTableRow());
        });
    }
}
