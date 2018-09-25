package com.testeapp.rag.trabalhogeometria;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends Activity {

    private GLSurfaceView superficieDesenho = null;
    private Renderizador render = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        this.superficieDesenho = new GLSurfaceView(this);
        this.render = new Renderizador(this);
        this.superficieDesenho.setRenderer(this.render);
        setContentView(this.superficieDesenho);

        this.superficieDesenho.setOnTouchListener(render);

    }
    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX ();
        float y = event.getY ();
        int action = event.getAction ();

        if (event.getAction() == MotionEvent.ACTION_UP) {

            startTime = System.currentTimeMillis();

        } else if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if(System.currentTimeMillis() - startTime <= MAX_DURATION)
            {
                action = 4;
            } else
            if(System.currentTimeMillis() - startTime <= MAX_DURATION+100)
            {
                action = 3;
            }

        }

        this.render.setCoordTouch ( x, y, action );

        return super.onTouchEvent ( event );

    }*/



}







