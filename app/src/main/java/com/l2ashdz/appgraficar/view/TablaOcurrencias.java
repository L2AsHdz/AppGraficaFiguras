package com.l2ashdz.appgraficar.view;

import android.content.Context;
import android.widget.TableLayout;

import com.l2ashdz.appgraficar.model.otros.Ocurrencia;

import java.util.List;

public class TablaOcurrencias extends TablaDinamica {

    private List<Ocurrencia> ocurrencias;

    public TablaOcurrencias(TableLayout tableLayout, Context context, List<Ocurrencia> ocurrencias) {
        super(tableLayout, context);
        this.ocurrencias = ocurrencias;
    }

    @Override
    public void crearTabla() {
        crearHeader(new String[]{"Operador", "Linea", "Columna", "Ocurrencia"});
        agregarFilas();
    }

    @Override
    protected void agregarFilas() {
        ocurrencias.forEach(o -> {
            nuevaFila();
            agregarCelda(o.getOperador());
            agregarCelda(String.valueOf(o.getLinea()));
            agregarCelda(String.valueOf(o.getColumna()));
            agregarCelda(o.getOcurrencia());
            getTableLayout().addView(getTableRow());
        });
    }
}
