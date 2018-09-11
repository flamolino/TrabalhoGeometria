package com.testeapp.rag.trabalhogeometria.geo_classes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Paralelogramo extends Geometria {

    float[] vet_coords = null;
    FloatBuffer buffer = null;

    public Paralelogramo(GL10 openGL, int tamanho){

        super();

        openGL.glEnableClientState ( GL10.GL_VERTEX_ARRAY );

        this.vet_coords = new float[] {

                0, 0,
                tamanho/4, tamanho,
                tamanho * 2, 0,
                tamanho * 2 + (tamanho/4), tamanho

        };

        this.buffer = generateBuffer(this.vet_coords);
        openGL.glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.setOpenGL ( openGL );

    }

    private FloatBuffer generateBuffer(float[] vetor) {

        ByteBuffer prBuffer = ByteBuffer.allocateDirect(vetor.length * 4);
        prBuffer.order( ByteOrder.nativeOrder());
        FloatBuffer prFloat = prBuffer.asFloatBuffer();
        prFloat.clear();
        prFloat.put(vetor);
        prFloat.flip();
        return prFloat;

    }

    public void desenha(){

        this.getOpenGL ().glLoadIdentity ();
        this.setOpenGLConfigs ();
        this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 4 );

    }

}
