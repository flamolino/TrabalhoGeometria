package com.testeapp.rag.trabalhogeometria.geo_classes;

import javax.microedition.khronos.opengles.GL10;

public abstract class Geometria {

    private float color_red;
    private float color_green;
    private float color_blue;
    private float color_alpha;
    private float translate_x;
    private float translate_y;
    private float translate_z;
    private float scale_x;
    private float scale_y;
    private float scale_z;
    private float rotate_angulo;
    private float rotate_x;
    private float rotate_y;
    private float rotate_z;
    private int tamanho;
    private int tipo;
    private GL10 openGL = null;

    public Geometria() {

        this.setColor_alpha ( 1 );
        this.setColor_blue ( 0 );
        this.setColor_green ( 0 );
        this.setColor_red ( 0 );

        this.setTranslate_x ( 0 );
        this.setTranslate_y ( 0 );
        this.setTranslate_z ( 0 );

        this.setScale_x ( 1 );
        this.setScale_y ( 1 );
        this.setScale_z ( 1 );

        this.setRotate_angulo ( 0 );
        this.setRotate_x ( 0 );
        this.setRotate_y ( 0 );
        this.setRotate_z ( 1 );

    }

    public void setCor(float red, float green, float blue, float alpha){

        this.setColor_red ( red );
        this.setColor_green ( green );
        this.setColor_blue ( blue );
        this.setColor_alpha ( alpha );

    }

    public void setPosXY(float x, float y){

        this.setTranslate_x ( x );
        this.setTranslate_y ( y );

    }

    public void setScala(float x, float y){

        this.setScale_x ( x );
        this.setScale_y ( y );

    }

    public void setRotacao(float angulo){

        this.setRotate_angulo ( angulo );

    }

    protected void setOpenGLConfigs(){

        this.openGL.glTranslatef ( this.getTranslate_x (), this.getTranslate_y (), this.getTranslate_z () );
        this.openGL.glRotatef ( this.getRotate_angulo (), this.getRotate_x (), this.getRotate_y (), this.getRotate_z () );
        this.openGL.glScalef ( this.getScale_x (), this.getScale_y (), this.getScale_z () );
        this.openGL.glColor4f ( this.getColor_red (), this.getColor_green (), this.getColor_blue (), this.getColor_alpha () );

    }

    protected float getColor_red() {
        return color_red;
    }

    protected void setColor_red(float color_red) {
        this.color_red = color_red;
    }

    protected float getColor_green() {
        return color_green;
    }

    protected void setColor_green(float color_green) {
        this.color_green = color_green;
    }

    protected float getColor_blue() {
        return color_blue;
    }

    protected void setColor_blue(float color_blue) {
        this.color_blue = color_blue;
    }

    protected float getColor_alpha() {
        return color_alpha;
    }

    protected void setColor_alpha(float color_alpha) {
        this.color_alpha = color_alpha;
    }

    protected float getTranslate_x() {
        return translate_x;
    }

    protected void setTranslate_x(float translate_x) {
        this.translate_x = translate_x;
    }

    protected float getTranslate_y() {
        return translate_y;
    }

    protected void setTranslate_y(float translate_y) {
        this.translate_y = translate_y;
    }

    protected float getTranslate_z() {
        return translate_z;
    }

    protected void setTranslate_z(float translate_z) {
        this.translate_z = translate_z;
    }

    protected float getScale_x() {
        return scale_x;
    }

    protected void setScale_x(float scale_x) {
        this.scale_x = scale_x;
    }

    protected float getScale_y() {
        return scale_y;
    }

    protected void setScale_y(float scale_y) {
        this.scale_y = scale_y;
    }

    protected float getScale_z() {
        return scale_z;
    }

    protected void setScale_z(float scale_z) {
        this.scale_z = scale_z;
    }

    public float getRotate_angulo() {
        return rotate_angulo;
    }

    protected void setRotate_angulo(float rotate_angulo) {
        this.rotate_angulo = rotate_angulo;
    }

    protected float getRotate_x() {
        return rotate_x;
    }

    protected void setRotate_x(float rotate_x) {
        this.rotate_x = rotate_x;
    }

    protected float getRotate_y() {
        return rotate_y;
    }

    protected void setRotate_y(float rotate_y) {
        this.rotate_y = rotate_y;
    }

    protected float getRotate_z() {
        return rotate_z;
    }

    protected void setRotate_z(float rotate_z) {
        this.rotate_z = rotate_z;
    }

    protected GL10 getOpenGL() {
        return openGL;
    }

    protected void setOpenGL(GL10 openGL) {
        this.openGL = openGL;
    }

    public float getPosX(){
        return this.getTranslate_x ();
    }

    public float getPosY(){
        return this.getTranslate_y ();
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
