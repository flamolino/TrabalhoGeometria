package com.testeapp.rag.trabalhogeometria.geo_classes;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import static java.lang.Thread.sleep;

public class Relogio extends Geometria {

    private float[] vet_coords = null;
    private FloatBuffer buffer = null;
    private float red, green, blue;
    private int segundo, minuto, hora;

    public Relogio(GL10 openGL, int tamanho, final int pont_hor, final int pont_min, final int pont_seg){

        super();
        this.setTamanho ( tamanho );
        this.setTipo ( 1 );
        openGL.glEnableClientState ( GL10.GL_VERTEX_ARRAY );
        this.setOpenGL ( openGL );
        this.segundo = ((60 - pont_seg) * 6) - 1;
        this.minuto = ((60 - pont_min) * 6);
        this.hora = ((24 - pont_hor) * 15) + 360;

        new Thread(new Runnable() {
            @Override
            public void run() {

                int contador_s = pont_seg-1;
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

    public void desenha(){

        this.red = this.getColor_red ();
        this.green = this.getColor_green ();
        this.blue = this.getColor_blue ();
        this.setCor ( 0,0,0,1 );
        int tamanho = this.getTamanho ();

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
            this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 4 );

        }

        this.setCor ( 1f,1f,1,1 );
        tamanho = this.getTamanho ();
        int grau;

        for(int i = 0; i < 60; i++){

            grau = i * 6;

            if(grau == 0 || grau == 90 || grau == 180 || grau == 180+90){

                this.vet_coords = new float[] {
                        -tamanho/50, 0,
                        -tamanho/50, tamanho*3,
                        tamanho/50, 0,
                        tamanho/50, tamanho*3
                };

            } else {

                this.vet_coords = new float[] {
                        -tamanho/30, 0,
                        -tamanho/30, tamanho*3,
                        tamanho/30, 0,
                        tamanho/30, tamanho*3
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
                -tamanho/30, 0,
                -tamanho/30, tamanho*3,
                tamanho/30, 0,
                tamanho/30, tamanho*3
        };

        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL().glLoadIdentity ();
        this.getOpenGL().glColor4f ( 1, 0, 0, 1 );
        this.getOpenGL().glTranslatef ( this.getPosX(), this.getPosY(), this.getTranslate_z () );
        this.getOpenGL().glRotatef(this.segundo, 0,0,4);
        this.getOpenGL().glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);


        tamanho = this.getTamanho();
        tamanho = (tamanho / 5);

        this.vet_coords = new float[] {
                -tamanho/10, 0,
                -tamanho/10, tamanho*2.5f,
                tamanho/10, 0,
                tamanho/10, tamanho*2.5f
        };

        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL().glLoadIdentity ();
        this.getOpenGL().glColor4f ( 0, 0, 1, 1 );
        this.getOpenGL().glTranslatef ( this.getPosX(), this.getPosY(), this.getTranslate_z () );
        this.getOpenGL().glRotatef(this.minuto, 0,0,4);
        this.getOpenGL().glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);


        tamanho = this.getTamanho();
        tamanho = (tamanho / 5);

        this.vet_coords = new float[] {
                -tamanho/7, 0,
                -tamanho/7, tamanho*2,
                tamanho/7, 0,
                tamanho/7, tamanho*2
        };

        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL().glLoadIdentity ();
        this.getOpenGL().glColor4f ( 0, 1, 0, 1 );
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
            this.getOpenGL().glColor4f ( 1, 1, 1, 1 );
            this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 4 );

        }


    }

}