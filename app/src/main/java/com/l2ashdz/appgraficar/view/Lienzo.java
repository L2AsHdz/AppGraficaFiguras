package com.l2ashdz.appgraficar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.l2ashdz.appgraficar.model.animaciones.Animacion;
import com.l2ashdz.appgraficar.model.figuras.Circulo;
import com.l2ashdz.appgraficar.model.figuras.Cuadrado;
import com.l2ashdz.appgraficar.model.figuras.Figura;
import com.l2ashdz.appgraficar.model.figuras.Linea;
import com.l2ashdz.appgraficar.model.figuras.Poligono;
import com.l2ashdz.appgraficar.model.figuras.Rectangulo;

import java.util.List;

public class Lienzo extends View {

    private List<Figura> figurasAGraficar;
    private List<Animacion> animaciones;
    private Paint redPincel;
    private Paint bluePincel;
    private Paint yellowPincel;
    private Paint brownPincel;
    private Paint blackPincel;
    private Paint orangePincel;
    private Paint greenPincel;
    private Paint purplePincel;
    private float x = 100;
    private float y = 100;
    private float x2 = 101;
    private float y2 = 300;
    private float xtemp = x;
    private float ytemp = y;
    private float distX = x2 - x;
    private float distY = y2 - y;
    private float pendiente;
    private float b;

    public Lienzo(Context context, List<Figura> figurasAGrraficar, List<Animacion> animaciones) {
        super(context);
        this.figurasAGraficar = figurasAGrraficar;
        this.animaciones = animaciones;

        inicializarPinceles();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*figurasAGraficar.forEach(f -> {
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
        });*/
        canvas.drawCircle(xtemp, ytemp, 100, nuevoPincel(Color.BLACK));
        canvas.drawLine(x, y, x2, y2, nuevoPincel(Color.RED));

        calcularPosiciones();

        if (xtemp != x2 | ytemp != y2) {
            invalidate();
        }
    }

    private void inicializarPinceles() {

        this.redPincel = nuevoPincel(Color.RED);
        this.greenPincel = nuevoPincel(Color.GREEN);
        this.bluePincel = nuevoPincel(Color.BLUE);
        this.yellowPincel = nuevoPincel(Color.YELLOW);
        this.blackPincel = nuevoPincel(Color.BLACK);
        this.brownPincel = nuevoPincel(Color.rgb(153, 102, 51));
        this.orangePincel = nuevoPincel(Color.rgb(255, 128, 0));
        this.purplePincel = nuevoPincel(Color.rgb(153, 0, 204));
    }

    private Paint nuevoPincel(int color) {
        Paint pincel = new Paint();
        pincel.setColor(color);
        pincel.setStyle(Paint.Style.FILL);
        pincel.setStrokeWidth(10);
        return pincel;
    }

    private void calcularPendiente(float x1, float y1, float x2, float y2) {
        pendiente = (y2 - y1) / (x2 - x1);
    }

    private void calcularB(float x1, float y1) {
        b = y1 - (pendiente * x1);
    }

    private float calcularY(float x) {
        return pendiente * x + b;
    }

    private float calcularX(float y) {
        return (y - b) / pendiente;
    }

    private void calcularPosiciones() {
        if (distX == 0 && distY != 0) {
            ytemp = (distY < 0) ? ytemp - 1 : ytemp + 1;
        } else if (distX != 0 && distY == 0) {
            xtemp = (distX < 0) ? xtemp - 1 : xtemp + 1;
        } else if (distX != 0 && distY != 0) {
            calcularPendiente(x, y, x2, y2);
            calcularB(x, y);
            if (Math.abs(distX) > Math.abs(distY)) {
                xtemp = (distX < 0) ? xtemp - 1 : xtemp + 1;
                ytemp = calcularY(xtemp);
            } else {
                ytemp = (distY < 0) ? ytemp - 1 : ytemp + 1;
                xtemp = calcularX(ytemp);
            }
        } else {
            //No hacer nada
        }
    }
}
