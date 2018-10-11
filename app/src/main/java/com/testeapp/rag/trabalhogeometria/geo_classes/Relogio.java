package com.testeapp.rag.trabalhogeometria.geo_classes;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import static java.lang.Thread.sleep;

public class Relogio extends Geometria {

    public static int BORDA_RELOGIO = 0;
    public static int CENTRO_RELOGIO = 1;
    public static int PONTEIRO_S = 2;
    public static int PONTEIRO_M = 3;
    public static int PONTEIRO_H = 4;


    private float[][] cores_relogio = null;
    private float[] vet_coords = null;
    private FloatBuffer buffer = null;
    private int segundo, minuto, hora;


    public Relogio(GL10 openGL, int tamanho, final int pont_hor, final int pont_min, final int pont_seg){

        super();
        this.setTamanho ( tamanho );
        this.setTipo ( 1 );
        openGL.glEnableClientState ( GL10.GL_VERTEX_ARRAY );
        this.setOpenGL ( openGL );
        this.segundo = ((60 - pont_seg) * 6) ;
        this.minuto = ((60 - pont_min) * 6);
        this.hora = ((24 - pont_hor) * 15) + 360;

        inicializaCoresRelogio();

        new Thread(new Runnable() {
            @Override
            public void run() {

                int contador_s = pont_seg;
                int contador_m = pont_min;
                int contador_h = pont_hor;

                while(true) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    contador_s++;
                    segundo = ((60 - contador_s) * 6);

                    if (contador_s > 59) {
                        contador_s = 0;

                        contador_m++;
                        minuto = ((60 - contador_m) * 6);
                        if (contador_m > 59) {
                            minuto = 0;
                            contador_m = 0;

                            contador_h++;
                            hora = ((24 - contador_h) * 15) + 360;
                            if (contador_h > 23) {
                                contador_h = 0;
                                hora = 0;
                            }
                        }
                    }
                }


            }
        }).start();


    }

    private void inicializaCoresRelogio() {

        this.cores_relogio = new float[5][4];

        this.cores_relogio[0][0] = 0;
        this.cores_relogio[0][1] = 0;
        this.cores_relogio[0][2] = 0;
        this.cores_relogio[0][3] = 1;

        this.cores_relogio[1][0] = 1;
        this.cores_relogio[1][1] = 1;
        this.cores_relogio[1][2] = 1;
        this.cores_relogio[1][3] = 1;

        this.cores_relogio[2][0] = 1;
        this.cores_relogio[2][1] = 0;
        this.cores_relogio[2][2] = 0;
        this.cores_relogio[2][3] = 1;

        this.cores_relogio[3][0] = 0;
        this.cores_relogio[3][1] = 0;
        this.cores_relogio[3][2] = 1;
        this.cores_relogio[3][3] = 1;

        this.cores_relogio[4][0] = 0;
        this.cores_relogio[4][1] = 1;
        this.cores_relogio[4][2] = 0;
        this.cores_relogio[4][3] = 1;

    }

    public void setCorRelogio(float red, float green, float blue, float alpha, int item){

        switch (item){

            case 0:
                this.cores_relogio[0][0] = red;
                this.cores_relogio[0][1] = green;
                this.cores_relogio[0][2] = blue;
                this.cores_relogio[0][3] = alpha;
                break;

            case 1:
                this.cores_relogio[1][0] = red;
                this.cores_relogio[1][1] = green;
                this.cores_relogio[1][2] = blue;
                this.cores_relogio[1][3] = alpha;
                break;

            case 2:
                this.cores_relogio[2][0] = red;
                this.cores_relogio[2][1] = green;
                this.cores_relogio[2][2] = blue;
                this.cores_relogio[2][3] = alpha;
                break;

            case 3:
                this.cores_relogio[3][0] = red;
                this.cores_relogio[3][1] = green;
                this.cores_relogio[3][2] = blue;
                this.cores_relogio[3][3] = alpha;
                break;

            case 4:
                this.cores_relogio[4][0] = red;
                this.cores_relogio[4][1] = green;
                this.cores_relogio[4][2] = blue;
                this.cores_relogio[4][3] = alpha;
                break;

        }

    }

    public void desenha(){

        this.setCor ( cores_relogio[0][0], cores_relogio[0][1], cores_relogio[0][2], cores_relogio[0][3] );
        int tamanho = this.getTamanho ();
        this.vet_coords = new float[] {
                -tamanho/2, -tamanho/2,
                -tamanho/2, tamanho/2,
                tamanho/2, -tamanho/2,
                tamanho/2, tamanho/2
        };

        for(int i = 0; i < 90; i++){

            this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
            this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
            this.getOpenGL ().glLoadIdentity ();
            this.setOpenGLConfigs (i);
            this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 4 );

        }

        this.setCor ( cores_relogio[1][0], cores_relogio[1][1], cores_relogio[1][2], cores_relogio[1][3] );
        tamanho = this.getTamanho ();
        int grau;

        for(int i = 0; i < 60; i++){

            grau = i * 6;

            if(grau == 0 || grau == 90 || grau == 180 || grau == 180+90){

                this.vet_coords = new float[] {
                        -tamanho/54, 0,
                        -tamanho/54, tamanho - (tamanho/3),
                        tamanho/54, 0,
                        tamanho/54, tamanho - (tamanho/3)
                };

            } else {

                this.vet_coords = new float[] {
                        -tamanho/34, 0,
                        -tamanho/34, tamanho - (tamanho/3),
                        tamanho/34, 0,
                        tamanho/34, tamanho - (tamanho/3)
                };

            }

            this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
            this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
            this.getOpenGL ().glLoadIdentity ();
            this.setOpenGLConfigs (grau);
            this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 4 );

        }


        tamanho = this.getTamanho();
        tamanho = (tamanho / 5);

        this.vet_coords = new float[] {
                -tamanho/40, 0,
                -tamanho/40, tamanho*3,
                tamanho/40, 0,
                tamanho/40, tamanho*3
        };

        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL().glLoadIdentity ();
        this.getOpenGL().glColor4f ( cores_relogio[2][0], cores_relogio[2][1], cores_relogio[2][2], cores_relogio[2][3]);
        this.getOpenGL().glTranslatef ( this.getPosX(), this.getPosY(), this.getTranslate_z () );
        this.getOpenGL().glRotatef(this.segundo, 0,0,4);
        this.getOpenGL().glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);


        tamanho = this.getTamanho();
        tamanho = (tamanho / 5);

        this.vet_coords = new float[] {
                -tamanho/15, 0,
                -tamanho/15, tamanho*2.5f,
                tamanho/15, 0,
                tamanho/15, tamanho*2.5f
        };

        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL().glLoadIdentity ();
        this.getOpenGL().glColor4f ( cores_relogio[3][0], cores_relogio[3][1], cores_relogio[3][2], cores_relogio[3][3]);
        this.getOpenGL().glTranslatef ( this.getPosX(), this.getPosY(), this.getTranslate_z () );
        this.getOpenGL().glRotatef(this.minuto, 0,0,4);
        this.getOpenGL().glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);


        tamanho = this.getTamanho();
        tamanho = (tamanho / 5);

        this.vet_coords = new float[] {
                -tamanho/10, 0,
                -tamanho/10, tamanho*2,
                tamanho/10, 0,
                tamanho/10, tamanho*2
        };

        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL().glLoadIdentity ();
        this.getOpenGL().glColor4f ( cores_relogio[4][0], cores_relogio[4][1], cores_relogio[4][2], cores_relogio[4][3] );
        this.getOpenGL().glTranslatef ( this.getPosX(), this.getPosY(), this.getTranslate_z () );
        this.getOpenGL().glRotatef(this.hora, 0,0,4);
        this.getOpenGL().glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);


        tamanho = this.getTamanho ();
        tamanho /= 25;

        this.vet_coords = new float[] {
                -tamanho/2, -tamanho/2,
                -tamanho/2, tamanho/2,
                tamanho/2, -tamanho/2,
                tamanho/2, tamanho/2
        };

        for(int i = 0; i < 360; i++){

            this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
            this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
            this.getOpenGL ().glLoadIdentity ();
            this.setOpenGLConfigs (i);
            this.getOpenGL().glColor4f ( cores_relogio[1][0], cores_relogio[1][1], cores_relogio[1][2], cores_relogio[1][3] );
            this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 4 );

        }


    }

}