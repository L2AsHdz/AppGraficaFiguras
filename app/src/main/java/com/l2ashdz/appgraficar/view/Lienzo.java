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
import static java.lang.Math.atan;
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

    private final List<Figura> figurasAGraficar;
    private final List<Animacion> animaciones;
    private final boolean executeAnimations;
    private float xtemp = -1;
    private float ytemp = -1;
    private float pendiente;
    private float b;
    private float h;
    private float k;
    private float radio = -1;
    private float theta = -1;
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

        //Se recorre la lista graficando las figuras obtenidas del texto de entrada
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

        //Si se detectaron animaciones las ejecuta una seguida de otra
        if (executeAnimations & !animaciones.isEmpty()) {
            Figura f = animaciones.get(index).getFigura();
            Paint pincel = setPincel(f.getColor());

            float x2 = animaciones.get(index).getPosx();
            float y2 = animaciones.get(index).getPosy();

            //Se establecen las coordenadas temporales
            if (xtemp == -1 & ytemp == -1) {
                xtemp = f.getPosx();
                ytemp = f.getPosy();
            }
            //Se remueve la figura a animar de las figuras a graficar
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
                calcularPosicionesLinea(f.getPosx(), f.getPosy(), x2, y2);
            } else {
                calcularPosicionesCurva(f.getPosx(), f.getPosy(), x2, y2);
            }


            //Redibuja mientras la diferencia entre la coordenada final y la coordenada temporal sea
            //mayor a 5, de lo contrario agrega la figura con su nueva posicion a la lista de
            //figuras a graficar
            if (abs(xtemp-x2) > 5 | abs(ytemp-y2) > 5) {
                invalidate();
            } else {
                f.setPosx(x2);
                f.setPosy(y2);

                if (f instanceof Linea) {
                    Linea l = (Linea) f;
                    l.setPosx2(xtemp + (l.getPosx2() - l.getPosx()));
                    l.setPosy2(ytemp + (l.getPosy2() - l.getPosy()));
                }
                figurasAGraficar.add(f);

                xtemp = -1;
                ytemp = -1;
                radio = -1;
                theta = -1;
                if (index < animaciones.size() - 1) {
                    index++;
                    invalidate();
                }
            }
        }
    }

    //Establece el pincel dependiendo del color indicado
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

    //Calcula la pendiente de la recta, se utiliza para la animacion tipo linea
    private void calcularPendiente(float x1, float y1, float x2, float y2) {
        pendiente = (y2 - y1) / (x2 - x1);
    }

    //calcula b constante de la ecuacion punto pendiente
    private void calcularB(float x1, float y1) {
        b = y1 - (pendiente * x1);
    }

    //Calcula la posicion en Y dependiendo de la posicion actual de X
    private float calcularY(float x) {
        return pendiente * x + b;
    }

    //Calcula la posicion en X dependiendo de la posicion actual de Y
    private float calcularX(float y) {
        return (y - b) / pendiente;
    }

    //Calcula las posiciones temporales para la acnimacion de tipo linea
    private void calcularPosicionesLinea(float x, float y, float x2, float y2) {
        float distX = x2 - x;
        float distY = y2 - y;
        int NO_PIXELS = 5;

        if (distX == 0 && distY != 0) {
            ytemp = (distY < 0) ? ytemp - NO_PIXELS : ytemp + NO_PIXELS;
        } else if (distX != 0 && distY == 0) {
            xtemp = (distX < 0) ? xtemp - NO_PIXELS : xtemp + NO_PIXELS;
        } else if (distX != 0 && distY != 0) {
            calcularPendiente(x, y, x2, y2);
            calcularB(x, y);
            if (abs(distX) > abs(distY)) {
                xtemp = (distX < 0) ? xtemp - NO_PIXELS : xtemp + NO_PIXELS;
                ytemp = calcularY(xtemp);
            } else {
                ytemp = (distY < 0) ? ytemp - NO_PIXELS : ytemp + NO_PIXELS;
                xtemp = calcularX(ytemp);
            }
        }
    }

    //Calcula la coordenada x del centro de la circunferencia
    private void calcularH(float x, float x2) {
        h = (x + x2) / 2;
    }

    //Calcula la coordenada y del centro de la circunferencia
    private void calcularK(float y, float y2) {
        k = (y + y2) / 2;
    }

    //Calcula el radio de la circunferencia entre los puntos indicados
    private void calcularRadio(float x, float y, float x2, float y2) {
        radio = (float) sqrt(pow((x2 - x), 2) + pow((y2 - y), 2)) / 2;
    }

    //Calcula el angulo de inicio para la animacion tipo curva
    private void calcularAngulo(float x, float y) {
        x = x - h;
        y = y - k;

        if (x > 0 && y >= 0) {
            theta = arctan(x, y);
        } else if (x == 0 && y > 0) {
            theta = 90;
        } else if (x < 0) {
            theta = arctan(x, y) + 180;
        } else if (x == 0 && y < 0) {
            theta = 270;
        } else if (x > 0 && y < 0) {
            theta = arctan(x, y) + 360;
        }
    }

    //Calcula las posiciones temporales para la animacion tipo curva
    private void calcularPosicionesCurva(float x, float y, float x2, float y2) {
        calcularH(x, x2);
        calcularK(y, y2);

        if (radio == -1) {
            calcularRadio(x, y, x2, y2);
        }
        if (theta == -1) {
            calcularAngulo(x, y);
        }

        theta++;
        xtemp = h + (radio * coseno(theta));
        ytemp = k + (radio * seno(theta));
    }

    //Dibuja un poligono de n lados con las caracteristicas especificadas
    private void drawPolygon(Canvas canvas, float x, float y, float alto, float ancho, int lados, Paint paint) {
        Path path = new Path();
        float radioH = alto / 2;
        float radioW = ancho / 2;
        int angulo = 360 / lados;

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

    private float seno(float angulo) {
        return (float) sin(angulo * PI / 180);
    }

    private float coseno(float angulo) {
        return (float) cos(angulo * PI / 180);
    }

    private float arctan(float x, float y) {
        return (float) (atan(y / x) * (180 / PI));
    }
}
