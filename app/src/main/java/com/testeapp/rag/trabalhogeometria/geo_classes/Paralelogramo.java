package com.testeapp.rag.trabalhogeometria.geo_classes;

import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Paralelogramo extends Geometria {

    private float[] vet_coords = null;
    private FloatBuffer buffer = null;
    private float red, green, blue;

    public Paralelogramo(GL10 openGL, int tamanho){

        super();
        this.setTamanho ( tamanho );
        this.setTipo ( 3 );
        openGL.glEnableClientState ( GL10.GL_VERTEX_ARRAY );
        this.setOpenGL ( openGL );

    }

    public void desenha(){

        this.red = this.getColor_red ();
        this.green = this.getColor_green ();
        this.blue = this.getColor_blue ();
        this.setCor ( 0,0,0,1 );
        int tamanho = this.getTamanho ();

        this.vet_coords = new float[]{

                0 - tamanho / 2, tamanho - tamanho / 2,
                (tamanho + (tamanho / 2.5f)) - tamanho / 2, tamanho - tamanho / 2,
                ((tamanho + (tamanho / 2.5f)) / 2) - tamanho / 2, (tamanho / 3.4f) - tamanho / 2
        };

        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL ().glLoadIdentity ();
        this.setOpenGLConfigs ();
        this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 3 );

        this.vet_coords = new float[] {

                (tamanho/2.5f)/2.1f, -tamanho/5f,
                tamanho + (tamanho/1.65f), -tamanho/5f,
                (tamanho + (tamanho/2.5f)) / 1.55f, (tamanho / 2f)

        };

        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL ().glLoadIdentity ();
        this.setOpenGLConfigs ();
        this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 3 );





        float lin = 1;
        this.setCor ( this.red,this.green,this.blue,1 );
        this.vet_coords = new float[]{

                0 - tamanho / 2 + lin, tamanho - tamanho / 2 - lin,
                (tamanho + (tamanho / 2.5f)) - tamanho / 2 + lin, tamanho - tamanho / 2 - lin,
                ((tamanho + (tamanho / 2.5f)) / 2) - tamanho / 2, (tamanho / 3.4f) - tamanho / 2 + lin
        };

        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL ().glLoadIdentity ();
        this.setOpenGLConfigs ();
        this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 3 );

        this.setCor ( this.red,this.green,this.blue,1 );
        this.vet_coords = new float[] {

                (tamanho/2.5f)/2.1f + lin, -tamanho/5f + lin,
                tamanho + (tamanho/1.65f) - lin, -tamanho/5f - lin,
                (tamanho + (tamanho/2.5f)) / 1.55f, (tamanho / 2f) - lin

        };

        this.buffer = GeraBuffer.generateBuffer(this.vet_coords);
        this.getOpenGL ().glVertexPointer(2, GL10.GL_FLOAT, 0, this.buffer);
        this.getOpenGL ().glLoadIdentity ();
        this.setOpenGLConfigs ();
        this.getOpenGL ().glDrawArrays ( GL10.GL_TRIANGLE_STRIP, 0, 3 );
    }



}
