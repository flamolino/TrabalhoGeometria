package com.testeapp.rag.trabalhogeometria.geo_classes;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GeraBuffer {

    public static FloatBuffer generateBuffer(float[] vetor) {

        ByteBuffer prBuffer = ByteBuffer.allocateDirect(vetor.length * 4);
        prBuffer.order( ByteOrder.nativeOrder());
        FloatBuffer prFloat = prBuffer.asFloatBuffer();
        prFloat.clear();
        prFloat.put(vetor);
        prFloat.flip();
        return prFloat;

    }

}
