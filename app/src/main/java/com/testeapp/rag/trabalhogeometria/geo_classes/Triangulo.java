package com.testeapp.rag.trabalhogeometria.geo_classes;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Triangulo extends Geometria {

    private float[] vet_coords = null;
    private FloatBuffer buffer = null;

    public Triangulo(GL10 openGL, int tamanho){

        super();
        this.setTamanho ( tamanho );
        this.setTipo ( 2 );
        openGL.glEnableClientState ( GL10.GL_VERTEX_ARRAY );
        this.setOpenGL ( openGL );

    }

    public void desenha(){

        int tamanho = this.getTamanho ();
        this.vet_coords = new float[] {
                -tamanho/2, -tamanho/2,
                (tamanho/2)+(-tamanho/2), tamanho/2,
                tamanho/2, -tamanho/2
        };

        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);

        this.getOpenGL ().glLoadIdentity ();
        this.setOpenGLConfigs ();
        this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_FAN, 0, 3 );

    }


}
