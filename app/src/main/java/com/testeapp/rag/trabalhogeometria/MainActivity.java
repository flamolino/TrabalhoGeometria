package com.testeapp.rag.trabalhogeometria;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

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


}
