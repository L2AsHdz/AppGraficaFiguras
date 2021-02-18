package com.l2ashdz.appgraficar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.l2ashdz.appgraficar.model.figuras.Circulo;
import com.l2ashdz.appgraficar.model.figuras.Cuadrado;
import com.l2ashdz.appgraficar.model.figuras.Figura;
import com.l2ashdz.appgraficar.model.figuras.Linea;
import com.l2ashdz.appgraficar.model.figuras.Poligono;
import com.l2ashdz.appgraficar.model.figuras.Rectangulo;

import java.util.List;

public class Lienzo extends View {

    private List<Figura> figurasAGraficar;
    private Paint redPincel;
    private Paint bluePincel;
    private Paint yellowPincel;
    private Paint brownPincel;
    private Paint blackPincel;
    private Paint orangePincel;
    private Paint greenPincel;
    private Paint purplePincel;

    public Lienzo(Context context, List<Figura> figurasAGrraficar) {
        super(context);
        this.figurasAGraficar = figurasAGrraficar;

        inicializarPinceles();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        figurasAGraficar.forEach(f -> {
            Paint pincel;

            switch(f.getColor()){
                case "ROJO":
                    pincel = this.redPincel;
                    break;
                case "AZUL":
                    pincel = this.bluePincel;
                    break;
                case "AMARILLO":
                    pincel = this.yellowPincel;
                    break;
                case "CAFE":
                    pincel = this.brownPincel;
                    break;
                case "NEGRO":
                    pincel = this.blackPincel;
                    break;
                case "NARANJA":
                    pincel = this.orangePincel;
                    break;
                case "VERDE":
                    pincel = this.greenPincel;
                    break;
                case "MORADO":
                    pincel = this.purplePincel;
                    break;
                default:
                    pincel = new Paint();
                    pincel.setColor(Color.CYAN);
                    pincel.setStyle(Paint.Style.FILL);
            }

            if (f instanceof Circulo) {
                Circulo c = (Circulo) f;
                canvas.drawCircle(c.getPosx(), c.getPosy(), c.getRadio(), pincel);
            } else if (f instanceof Cuadrado) {
                Cuadrado c = (Cuadrado) f;
                canvas.drawRect(c.getPosx(), c.getPosy(), c.getPosx()+c.getLado(), c.getPosy()+c.getLado(), pincel);
            } else if (f instanceof Rectangulo) {
                Rectangulo r = (Rectangulo) f;
                canvas.drawRect(r.getPosx(), r.getPosy(), r.getPosx()+r.getAncho(), r.getPosy()+r.getAlto(), pincel);
            } else if (f instanceof Linea) {
                Linea l = (Linea) f;
                canvas.drawLine(l.getPosx(), l.getPosy(), l.getPosx2(), l.getPosy2(), pincel);
            } else if (f instanceof Poligono) {
                Poligono p = (Poligono) f;
            }
        });
    }

    private void inicializarPinceles(){

        this.redPincel = nuevoPincel(Color.RED);
        this.greenPincel = nuevoPincel(Color.GREEN);
        this.bluePincel = nuevoPincel(Color.BLUE);
        this.yellowPincel = nuevoPincel(Color.YELLOW);
        this.blackPincel = nuevoPincel(Color.BLACK);
        this.brownPincel = nuevoPincel(Color.rgb(153, 102, 51));
        this.orangePincel = nuevoPincel(Color.rgb(255, 128, 0));
        this.purplePincel = nuevoPincel(Color.rgb(153, 0, 204));
    }

    private Paint nuevoPincel(int color){
        Paint pincel = new Paint();
        pincel.setColor(color);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setStrokeWidth(10);
        return pincel;
    }
}
