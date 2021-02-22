package com.l2ashdz.appgraficar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.PI;

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
    private Path path;
    private boolean executeAnimations;
    private float xtemp = -1;
    private float ytemp = -1;
    private float pendiente;
    private float b;
    private float h;
    private float k;
    private float radio;
    private int index = 0;

    public Lienzo(Context context, List<Figura> figurasAGrraficar, List<Animacion> animaciones, boolean executeAnimations) {
        super(context);
        this.figurasAGraficar = figurasAGrraficar;
        this.animaciones = animaciones;
        this.executeAnimations = executeAnimations;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        figurasAGraficar.forEach(f -> {
            Paint pincel = setPincel(f.getColor());

            if (f instanceof Circulo) {
                Circulo c = (Circulo) f;
                canvas.drawCircle(c.getPosx(), c.getPosy(), c.getRadio(), pincel);
            } else if (f instanceof Cuadrado) {
                Cuadrado c = (Cuadrado) f;
                canvas.drawRect(c.getPosx(), c.getPosy(), c.getPosx() + c.getLado(), c.getPosy() + c.getLado(), pincel);
            } else if (f instanceof Rectangulo) {
                Rectangulo r = (Rectangulo) f;
                canvas.drawRect(r.getPosx(), r.getPosy(), r.getPosx() + r.getAncho(), r.getPosy() + r.getAlto(), pincel);
            } else if (f instanceof Linea) {
                Linea l = (Linea) f;
                canvas.drawLine(l.getPosx(), l.getPosy(), l.getPosx2(), l.getPosy2(), pincel);
            } else if (f instanceof Poligono) {
                Poligono p = (Poligono) f;
                drawPolygon(canvas, p.getPosx(), p.getPosy(), p.getAlto(), p.getAncho(), p.getCantLados(), pincel);
            }
        });

        if (executeAnimations & !animaciones.isEmpty()) {
            Figura f = animaciones.get(index).getFigura();
            Paint pincel = setPincel(f.getColor());

            if (xtemp == -1 & ytemp == -1) {
                xtemp = f.getPosx();
                ytemp = f.getPosy();
            }
            figurasAGraficar.remove(f);

            if (f instanceof Circulo) {
                Circulo c = (Circulo) f;
                canvas.drawCircle(xtemp, ytemp, c.getRadio(), pincel);
            } else if (f instanceof Cuadrado) {
                Cuadrado c = (Cuadrado) f;
                canvas.drawRect(xtemp, ytemp, xtemp + c.getLado(), ytemp + c.getLado(), pincel);
            } else if (f instanceof Rectangulo) {
                Rectangulo r = (Rectangulo) f;
                canvas.drawRect(xtemp, ytemp, xtemp + r.getAncho(), ytemp + r.getAlto(), pincel);
            } else if (f instanceof Linea) {
                Linea l = (Linea) f;
                canvas.drawLine(xtemp, ytemp, xtemp + (l.getPosx2() - l.getPosx()), ytemp + (l.getPosy2() - l.getPosy()), pincel);
            } else if (f instanceof Poligono) {
                Poligono p = (Poligono) f;
                drawPolygon(canvas, xtemp, ytemp, p.getAlto(), p.getAncho(), p.getCantLados(), pincel);
            }

            if (animaciones.get(index).getTipoAnimacion().equalsIgnoreCase("linea")) {
                calcularPosicionesLinea(f.getPosx(), f.getPosy(), animaciones.get(index).getPosx(), animaciones.get(index).getPosy());
            } else {
                calcularPosicionesCurva(f.getPosx(), f.getPosy(), animaciones.get(index).getPosx(), animaciones.get(index).getPosy());
            }


            if (xtemp != animaciones.get(index).getPosx() | ytemp != animaciones.get(index).getPosy()) {
                invalidate();
            } else {
                f.setPosx(xtemp);
                f.setPosy(ytemp);

                if (f instanceof Linea) {
                    Linea l = (Linea) f;
                    l.setPosx2(xtemp + (l.getPosx2() - l.getPosx()));
                    l.setPosy2(ytemp + (l.getPosy2() - l.getPosy()));
                }
                figurasAGraficar.add(f);

                xtemp = -1;
                ytemp = -1;
                if (index < animaciones.size() - 1) {
                    index++;
                    invalidate();
                }
            }
        }
    }

    private Paint setPincel(String color) {
        switch (color) {
            case "rojo":
            default:
                return nuevoPincel(Color.RED);
            case "azul":
                return nuevoPincel(Color.BLUE);
            case "amarillo":
                return nuevoPincel(Color.YELLOW);
            case "cafe":
                return nuevoPincel(Color.rgb(153, 102, 51));
            case "negro":
                return nuevoPincel(Color.BLACK);
            case "naranja":
                return nuevoPincel(Color.rgb(255, 128, 0));
            case "verde":
                return nuevoPincel(Color.GREEN);
            case "morado":
                return nuevoPincel(Color.rgb(153, 0, 204));
        }
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

    private void calcularPosicionesLinea(float x, float y, float x2, float y2) {
        float distX = x2 - x;
        float distY = y2 - y;

        if (distX == 0 && distY != 0) {
            ytemp = (distY < 0) ? ytemp - 1 : ytemp + 1;
        } else if (distX != 0 && distY == 0) {
            xtemp = (distX < 0) ? xtemp - 1 : xtemp + 1;
        } else if (distX != 0 && distY != 0) {
            calcularPendiente(x, y, x2, y2);
            calcularB(x, y);
            if (abs(distX) > abs(distY)) {
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

    private void calcularH(float x, float x2) {
        h = (x + x2) / 2;
    }

    private void calcularK(float y, float y2) {
        k = (y + y2) / 2;
    }

    private void calcularRadio(float x2, float y2) {
        radio = (float) sqrt(pow((x2 - h), 2) + pow((y2 - k), 2));
    }

    private float calcularYCurva(float x) {
        return (float) (sqrt(pow(radio, 2) - pow((x - h), 2)) + k);
    }

    private float calcularXCurva(float y) {
        return (float) (sqrt(pow(radio, 2) - pow((y - k), 2)) + h);
    }

    private void calcularPosicionesCurva(float x, float y, float x2, float y2) {
        float distX = x2 - x;
        float distY = y2 - y;

        calcularH(x, x2);
        calcularK(y, y2);
        calcularRadio(x2, y2);

        if (distX == 0 && distY != 0) {
            ytemp = (distY < 0) ? ytemp - 1 : ytemp + 1;
            xtemp = calcularXCurva(ytemp);
        } else if (distX != 0 && distY == 0) {
            xtemp = (distX < 0) ? xtemp - 1 : xtemp + 1;
            ytemp = calcularYCurva(xtemp);
        } else if (distX != 0 && distY != 0) {
            xtemp = (distX < 0) ? xtemp - 1 : xtemp + 1;
            ytemp = calcularYCurva(xtemp);
        } else {
            //No hacer nada
        }
    }

    private void drawPolygon(Canvas canvas, float x, float y, float alto, float ancho, int lados, Paint paint) {
        this.path = new Path();
        float radioH = alto / 2;
        float radioW = ancho / 2;
        float angulo = 360 / lados;

        for (int i = 0; i < lados; i++) {
            if (i == 0) {
                path.moveTo((float) (radioW * coseno(angulo * i)), (float) (radioH * seno(angulo * i)));
            } else {
                path.lineTo((float) (radioW * coseno(angulo * i)), (float) (radioH * seno(angulo * i)));
            }
        }
        path.close();
        canvas.save();
        canvas.translate(x, y);
        canvas.drawPath(path, paint);
        canvas.restore();
    }

    float seno(float num) {
        return (float) sin(num * PI / 180);
    }

    float coseno(float num) {
        return (float) cos(num * PI / 180);
    }
}
