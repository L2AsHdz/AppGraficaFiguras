package com.l2ashdz.appgraficar.view;

import android.content.Context;
import android.widget.TableLayout;

import com.l2ashdz.appgraficar.model.errores.ErrorAnalisis;

import java.util.List;

public class TablaErrores extends TablaDinamica {

    private List<ErrorAnalisis> errores;

    public TablaErrores(TableLayout tableLayout, Context context, List<ErrorAnalisis> errores) {
        super(tableLayout, context);
        this.errores = errores;
    }

    @Override
    public void crearTabla() {
        crearHeader(new String[]{"Lexema", "Linea", "Columna", "Tipo", "Descripcion"});
        agregarFilas();
    }

    @Override
    protected void agregarFilas() {
        errores.forEach(e -> {
            nuevaFila();
            agregarCelda(e.getLexema());
            agregarCelda(String.valueOf(e.getLinea()));
            agregarCelda(String.valueOf(e.getColumna()));
            agregarCelda(e.getTipoError().toString());
            agregarCelda(e.getDescripcion());
            getTableLayout().addView(getTableRow());
        });
    }
}
