package com.l2ashdz.appgraficar.view;

import android.content.Context;
import android.widget.TableLayout;

import com.l2ashdz.appgraficar.model.otros.FiguraUsada;

import java.util.List;

public class TablaUsoFiguras extends TablaDinamica {

    private List<FiguraUsada> usoFiguras;

    public TablaUsoFiguras(TableLayout tableLayout, Context context, List<FiguraUsada> usoFiguras) {
        super(tableLayout, context);
        this.usoFiguras = usoFiguras;
    }

    @Override
    public void crearTabla() {
        crearHeader(new String[]{"Figura", "Uso"});
        agregarFilas();
    }

    @Override
    protected void agregarFilas() {
        usoFiguras.forEach(f -> {
            nuevaFila();
            agregarCelda(f.getNombreFigura());
            agregarCelda(String.valueOf(f.getCantUsos()));
            getTableLayout().addView(getTableRow());
        });
    }
}
