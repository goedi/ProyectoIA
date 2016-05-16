package Logica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DanielFelipe
 */
public class Nodo {
    private Coordenadas posicion;
    public Nodo padre;
    private boolean traje;
    private String operador;
    private int profundidad, costo, meta;

    public Nodo(Coordenadas posicion, Nodo padre, boolean traje, String operador, int profundidad, int costo, int meta) {
        this.posicion = posicion;
        this.padre = padre;
        this.traje = traje;
        this.operador = operador;
        this.profundidad = profundidad;
        this.costo = costo;
        this.meta = meta;
    }

    public Nodo() {
        this.posicion = null;
        this.padre = null;
        this.traje = false;
        this.operador = "";
        this.profundidad = -1;
        this.costo = -1;
        this.meta = -1;
    }

    public Coordenadas getPosicion() {
        return posicion;
    }

    public void setPosicion(Coordenadas posicion) {
        this.posicion = posicion;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public boolean isTraje() {
        return traje;
    }

    public void setTraje(boolean traje) {
        this.traje = traje;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }
    
    
}
