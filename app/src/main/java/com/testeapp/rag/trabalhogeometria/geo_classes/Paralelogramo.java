package com.testeapp.rag.trabalhogeometria.geo_classes;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Paralelogramo extends Geometria {

    private float[] vet_coords = null;
    private FloatBuffer buffer = null;
    private int tamanho;

    public Paralelogramo(GL10 openGL, int tamanho){

        super();
        this.tamanho = tamanho;
        this.setTamanho ( tamanho );
        openGL.glEnableClientState ( GL10.GL_VERTEX_ARRAY );
        this.setOpenGL ( openGL );


    }

    public void desenha(){

        this.vet_coords = new float[] {
                -tamanho/2, -tamanho/2,
                -tamanho/2+(tamanho/4), tamanho/2,
                (tamanho * 2)/2, -tamanho/2,
                (tamanho * 2 + (tamanho/4))/2, tamanho/2
        };
        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);

        this.getOpenGL ().glLoadIdentity ();
        this.setOpenGLConfigs ();
        this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 4 );

    }


}
