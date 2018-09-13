package com.testeapp.rag.trabalhogeometria;

import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

import com.testeapp.rag.trabalhogeometria.geo_classes.Geometria;
import com.testeapp.rag.trabalhogeometria.geo_classes.Paralelogramo;
import com.testeapp.rag.trabalhogeometria.geo_classes.Quadrado;
import com.testeapp.rag.trabalhogeometria.geo_classes.Triangulo;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class Renderizador implements GLSurfaceView.Renderer {

    private float t_coord_x, t_coord_y;
    private int t_acao;
    private int largura = 0, altura = 0;
    private Quadrado quadrado = null;
    private Triangulo triangulo = null;
    private Paralelogramo paralelogramo = null;
    private ArrayList<Quadrado> lst_quadrado = null;
    private ArrayList<Triangulo> lst_triangulo = null;
    private ArrayList<Paralelogramo> lst_paralelogramo = null;
    private MotionEvent touch = null;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glClearColor(1, 1, 1, 1);
        this.lst_paralelogramo = new ArrayList<>();
        this.lst_quadrado = new ArrayList<>();
        this.lst_triangulo = new ArrayList<>();

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

        this.quadrado = new Quadrado(gl, 100);
        this.quadrado.setPosXY(100, 100);
        this.quadrado.setRotacao(0);
        this.quadrado.setScala(1, 1);
        this.quadrado.setCor(0, 1, 0, 1);

        this.triangulo = new Triangulo (gl, 100);
        this.triangulo.setPosXY(100, 200);
        this.triangulo.setRotacao(0);
        this.triangulo.setScala(1, 1);
        this.triangulo.setCor(1, 0, 0, 1);

        this.paralelogramo = new Paralelogramo (gl, 100);
        this.paralelogramo.setPosXY(200, 200);
        this.paralelogramo.setRotacao(0);
        this.paralelogramo.setScala(1, 1);
        this.paralelogramo.setCor(1, 1, 0, 1);

        this.lst_quadrado.add ( this.quadrado );
        this.lst_triangulo.add ( this.triangulo );
        this.lst_paralelogramo.add ( this.paralelogramo );

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        this.quadrado.desenha ();

        this.triangulo.desenha ();

        this.paralelogramo.desenha ();


    }

    public void setCoordTouch(float x, float y, int acao){

        this.t_coord_x = x;
        this.t_coord_y = altura - y + 35;
        this.t_acao = acao;
        moveGeoComTouch();
    }

    private void moveGeoComTouch() {

        float x, y, tam;

        switch (this.t_acao){

            case MotionEvent.ACTION_DOWN:

                for(int i = 0; i < this.lst_quadrado.size (); i++){
                    x = this.lst_quadrado.get ( i ).getPosX ();
                    y = this.lst_quadrado.get ( i ).getPosY ();
                    tam = this.lst_quadrado.get ( i ).getTamanho () / 2;

                    if( t_coord_x >= (x-tam) && t_coord_x < (x+tam) ){
                        if(t_coord_y >= (y-tam) && t_coord_y < (y+tam)){

                            Log.i ("TOUCH", "Peguei o quadrado");

                        }
                    }
                }


                break;

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:

                break;

            default:
                break;
        }

    }


}
