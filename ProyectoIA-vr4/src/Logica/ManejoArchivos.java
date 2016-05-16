package Logica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template arch, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;

/**
 *
 * @author DanielFelipe
 */
public class ManejoArchivos {

    public int[][] leerArchivoTablero() {

        int[][] tablero = new int[10][10];

        try {
            JFileChooser archivo = new JFileChooser();
            archivo.showOpenDialog(null);
            File arch = archivo.getSelectedFile();
            FileReader frLector = new FileReader(arch);
            BufferedReader brLector = new BufferedReader(frLector);

            String linea = null;
            int contadorFilas = 0;
            while ((linea = brLector.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(linea);
                for (int i = 0; i < 10; i++) {
                    tablero[contadorFilas][i] = Integer.parseInt(stk.nextToken());
                }
                contadorFilas++;
            }

        } catch (IOException e) {

        }
        return tablero;
    }
}
