package com.testeapp.rag.trabalhogeometria;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.MotionEvent;

public class MainActivity extends Activity {

    private GLSurfaceView superficieDesenho = null;
    private Renderizador render = null;

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

        this.render.setCoordTouch ( x, y, event.getAction () );

        return super.onTouchEvent ( event );
    }
}
