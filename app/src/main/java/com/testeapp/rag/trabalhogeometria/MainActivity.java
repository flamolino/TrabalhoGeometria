package com.testeapp.rag.trabalhogeometria;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends Activity {

    private GLSurfaceView superficieDesenho = null;
    private Renderizador render = null;
    long startTime;
    static final int MAX_DURATION = 100;
    //private int cliques = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        this.superficieDesenho = new GLSurfaceView(this);
        this.render = new Renderizador();
        this.superficieDesenho.setRenderer(this.render);
        setContentView(this.superficieDesenho);

    }

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

    }



}







