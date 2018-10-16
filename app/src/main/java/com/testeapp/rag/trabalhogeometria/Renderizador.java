package com.testeapp.rag.trabalhogeometria;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import com.testeapp.rag.trabalhogeometria.geo_classes.GLText;
import com.testeapp.rag.trabalhogeometria.geo_classes.Geometria;
import com.testeapp.rag.trabalhogeometria.geo_classes.GeraBuffer;
import com.testeapp.rag.trabalhogeometria.geo_classes.MenuTopo;
import com.testeapp.rag.trabalhogeometria.geo_classes.Paralelogramo;
import com.testeapp.rag.trabalhogeometria.geo_classes.Quadrado;
import com.testeapp.rag.trabalhogeometria.geo_classes.Relogio;
import com.testeapp.rag.trabalhogeometria.geo_classes.Triangulo;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.testeapp.rag.trabalhogeometria.geo_classes.GeraBuffer.generateBuffer;

public class Renderizador implements GLSurfaceView.Renderer, View.OnTouchListener, SensorEventListener {

    private float t_coord_x, t_coord_y;
    private int t_acao;
    private int largura = 0, altura = 0;
    private Quadrado quadrado = null;
    private Triangulo triangulo = null;
    private Paralelogramo paralelogramo = null;
    private ArrayList<Geometria> lst_geometria = null;
    private Geometria obj_selecionado = null, obj_clonado = null;
    private MenuTopo menu = null;
    private GL10 openGLVai = null;
    private long startTime;
    private static final int MAX_DURATION = 100;
    private SensorManager sensorManager = null;
    private Sensor accelerometer = null;
    private float bestOfX, bestOfY, bestOfZ;
    private Context context = null;
    private float angulo = 0;
    private int anguloReverso = 0;
    private Relogio relogio = null;
    private GLText texto = null;
    private int codTextura = 0;
    private FloatBuffer buffer = null;
    private float[] coordgit = null;

    public Renderizador(Context con){

        this.context = con;

        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        this.accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.sensorManager.registerListener(this, this.accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        listSensors();

    }

    private void listSensors() {

        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor s: deviceSensors){
            Log.d("Sensors", s.getName());
        }

    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

            bestOfX = x;

            bestOfY = y;

            bestOfZ = z;

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        gl.glClearColor(0, 0, 0, 1);
        this.lst_geometria = new ArrayList<>();
        this.openGLVai = gl;

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        this.largura = width;
        this.altura = height;
        int tamanho = width / 3;

        configuraTela(gl, width, height);
        this.menu = new MenuTopo ( gl, width, height );

        Quadrado quad = new Quadrado(gl, tamanho);
        quad.randomizaCor();
        quad.setPosXY(width/2, height/2);
        lst_geometria.add(quad);

        gl.glEnableClientState ( GL10.GL_VERTEX_ARRAY );

        float[] vet_coord = new float[] {
                -tamanho/2, 0,
                -tamanho/2, tamanho*3,
                tamanho/2, 0,
                tamanho/2, tamanho*3
        };

        buffer = generateBuffer(vet_coord);
        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, buffer);
        gl.glLoadIdentity ();

        this.t_coord_x = width/2;
        this.t_coord_y = height/2;

        this.relogio = new Relogio(gl, tamanho, 23, 30, 0);
        this.relogio.setPosXY(this.t_coord_x, this.t_coord_y);
        this.relogio.setCorRelogio ( 1.0f, 0.85f, 0, 1, Relogio.CENTRO_RELOGIO );

        this.texto = new GLText ( gl );
        this.texto.setTextPosition ( 10, 50, 0 );
        this.texto.setTextColor ( 1, 0, 0, 1 );
        this.texto.setTextBackgroundColor(1, 1, 1, 0);
        this.texto.setText ( "frfango" );
        this.texto.setTextSize ( 6);


        //EXEMPLO DE UM VETOR DE COORDENADAS DE TEXTURA NO SISTEMA UV OU ST.
        float[] coordgit = {
                0,1,
                0,0,
                1,1,
                1,0};

        //RECORTE DE TEXTURA
        float[] coordTexura = { 0,0.5f,
                0,0,
                0.5f,0.5f,
                0.5f,0};

        //ESPELHAMENTO DE TEXTURA
        float[] coordTexura2 = { 0, 0,
                0,1,
                1,0,
                1,1};

        //HABILITANTO A MÁQUINA OPENGL PARA O USO DE TEXTURAS
        gl.glEnable(GL10.GL_TEXTURE_2D);

        //HABILITA A MAQUINA PARA UTILIZAR UM VETOR DE COORDENADAS DE TEX
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        //CRIANDO O VETOR DE COORDENADAS EM JAVA;
        float[] vetCoordText = {0,1,
                0,0,
                1,1,
                1,0};


        gl.glLoadIdentity ();

        //CRIANDO O FLOAT BUFFER A PARTIR DO VETOR JAVA
        FloatBuffer texCoords = generateBuffer(coordgit);

        //REGISTRA AS COORDENADAS DE TEXTURA NA MÁQUINA OPENGL
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, texCoords);

        codTextura = carregaTextura(gl, R.mipmap.luigi_face);

        //ASSINAR A TEXTURA QUE A OPENGL VAI UTILIZAR NO DESENHO DA PRIMITIVA
        gl.glBindTexture(GL10.GL_TEXTURE_2D, codTextura);

    }

    private int carregaTextura(GL10 opengl, int codTextura) {

        //CARREGA A IMAGEM NA MEMORIA RAAAMMMM
        Bitmap imagem = BitmapFactory.decodeResource(this.context.getResources(), codTextura);

        //DEFINE UM ARRAY PARA ARMAZ. DOS IDS DE TEXTURA (APENAS 1 POSICAO)
        int[] idTextura = new int[1];

        //GERA AS AREAS NA GPU E CRIA UM ID PARA CADA UMA
        opengl.glGenTextures(1, idTextura, 0);

        //DIZER PARA A MAQUINA QUAL DAS AREAS CRIADAS NA VRAM EU QUERO TRABALHAR
        opengl.glBindTexture(GL10.GL_TEXTURE_2D, idTextura[0]);

        //COPIA A IMAGEM DA RAM PARA A VRAM
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, imagem,0);

        //CONFIGURA OS ALGORITMOS QUE SERAO UTILIZADOS PARA RECALCULAR A IMAGEM EM CASO DE ESCLA PARA MAIS OU MENOS (MIN E MAG)
        opengl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);

        opengl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);

        //APONTA A VRAM OPENGL PARA O NADA (CODIGO ZERO)
        opengl.glBindTexture(GL10.GL_TEXTURE_2D, 0);

        //LIBERA A MEMORIA RAM
        imagem.recycle();

        return idTextura[0];
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        int tipo;

        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        this.texto.setTextPosition(this.t_coord_x, this.t_coord_y + 100, 1);
        this.texto.drawText ();


/*
        gl.glLoadIdentity ();
        gl.glTranslatef (this.t_coord_x, this.t_coord_y, 1 );
        gl.glScalef(1, 0.35f, 1);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);



        //this.relogio.setPosXY(this.t_coord_x, this.t_coord_y);
        //this.relogio.desenha();


        //this.menu.desenha ();


        for(int i = 0; i < this.lst_geometria.size (); i++){
            tipo = this.lst_geometria.get ( i ).getTipo ();
            switch (tipo){
                case 1:
                    this.quadrado = (Quadrado) this.lst_geometria.get ( i );
                   // this.quadrado.setPosXY(this.quadrado.getPosX() + bestOfX, this.quadrado.getPosY() + bestOfY);
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



        gl.glLoadIdentity();

        gl.glColor4f(1, 0, 0, 1);
        gl.glTranslatef(t_coord_x, t_coord_y, 1);
        gl.glRotatef(this.angulo, 0,0,4);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);


        //empilha uma transformação geometrica
        gl.glPushMatrix();

            gl.glColor4f(1, 1, 0, 1);
            gl.glTranslatef(0, 0, 0);
            gl.glRotatef((this.angulo*2)*-1, 0,0,4);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);

                gl.glPushMatrix();

                gl.glColor4f(0.9f, 0.9f, 0.9f, 1);
                gl.glTranslatef(0, 200, 0);
                gl.glRotatef(this.angulo*4, 0,0,4);
                gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);


                gl.glPopMatrix();

        gl.glPopMatrix();

        this.angulo += 2;

*/

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
        this.t_coord_y = this.altura - y;
        this.t_acao = acao;
        moveGeoComTouch();

    }

    private void moveGeoComTouch() {

        float x, y, tam, escala, angulo;

        switch (this.t_acao){

            case MotionEvent.ACTION_DOWN:

                Geometria.getColorPixel ( t_coord_x, t_coord_y, this.openGLVai );

                if(this.obj_clonado == null) {
                    if (this.t_coord_y <= (this.altura - 120)) {

                        for (int i = 0; i < this.lst_geometria.size (); i++) {
                            x = this.lst_geometria.get ( i ).getPosX ();
                            y = this.lst_geometria.get ( i ).getPosY ();
                            tam = this.lst_geometria.get ( i ).getTamanho () / 2 * this.lst_geometria.get ( i ).getScale_x ();


                            if(this.lst_geometria.get ( i ).getTipo () != 3) {

                                angulo = this.lst_geometria.get ( i ).getRotate_angulo () % 360;

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

                    int new_tam = (int) Math.floor ( this.largura / 3.5f );
                    this.menu.setCorQuadrado ( 0, 0, 0 );
                    this.menu.setCorTriangulo ( 0, 0, 0 );
                    this.menu.setCorParalelogramo ( 0, 0, 0 );
                    if (this.t_coord_y <= (this.altura - 120 - (this.obj_clonado.getTamanho ()/2))) {
                        this.obj_clonado.setCor ( (float) Math.random (), (float) Math.random (), (float) Math.random (), 1 );
                        this.obj_clonado.setPosXY ( this.t_coord_x, this.t_coord_y );
                        this.obj_clonado.setTamanho ( new_tam );
                        this.obj_clonado.setScala ( 0.5f, 0.5f);
                        this.lst_geometria.add ( obj_clonado );
                    }
                    this.obj_clonado = null;

                }

                break;

            case MotionEvent.ACTION_MOVE:

                if(this.obj_clonado == null) {
                    if (this.obj_selecionado != null) {
                        if (this.t_coord_y < ((this.altura - 120) - (this.obj_selecionado.getTamanho ()) / 2) && this.t_coord_y >= -this.obj_selecionado.getTamanho ()) {
                            if (this.t_coord_x >= -this.obj_selecionado.getTamanho () && this.t_coord_x < (largura - -this.obj_selecionado.getTamanho ())) {

                                this.obj_selecionado.setPosXY ( t_coord_x, t_coord_y);

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
                    this.obj_selecionado.setRotacao ( this.obj_selecionado.getRotate_angulo () + 22.5f );
                    this.obj_selecionado = null;
                }
                break;

            case 4:
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
                    if(this.obj_selecionado.getScale_x () == 0.5f){
                        this.obj_selecionado.setScala ( (1.42f)/2, (1.42f)/2);
                    } else if(this.obj_selecionado.getScale_x () == (1.42f)/2){
                        this.obj_selecionado.setScala ( (1.42f + (1.42f/2.5f))/2, (1.42f + (1.42f/2.5f))/2);
                    } else{
                        this.obj_selecionado.setScala ( 0.5f, 0.5f );
                    }
                    this.obj_selecionado = null;
                }
                break;

            default:
                break;
        }

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        float x = motionEvent.getX ();
        float y = motionEvent.getY ();
        int action = motionEvent.getAction ();

        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {

            startTime = System.currentTimeMillis();

        } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

            if(System.currentTimeMillis() - startTime <= MAX_DURATION)
            {
                action = 4;
            } else
            if(System.currentTimeMillis() - startTime <= MAX_DURATION+100)
            {
                action = 3;
            }

        }

        setCoordTouch(x, y, action);




        return true;
    }



}
























