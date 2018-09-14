package com.testeapp.rag.trabalhogeometria.geo_classes;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MenuTopo extends Geometria {

    private float[] vet_coords = null;
    private FloatBuffer buffer = null;
    private int width, height;
    private Quadrado quadrado = null;
    private Triangulo triangulo = null;
    private Paralelogramo paralelogramo = null;
    private float redQ, redT, redP, greenQ, greenT, greenP, blueQ, blueT, blueP;

    public MenuTopo(GL10 openGL, int width, int height ){

        super();
        this.setTipo ( 1 );
        openGL.glEnableClientState ( GL10.GL_VERTEX_ARRAY );
        this.setOpenGL ( openGL );
        this.width = width;
        this.height = height;

    }

    public void desenha(){

        desenhaBordaPainel();
        desenhaPainel();
        desenhaQuadrado();
        desenhaTriangulo();
        desenhaParalelogramo();

    }

    private void desenhaParalelogramo() {

        int posXRel = width - 64;
        this.paralelogramo = new Paralelogramo ( this.getOpenGL (), 100 );
        this.getParalelogramo ().setPosXY ( posXRel - (this.getParalelogramo ().getTamanho () ), height - 60 );
        this.getParalelogramo ().setScala ( 1,1 );
        this.getParalelogramo ().setRotacao ( 0 );
        this.getParalelogramo ().setCor ( this.redP, this.greenP, this.blueP, 1 );
        this.getParalelogramo ().desenha ();

    }

    private void desenhaTriangulo() {

        int posXRel = (width / 100) * 64;
        this.triangulo = new Triangulo ( this.getOpenGL (), 100 );
        this.getTriangulo ().setPosXY ( posXRel - (this.getTriangulo ().getTamanho () ), height - 60 );
        this.getTriangulo ().setScala ( 1,1 );
        this.getTriangulo ().setRotacao ( 0 );
        this.getTriangulo ().setCor ( this.redT, this.greenT, this.blueT, 1 );
        this.getTriangulo ().desenha ();

    }

    private void desenhaQuadrado() {

        int posXRel = 200;
        this.quadrado = new Quadrado ( this.getOpenGL (), 100 );
        this.getQuadrado ().setPosXY ( posXRel - (this.getQuadrado ().getTamanho () ), height - 60 );
        this.getQuadrado ().setScala ( 1,1 );
        this.getQuadrado ().setRotacao ( 0 );
        this.getQuadrado ().setCor ( this.redQ, this.greenQ, this.blueQ, 1 );
        this.getQuadrado ().desenha ();

    }

    private void desenhaPainel() {

        this.setPosXY ( 1, height - 119 );
        this.setRotacao ( 0 );
        this.setScala ( 1, 1 );
        this.setCor ( 0.816f, 0.941f, 0.753f,1 );
        this.vet_coords = new float[] {
                0, 0,
                0, 118,
                width - 2, 0,
                width - 2, 118
        };
        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL ().glLoadIdentity ();
        this.setOpenGLConfigs ();
        this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 4 );

    }

    private void desenhaBordaPainel() {

        this.setPosXY ( 0, height - 120 );
        this.setRotacao ( 0 );
        this.setScala ( 1, 1 );
        this.setCor ( 0,0,0,1 );
        this.vet_coords = new float[] {
                0, 0,
                0, 120,
                width, 0,
                width, 120
        };
        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL ().glLoadIdentity ();
        this.setOpenGLConfigs ();
        this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 4 );

    }


    public Quadrado getQuadrado() {
        return quadrado;
    }

    public Triangulo getTriangulo() {
        return triangulo;
    }

    public Paralelogramo getParalelogramo() {
        return paralelogramo;
    }

    public void setCorQuadrado(float red, float green, float blue){
        this.redQ = red;
        this.greenQ = green;
        this.blueQ = blue;
    }

    public void setCorTriangulo(float red, float green, float blue){
        this.redT = red;
        this.greenT = green;
        this.blueT = blue;
    }

    public void setCorParalelogramo(float red, float green, float blue){
        this.redP = red;
        this.greenP = green;
        this.blueP = blue;
    }


}
