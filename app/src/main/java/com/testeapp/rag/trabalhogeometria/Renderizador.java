package com.testeapp.rag.trabalhogeometria;

import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import com.testeapp.rag.trabalhogeometria.geo_classes.Geometria;
import com.testeapp.rag.trabalhogeometria.geo_classes.MenuTopo;
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
    private ArrayList<Geometria> lst_geometria = null;
    private Geometria obj_selecionado = null, obj_clonado = null;

    private MenuTopo menu = null;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glClearColor(1, 1, 1, 1);
        this.lst_geometria = new ArrayList<>();

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        this.largura = width;
        this.altura = height;

        configuraTela(gl, width, height);
        this.menu = new MenuTopo ( gl, width, height );
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        int tipo;

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        this.menu.desenha ();

        for(int i = 0; i < this.lst_geometria.size (); i++){
            tipo = this.lst_geometria.get ( i ).getTipo ();
            switch (tipo){
                case 1:
                    this.quadrado = (Quadrado) this.lst_geometria.get ( i );
                    this.quadrado.desenha ();
                    break;
                case 2:
                    this.triangulo = (Triangulo) this.lst_geometria.get ( i );
                    this.triangulo.desenha ();
                    break;
                case 3:
                    this.paralelogramo = (Paralelogramo) this.lst_geometria.get ( i );
                    this.paralelogramo.desenha ();
                    break;
                default:
                    break;
            }
        }

    }


    private void configuraTela(GL10 gl, int width, int height) {

        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(0, width, 0, height, -1, 1);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();

    }

    public void setCoordTouch(float x, float y, int acao){

        this.t_coord_x = x;
        this.t_coord_y = this.altura - y + 37;
        this.t_acao = acao;
        moveGeoComTouch();
    }

    private void moveGeoComTouch() {

        float x, y, tam;

        switch (this.t_acao){

            case MotionEvent.ACTION_DOWN:

                if(this.obj_clonado == null) {
                    if (this.t_coord_y <= (this.altura - 120)) {

                        for (int i = 0; i < this.lst_geometria.size (); i++) {
                            x = this.lst_geometria.get ( i ).getPosX ();
                            y = this.lst_geometria.get ( i ).getPosY ();
                            tam = this.lst_geometria.get ( i ).getTamanho () / 2;

                            if(this.lst_geometria.get ( i ).getTipo () != 3) {
                                if (t_coord_x >= (x - tam) && t_coord_x < (x + tam)) {
                                    if (t_coord_y >= (y - tam) && t_coord_y < (y + tam)) {

                                        this.obj_selecionado = this.lst_geometria.get ( i );

                                    }
                                }
                            } else {
                                if (t_coord_x >= (x - tam) && t_coord_x < (x + tam*4)) {
                                    if (t_coord_y >= (y - tam) && t_coord_y < (y + tam)) {

                                        this.obj_selecionado = this.lst_geometria.get ( i );

                                    }
                                }
                            }

                        }

                    } else {

                        x = this.menu.getQuadrado ().getPosX ();
                        y = this.menu.getQuadrado ().getPosY ();
                        tam = this.menu.getQuadrado ().getTamanho () / 2;
                        if (t_coord_x >= (x - tam) && t_coord_x < (x + tam)) {
                            if (t_coord_y >= (y - tam) && t_coord_y < (y + tam)) {

                                this.menu.setCorQuadrado ( 1, 1, 1 );
                                this.menu.setCorTriangulo ( 0, 0, 0 );
                                this.menu.setCorParalelogramo ( 0, 0, 0 );
                                this.obj_clonado = this.menu.getQuadrado ();

                            }
                        }

                        if (this.obj_clonado == null) {
                            x = this.menu.getTriangulo ().getPosX ();
                            y = this.menu.getTriangulo ().getPosY ();
                            tam = this.menu.getTriangulo ().getTamanho () / 2;
                            if (t_coord_x >= (x - tam) && t_coord_x < (x + tam)) {
                                if (t_coord_y >= (y - tam) && t_coord_y < (y + tam)) {

                                    this.menu.setCorQuadrado ( 0, 0, 0 );
                                    this.menu.setCorTriangulo ( 1, 1, 1 );
                                    this.menu.setCorParalelogramo ( 0, 0, 0 );
                                    this.obj_clonado = this.menu.getTriangulo ();

                                }
                            }
                        }

                        if (this.obj_clonado == null) {
                            x = this.menu.getParalelogramo ().getPosX ();
                            y = this.menu.getParalelogramo ().getPosY ();
                            tam = this.menu.getParalelogramo ().getTamanho () / 2;
                            if (t_coord_x >= (x - tam) && t_coord_x < (x + tam*4)) {
                                if (t_coord_y >= (y - tam) && t_coord_y < (y + tam)) {

                                    this.menu.setCorQuadrado ( 0, 0, 0 );
                                    this.menu.setCorTriangulo ( 0, 0, 0 );
                                    this.menu.setCorParalelogramo ( 1, 1, 1 );
                                    this.obj_clonado = this.menu.getParalelogramo ();

                                }
                            }
                        }

                    }
                }else {

                    int new_tam = this.largura / 4;
                    this.menu.setCorQuadrado ( 0, 0, 0 );
                    this.menu.setCorTriangulo ( 0, 0, 0 );
                    this.menu.setCorParalelogramo ( 0, 0, 0 );
                    if (this.t_coord_y <= (this.altura - 120 - (this.obj_clonado.getTamanho ()/2))) {
                        this.obj_clonado.setCor ( (float) Math.random (), (float) Math.random (), (float) Math.random (), 1 );
                        this.obj_clonado.setPosXY ( this.t_coord_x, this.t_coord_y );
                        this.obj_clonado.setTamanho ( new_tam );
                        this.lst_geometria.add ( obj_clonado );
                    }
                    this.obj_clonado = null;

                }

                break;

            case MotionEvent.ACTION_MOVE:

                    if(this.obj_clonado == null) {
                        if (this.obj_selecionado != null) {
                            if (this.t_coord_y < ((this.altura - 120) - (this.obj_selecionado.getTamanho ()) / 2) && this.t_coord_y >= this.obj_selecionado.getTamanho () / 2) {
                                if (this.t_coord_x >= this.obj_selecionado.getTamanho () / 2 && this.t_coord_x < (largura - this.obj_selecionado.getTamanho () / 2)) {
                                    this.obj_selecionado.setPosXY ( t_coord_x, t_coord_y );
                                }
                            }
                        }
                    }

                break;

            case MotionEvent.ACTION_UP:

                    if(this.obj_selecionado != null){
                        this.obj_selecionado = null;
                    }

                break;

            case 3:
                for(int i = 0; i < this.lst_geometria.size (); i++){
                    x = this.lst_geometria.get ( i ).getPosX ();
                    y = this.lst_geometria.get ( i ).getPosY ();
                    tam = this.lst_geometria.get ( i ).getTamanho () / 2;

                        if (t_coord_x >= (x - tam) && t_coord_x < (x + tam)) {
                            if (t_coord_y >= (y - tam) && t_coord_y < (y + tam)) {

                                this.obj_selecionado = this.lst_geometria.get ( i );

                            }
                        }

                }
                if(this.obj_selecionado != null){
                    this.obj_selecionado.setRotacao ( this.obj_selecionado.getRotate_angulo () + 45 );
                    this.obj_selecionado = null;
                }
                break;
            default:
                break;
        }

    }


}
