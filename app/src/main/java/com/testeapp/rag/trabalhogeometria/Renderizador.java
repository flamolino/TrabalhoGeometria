package com.testeapp.rag.trabalhogeometria;

import android.opengl.GLSurfaceView;

import com.testeapp.rag.trabalhogeometria.geo_classes.Paralelogramo;
import com.testeapp.rag.trabalhogeometria.geo_classes.Quadrado;
import com.testeapp.rag.trabalhogeometria.geo_classes.Triangulo;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Renderizador implements GLSurfaceView.Renderer {

    private int largura = 0, altura = 0;
    private Quadrado quadrado = null;
    private Triangulo triangulo = null;
    private Paralelogramo paralelogramo = null;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glClearColor(1, 1, 1, 1);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        this.largura = width;
        this.altura = height;

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, width, 0, height, -1, 1);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        this.quadrado = new Quadrado(gl, 300);
        this.quadrado.setPosXY(100, 100);
        this.quadrado.setRotacao(0);
        this.quadrado.setScala(1, 1);
        this.quadrado.setCor(0, 1, 0, 1);
        this.quadrado.desenha ();

        this.triangulo = new Triangulo (gl, 300);
        this.triangulo.setPosXY(100, 400);
        this.triangulo.setRotacao(0);
        this.triangulo.setScala(1, 1);
        this.triangulo.setCor(1, 0, 0, 1);
        this.triangulo.desenha ();

        this.paralelogramo = new Paralelogramo (gl, 300);
        this.paralelogramo.setPosXY(400, 400);
        this.paralelogramo.setRotacao(0);
        this.paralelogramo.setScala(1, 1);
        this.paralelogramo.setCor(1, 0, 0, 1);
        this.paralelogramo.desenha ();

    }

}
