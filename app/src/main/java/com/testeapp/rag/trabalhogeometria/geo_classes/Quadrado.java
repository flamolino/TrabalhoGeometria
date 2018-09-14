package com.testeapp.rag.trabalhogeometria.geo_classes;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Quadrado extends Geometria {

    private float[] vet_coords = null;
    private FloatBuffer buffer = null;
    private float red, green, blue;

    public Quadrado(GL10 openGL, int tamanho){

        super();
        this.setTamanho ( tamanho );
        this.setTipo ( 1 );
        openGL.glEnableClientState ( GL10.GL_VERTEX_ARRAY );
        this.setOpenGL ( openGL );

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

        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL ().glLoadIdentity ();
        this.setOpenGLConfigs ();
        this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 4 );

        float lin = 1;
        this.setCor ( this.red,this.green,this.blue,1 );
        this.vet_coords = new float[] {
                -tamanho/2+lin, -tamanho/2+lin,
                -tamanho/2+lin, tamanho/2-lin,
                tamanho/2-lin, -tamanho/2+lin,
                tamanho/2-lin, tamanho/2-lin
        };
        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL ().glLoadIdentity ();
        this.setOpenGLConfigs ();
        this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 4 );

    }



}
