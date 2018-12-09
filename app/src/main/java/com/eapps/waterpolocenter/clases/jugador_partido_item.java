package com.eapps.waterpolocenter.clases;

public class jugador_partido_item {

    String nombre;
    int flag,dato;

    public jugador_partido_item(String nombre, int dato, int flag) {
        this.nombre = nombre;
        this.dato = dato;
        this.flag = flag;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
