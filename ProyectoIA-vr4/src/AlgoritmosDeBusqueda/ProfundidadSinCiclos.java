/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AlgoritmosDeBusqueda;

import Logica.Coordenadas;
import Logica.ManejoArchivos;
import Logica.Nodo;
import Logica.Tablero;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Goedi
 */
public class ProfundidadSinCiclos {

    //Variable globales

    public static int tablero[][];
    public static Vector <Nodo> COLA;
    public static LinkedList <Nodo> padresEliminados;
    public static LinkedList<Nodo> RUTA;
    private static int abuelo;
    public static Tablero vista;

    public ProfundidadSinCiclos() {
        //tablero = new ManejoArchivos().leerArchivoTablero();
        COLA = new Vector<>();
        padresEliminados = new LinkedList<>();
        RUTA = new LinkedList<>();
        abuelo = 3;
    }

    public static void crearNodoRaiz() {
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
        COLA.add(new Nodo(coor, null, false, "Inicio", 0, 0, 0));
    }

    public static ArrayList<Coordenadas> mover(Nodo padre) {
        int col = padre.getPosicion().getColumna();
        int fil = padre.getPosicion().getFila();

        ArrayList<Coordenadas> coordenadas = new ArrayList<>();
        ArrayList<Coordenadas> coordenadasPosibles = new ArrayList<>();

        coordenadas.add(new Coordenadas(fil + 1, col));
        coordenadas.add(new Coordenadas(fil - 1, col));
        coordenadas.add(new Coordenadas(fil, col + 1));
        coordenadas.add(new Coordenadas(fil, col - 1));

        for (int i = 0; i < coordenadas.size(); i++) {
            try {
                if (tablero[coordenadas.get(i).getFila()][coordenadas.get(i).getColumna()] != 1) {
                    coordenadasPosibles.add(coordenadas.get(i));
                }
            } catch (ArrayIndexOutOfBoundsException aioobe) {

            }
        }
        return coordenadasPosibles;
    }

    public static void esMeta(Nodo nodo) {

        if (tablero[nodo.getPosicion().getFila()][nodo.getPosicion().getColumna()] == 6) {
            tablero[nodo.getPosicion().getFila()][nodo.getPosicion().getColumna()] = 0;
            nodo.setMeta(nodo.getMeta() + 1);
        }

    }

    public static void esTraje(Nodo nodo) {

        if (tablero[nodo.getPosicion().getFila()][nodo.getPosicion().getColumna()] == 3) {
            tablero[nodo.getPosicion().getFila()][nodo.getPosicion().getColumna()] = 0;
            nodo.setTraje(true);
        }

    }

    public static void esCampoTipoUno(Nodo nodo) {

        if (tablero[nodo.getPosicion().getFila()][nodo.getPosicion().getColumna()] == 4 && nodo.isTraje() == false) {
            nodo.setCosto(nodo.getCosto() + 3);
        }
    }

    public static void esCampoTipoDos(Nodo nodo) {

        if (tablero[nodo.getPosicion().getFila()][nodo.getPosicion().getColumna()] == 5 && nodo.isTraje() == false) {
            nodo.setCosto(nodo.getCosto() + 6);
        }
    }

    public static void padreTieneTraje(Nodo padre, Nodo hijo) {
        if (padre.isTraje()) {
            hijo.setTraje(true);
        }
    }

    private static void buscarMenor(Vector<Nodo> cola) {
        //Collections.reverse(cola);
        Nodo menorCosto = cola.get(0);
        
        for (int i = 0; i < cola.size(); i++) {
            if (cola.get(i).getCosto() < menorCosto.getCosto()) {
                menorCosto = cola.get(i);
            }
        }
        COLA.remove(menorCosto);
        COLA.add(0, menorCosto);

        //return menorCosto;
    }

    public static boolean estaDevolviendo2(Nodo padre, Nodo hijo) {
        while (!padre.getOperador().equals("Inicio")) {
            try {
                if (padre.getPosicion().equals(hijo.getPosicion()) && padre.getMeta() == hijo.getMeta()) {
                    return true;
                } else {
                    estaDevolviendo2(padre.getPadre(), hijo);
                }
            }catch(NullPointerException npe){
                return true;
            }

        }
        return false;
    }

    public static void crearNodoHijo(Nodo padre) {
        Nodo hijo = new Nodo();
        ArrayList<Coordenadas> hijos = mover(padre);
        for (int i = 0; i < hijos.size(); i++) {
            //Izquierda
            if (padre.getPosicion().getFila() == hijos.get(i).getFila()
                    && padre.getPosicion().getColumna() - 1 == hijos.get(i).getColumna()) {
                hijo = new Nodo(hijos.get(i), padre, false, "Izquierda", padre.getProfundidad() + 1, padre.getCosto() + 1, padre.getMeta());
            }
            //Abajo
            else if (padre.getPosicion().getFila() + 1 == hijos.get(i).getFila()
                    && padre.getPosicion().getColumna() == hijos.get(i).getColumna()) {
                hijo = new Nodo(hijos.get(i), padre, false, "Abajo", padre.getProfundidad() + 1, padre.getCosto() + 1, padre.getMeta());
            } 
            //Derecha
            else if (padre.getPosicion().getFila() == hijos.get(i).getFila()
                    && padre.getPosicion().getColumna() + 1 == hijos.get(i).getColumna()) {
                hijo = new Nodo(hijos.get(i), padre, false, "Derecha", padre.getProfundidad() + 1, padre.getCosto() + 1, padre.getMeta());
            } 
            //Arriba
            else if (padre.getPosicion().getFila() - 1 == hijos.get(i).getFila()
                    && padre.getPosicion().getColumna() == hijos.get(i).getColumna()) {
                hijo = new Nodo(hijos.get(i), padre, false, "Arriba", padre.getProfundidad() + 1, padre.getCosto() + 1, padre.getMeta());

            }

            if (!estaDevolviendo2(padre, hijo)) {
                esMeta(hijo);
                esTraje(hijo);
                padreTieneTraje(padre, hijo);
                esCampoTipoUno(hijo);
                esCampoTipoDos(hijo);
                COLA.add(hijo);
                //System.out.println("creo un hijo!");
            }
        }
        padresEliminados.add(padre);
        COLA.remove(padre);
        abuelo = 3;
    }

    public static LinkedList<Nodo> ejecutar() {
        //LinkedList temp = COLA;
        crearNodoRaiz();
        int meta1 =1;
        int meta12 = 1;
        boolean armadura = true;
        while (!COLA.isEmpty()) {
            System.out.println("********************************");
            System.out.println("Operador: " + COLA.get(0).getOperador());
            System.out.println("Posicion: (" + COLA.get(0).getPosicion().getFila() + "," + COLA.get(0).getPosicion().getColumna() + ")");
            System.out.println("Costo: " + COLA.get(0).getCosto());
            System.out.println("Profundidad: " + COLA.get(0).getProfundidad());
            System.out.println("Meta: " + COLA.get(0).getMeta());
            System.out.println("Traje: " + COLA.get(0).isTraje());
            System.out.println("nodos en COLA = " + COLA.size());

            //vista.moverPlayer(COLA.get(0).getPosicion().getFila(), COLA.get(0).getPosicion().getColumna());
            crearNodoHijo(COLA.get(0));

            //buscarMenor(COLA);
            if(COLA.get(0).getMeta() == meta1 )
            {
                meta1++;
                Nodo ultimo = COLA.get(0);
                COLA.removeAllElements();
                COLA.add(ultimo);
            }
            else if(COLA.get(0).getMeta() == meta12 && COLA.get(0).isTraje() == armadura){
                meta12++;
                armadura = false;
                Nodo ultimo = COLA.get(0);
                COLA.removeAllElements();
                COLA.add(ultimo);
            }
            else if (padresEliminados.getLast().getMeta() == 2) {
                System.out.println("el ultimo padre eliminado fue = (" + padresEliminados.getLast().getPosicion().getFila() + "," + padresEliminados.getLast().getPosicion().getColumna() + ")");
                return padresEliminados;
            }
        }
        return padresEliminados;
    }

    public static LinkedList<Nodo> resultado(Nodo ultimo) {

        if (!ultimo.getOperador().equals("Inicio")) {
            RUTA.add(ultimo);
            resultado(ultimo.getPadre());
        } else {
            RUTA.add(ultimo);
            Collections.reverse(RUTA);
            return RUTA;
        }
        return RUTA;
    }

    public static void main(String[] args) {
        vista = new Tablero();
        vista.setVisible(true);
        tablero = vista.game_table;
        vista.mostrar(tablero);
        LinkedList <Nodo> rutaFinal = new LinkedList<>();
        ProfundidadSinCiclos o = new ProfundidadSinCiclos();
        rutaFinal = o.ejecutar();
        Nodo finalizo = rutaFinal.getLast();
        rutaFinal = new LinkedList<Nodo>();
        rutaFinal = ProfundidadSinCiclos.resultado(finalizo);
        for (int i = 1; i < rutaFinal.size(); i++) {
            System.out.println("///////////////////////////////////");
            System.out.println("Operador: " + rutaFinal.get(i).getOperador());
            System.out.println("Posicion: (" + rutaFinal.get(i).getPosicion().getFila() + "," + rutaFinal.get(i).getPosicion().getColumna() + ")");
            System.out.println("Costo: " + rutaFinal.get(i).getCosto());
            System.out.println("Profundidad: " + rutaFinal.get(i).getProfundidad());
            System.out.println("Meta: " + rutaFinal.get(i).getMeta());
            vista.moverPlayer(rutaFinal.get(i).getPosicion().getFila(), rutaFinal.get(i).getPosicion().getColumna());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("error");
            }
        }
        
    }
}
