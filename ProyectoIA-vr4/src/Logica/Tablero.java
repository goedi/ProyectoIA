package Logica;


import AlgoritmosDeBusqueda.CostoUniforme;
import AlgoritmosDeBusqueda.ProfundidadSinCiclos;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DanielFelipe
 */
public class Tablero extends JFrame {
    
    
    JLabel tablero[][];
    JPanel jPanelPrincipal;
    GridLayout grid;
    //public CostoUniforme costo;
    public static int game_table [][]= new int[10][10];
    
    public Tablero() {
        super("Proyecto Inteligencia Artificial");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jPanelPrincipal = new JPanel();
        jPanelPrincipal.setBackground(Color.black);
        jPanelPrincipal.setLayout(grid = new GridLayout(10, 10));
        this.add(jPanelPrincipal);
        this.setMaximumSize(new java.awt.Dimension(400,400));
        setVisible(true);
        
        game_table = new ManejoArchivos().leerArchivoTablero();
    }
    
    public void mostrar(int[][] tabla) {

        tablero = new JLabel[10][10];
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla.length; j++) {
                switch (tabla[i][j]) {
                    case 0:
//                        System.out.println("caso0");
                        tablero[i][j] = new JLabel(new ImageIcon(getClass().getResource("/imagenes/blanco.jpg")));
//                        jPanelPrincipal.add(tablero[i][j]);
//                        this.repaint();
                        break;
                    case 1:
//                        System.out.println("caso1");
                        tablero[i][j] = new JLabel(new ImageIcon(getClass().getResource("/imagenes/marron.jpg")));
//                        jPanelPrincipal.add(tablero[i][j]);
//                        this.repaint();
                        break;
                    case 2:
//                        System.out.println("caso2");
                        tablero[i][j] = new JLabel(new ImageIcon(getClass().getResource("/imagenes/azul.jpg")));
//                        jPanelPrincipal.add(tablero[i][j]);
//                        this.repaint();
                        break;
                    case 3:
//                        System.out.println("caso3");
                        tablero[i][j] = new JLabel(new ImageIcon(getClass().getResource("/imagenes/lavanda.jpg")));
//                        jPanelPrincipal.add(tablero[i][j]);
//                        this.repaint();
                        break;
                    case 4:
//                        System.out.println("caso4");
                        tablero[i][j] = new JLabel(new ImageIcon(getClass().getResource("/imagenes/naranja.jpg")));
//                        jPanelPrincipal.add(tablero[i][j]);
//                        this.repaint();

                        break;
                    case 5:
//                        System.out.println("caso5");
                        tablero[i][j] = new JLabel(new ImageIcon(getClass().getResource("/imagenes/rojo.jpg")));
//                        jPanelPrincipal.add(tablero[i][j]);
//                        this.repaint();
                        break;
                    case 6:
//                        System.out.println("caso6");
                        tablero[i][j] = new JLabel(new ImageIcon(getClass().getResource("/imagenes/amarillo.jpg")));
//                        jPanelPrincipal.add(tablero[i][j]);
//                        this.repaint();
                        break;

                }
                jPanelPrincipal.add(tablero[i][j]);
                this.repaint();
            }
        }
        this.setSize(310, 320);
    }
    
    public void moverPlayer(int x_pos, int y_pos)
    {
//         new JLabel(new ImageIcon(getClass().getResource("/imagenes/azul.jpg")));
        JLabel temp_lb;
        int posX;
        int posY;
        if(ProfundidadSinCiclos.RUTA.size() > 0)
        {
            


            posX =x_pos;
            posY =y_pos;
            temp_lb = new JLabel(new ImageIcon(getClass().getResource("/imagenes/azul.jpg")));
            jPanelPrincipal.remove(posX*10+posY);
            jPanelPrincipal.add(temp_lb,posX*10+posY );
            
            
//            posX =CostoUniforme.padresEliminados.getFirst().getPosicion().getFila();
//            posY =CostoUniforme.padresEliminados.getFirst().getPosicion().getColumna();
//            temp_lb = new JLabel(new ImageIcon(getClass().getResource("/imagenes/blanco.jpg")));
//            jPanelPrincipal.remove(posX*10+posY);
//            jPanelPrincipal.add(temp_lb, posX*10+posY );  
        }
        jPanelPrincipal.updateUI();
        
    }

    public static void main(String[] args) {

        Tablero ob = new Tablero();
        ManejoArchivos o = new ManejoArchivos();
        ob.mostrar(game_table);
        ob.moverPlayer(9, 9);
    }

}
