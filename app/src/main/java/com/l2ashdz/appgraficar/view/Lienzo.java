package com.l2ashdz.appgraficar.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.l2ashdz.appgraficar.model.figuras.Figura;

import java.util.List;

public class Lienzo extends View {

    private List<Figura> figurasAGrraficar;
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
        this.figurasAGrraficar = figurasAGrraficar;

        inicializarPinceles();
    }

    @Override
    protected void onDraw(Canvas canvas) {

    }

    private void inicializarPinceles(){
        this.redPincel = new Paint();
        this.redPincel.setColor(Color.RED);
        this.redPincel.setStyle(Paint.Style.FILL);

        this.greenPincel = new Paint();
        this.greenPincel.setColor(Color.GREEN);
        this.greenPincel.setStyle(Paint.Style.FILL);

        this.bluePincel = new Paint();
        this.bluePincel.setColor(Color.BLUE);
        this.bluePincel.setStyle(Paint.Style.FILL);

        this.yellowPincel = new Paint();
        this.yellowPincel.setColor(Color.YELLOW);
        this.yellowPincel.setStyle(Paint.Style.FILL);

        this.brownPincel = new Paint();
        this.brownPincel.setColor(Color.rgb(153, 102, 51));
        this.brownPincel.setStyle(Paint.Style.FILL);

        this.blackPincel = new Paint();
        this.blackPincel.setColor(Color.BLACK);
        this.blackPincel.setStyle(Paint.Style.FILL);

        this.orangePincel = new Paint();
        this.orangePincel.setColor(Color.rgb(255, 128, 0));
        this.orangePincel.setStyle(Paint.Style.FILL);

        this.purplePincel = new Paint();
        this.purplePincel.setColor(Color.rgb(153, 0, 204));
        this.purplePincel.setStyle(Paint.Style.FILL);
    }
}
