/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmosDeBusqueda;

import Logica.Coordenadas;
import Logica.ManejoArchivos;
import Logica.Nodo;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author DanielFelipe
 */
public class BusquedaPorAmplitud {

    public int tablero[][];
    public Nodo raiz;

    public BusquedaPorAmplitud() {
        tablero = new ManejoArchivos().leerArchivoTablero();
    }

    public Nodo crearNodoRaiz() {
        int x = -1, y = -1;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == 2) {
                    x = i;
                    y = j;
                }
            }
        }
        Coordenadas coor = new Coordenadas(x, y);

        raiz = new Nodo(coor, null, false, "Ninguno", 0, 0, 0);
        
        return raiz;
    }

    public ArrayList<Coordenadas> mover(Nodo padre) {
        int columna = padre.getPosicion().getColumna();
        int fila = padre.getPosicion().getFila();

        ArrayList<Coordenadas> coordenadasPosibles = new ArrayList<>();
        
        //Izquierda
        if(columna - 1 != -1){
            coordenadasPosibles.add(new Coordenadas(fila, columna - 1));
        }
        //Abajo
        if(fila + 1 != 10){
            coordenadasPosibles.add(new Coordenadas(fila + 1, columna));
        }
        //Derecha
        if(columna + 1 != 10){
            coordenadasPosibles.add(new Coordenadas(fila, columna + 1));
        }
        //Arriba
        if(fila - 1 != -1){
            coordenadasPosibles.add(new Coordenadas(fila - 1, columna));
        }
        
        for (int i = 0; i < coordenadasPosibles.size(); i++) {
            if (tablero[coordenadasPosibles.get(i).getFila()][coordenadasPosibles.get(i).getColumna()] == 1) {
                coordenadasPosibles.remove(i);
                i--;
            }
        }
        
        return coordenadasPosibles;
    }
    
    public void esMeta(Nodo nodo){
        
        if(tablero[nodo.getPosicion().getFila()][nodo.getPosicion().getColumna()] == 6){
            tablero[nodo.getPosicion().getFila()][nodo.getPosicion().getColumna()] = 0;
            nodo.setMeta(nodo.getMeta()+1);
        }
        
    }
    
    public void esTraje(Nodo nodo){
        
        if(tablero[nodo.getPosicion().getFila()][nodo.getPosicion().getColumna()] == 3){
            tablero[nodo.getPosicion().getFila()][nodo.getPosicion().getColumna()] = 0;
            nodo.setTraje(true);
        }
        
    }
    
    public void esCampoTipoUno(Nodo nodo){
        
        if(tablero[nodo.getPosicion().getFila()][nodo.getPosicion().getColumna()] == 4 && nodo.isTraje() == false){
            nodo.setCosto(nodo.getCosto()+3);
        }
        
    }
    
    public void esCampoTipoDos(Nodo nodo){
        
        if(tablero[nodo.getPosicion().getFila()][nodo.getPosicion().getColumna()] == 5 && nodo.isTraje() == false){
            nodo.setCosto(nodo.getCosto() + 6);
        }
    }
    
    public void padreTieneTraje(Nodo padre, Nodo hijo){
        if(padre.isTraje()){
            hijo.setTraje(true);
        }
    }

    public ArrayList<Nodo> crearNodoHijo() {
        
        
        LinkedList<Nodo> cola = new LinkedList<>();
        
        cola.add(crearNodoRaiz());
        
        
        
        ArrayList<Nodo> padresEliminados = new ArrayList<>();

        while (cola.getFirst().getMeta() != 2) {

            ArrayList<Coordenadas> hijos = mover(cola.getFirst());

            for (int i = 0; i < hijos.size(); i++) {
                //Izquierda
                if (cola.getFirst().getPosicion().getFila() == hijos.get(i).getFila() && cola.getFirst().getPosicion().getColumna() - 1 == hijos.get(i).getColumna()) {
                    Nodo hijo = new Nodo(hijos.get(i), cola.getFirst(), false, "Izquierda", cola.getFirst().getProfundidad() + 1, cola.getFirst().getCosto() + 1, cola.getFirst().getMeta());
                    esMeta(hijo);
                    esTraje(hijo);
                    padreTieneTraje(cola.getFirst(), hijo);
                    esCampoTipoUno(hijo);
                    esCampoTipoDos(hijo);
                    cola.add(hijo);
                }
                //Abajo
                else if (cola.getFirst().getPosicion().getFila() + 1 == hijos.get(i).getFila() && cola.getFirst().getPosicion().getColumna() == hijos.get(i).getColumna()) {
                    Nodo hijo = new Nodo(hijos.get(i), cola.getFirst(), false, "Abajo", cola.getFirst().getProfundidad() + 1, cola.getFirst().getCosto() + 1, cola.getFirst().getMeta());
                    esMeta(hijo);
                    esTraje(hijo);
                    padreTieneTraje(cola.getFirst(), hijo);
                    esCampoTipoUno(hijo);
                    esCampoTipoDos(hijo);
                    cola.add(hijo);
                }
                //Derecha
                else if (cola.getFirst().getPosicion().getFila() == hijos.get(i).getFila() && cola.getFirst().getPosicion().getColumna() + 1 == hijos.get(i).getColumna()) {
                    Nodo hijo = new Nodo(hijos.get(i), cola.getFirst(), false, "Derecha", cola.getFirst().getProfundidad() + 1, cola.getFirst().getCosto() + 1, cola.getFirst().getMeta());
                    esMeta(hijo);
                    esTraje(hijo);
                    padreTieneTraje(cola.getFirst(), hijo);
                    esCampoTipoUno(hijo);
                    esCampoTipoDos(hijo);
                    cola.add(hijo);
                }
                //Arriba
                else if (cola.getFirst().getPosicion().getFila() - 1 == hijos.get(i).getFila() && cola.getFirst().getPosicion().getColumna() == hijos.get(i).getColumna()) {
                    Nodo hijo = new Nodo(hijos.get(i), cola.getFirst(), false, "Arriba", cola.getFirst().getProfundidad() + 1, cola.getFirst().getCosto() + 1, cola.getFirst().getMeta());
                    esMeta(hijo);
                    esTraje(hijo);
                    padreTieneTraje(cola.getFirst(), hijo);
                    esCampoTipoUno(hijo);
                    esCampoTipoDos(hijo);
                    cola.add(hijo);
                }

            }
            
            if(cola.isEmpty()){
                return padresEliminados;
            }
            
            padresEliminados.add(cola.getFirst());
            
            cola.removeFirst();
            

        }
        
        padresEliminados.add(cola.getFirst());
        
        //cola.removeFirst();
        
        
        return padresEliminados;
    }
    
    
    public ArrayList<Coordenadas> BuquedaAmplitud(ArrayList<Nodo> arregloAMeta) {

        int meta = arregloAMeta.size() - 1;

        ArrayList<Nodo> caminoALaMetaPadres = new ArrayList<>();

        ArrayList<Coordenadas> caminoALaMeta = new ArrayList<>();

        caminoALaMetaPadres.add(arregloAMeta.get(meta));
        
        caminoALaMetaPadres.add(arregloAMeta.get(meta).getPadre());
        caminoALaMetaPadres.add(arregloAMeta.get(meta).getPadre().getPadre());
        caminoALaMetaPadres.add(arregloAMeta.get(meta).getPadre().getPadre().getPadre());
        caminoALaMetaPadres.add(arregloAMeta.get(meta).getPadre().getPadre().getPadre().getPadre());
        caminoALaMetaPadres.add(arregloAMeta.get(meta).getPadre().getPadre().getPadre().getPadre().getPadre());
        caminoALaMetaPadres.add(arregloAMeta.get(meta).getPadre().getPadre().getPadre().getPadre().getPadre().getPadre());
        /*while (caminoALaMetaPadres.size() != arregloAMeta.get(meta).getProfundidad() + 1) {
            caminoALaMetaPadres.add(arregloAMeta.get(meta).getPadre());
        }*/

        for (int i = arregloAMeta.get(meta).getProfundidad(); i >= 0; i--) {
            caminoALaMeta.add(caminoALaMetaPadres.get(i).getPosicion());
        }

        return caminoALaMeta;
    }

    
//    public static void main(String[] args) {
//        BusquedaPorAmplitud o = new BusquedaPorAmplitud();
//        
//        BusquedaPorAmplitud n = new BusquedaPorAmplitud();
//        
//        ArrayList<Nodo> cola = new ArrayList<Nodo>();
//        cola = o.crearNodoHijo();
//        
//        for (int i = 0; i < cola.size(); i++) {
//            System.out.println("Operador: " + cola.get(i).getOperador());
//            System.out.println("Posicion: (" + cola.get(i).getPosicion().getFila() + "," + cola.get(i).getPosicion().getColumna() + ")");
//            System.out.println("Costo: " + cola.get(i).getCosto());
//            System.out.println("Profundidad: " + cola.get(i).getProfundidad());
//            System.out.println("Meta: " + cola.get(i).getMeta());
//            if(cola.get(i).getPadre() != null){
//                System.out.println("Padre: (" + cola.get(i).getPadre().getPosicion().getFila() + "," + cola.get(i).getPadre().getPosicion().getColumna() + ")");
//            }else{
//                System.out.println("Padre: " + cola.get(i).getPadre());
//            }
//            System.out.println("Traje: " + cola.get(i).isTraje());
//        }
//        
//        ArrayList<Coordenadas> arreglo = n.BuquedaAmplitud(n.crearNodoHijo());
//        
//        for (int i = 0; i < arreglo.size(); i++) {
//            System.out.println("(" + arreglo.get(i).getFila() + "," + arreglo.get(i).getColumna() + ")");
//        }
//    }
//    
}
