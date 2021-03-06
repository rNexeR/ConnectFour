/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameVisual;

import Librerias.Usuarios;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author KELVIN
 */
public class GameNumeraciones {
    public static int getNextNumPartida(Usuarios loggedIn){
        System.out.println("Solicitando nuevo numero de partida");
        File n = new File("GameFiles" + File.separator + "usuarios" + File.separator + loggedIn.getUsername() + File.separator + "numeracion.num");
        int num = -1;
        try {
            RandomAccessFile m = new RandomAccessFile(n, "rw");
            m.seek(0);
            num = m.readInt();
            
            m.seek(0);
            m.writeInt(num+1);
            
            m.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Numero de partida: " + num);
        return num;
    }
    
    public static int getNextNumTablero(Usuarios loggedIn){
        File n = new File("GameFiles" + File.separator + "usuarios" + File.separator + loggedIn.getUsername() + File.separator + "tableros" + File.separator + "numeracion.num");
        int num = -1;
        try {
            RandomAccessFile m = new RandomAccessFile(n, "rw");
            m.seek(0);
            num = m.readInt();
            
            m.seek(0);
            m.writeInt(num+1);
            
            m.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return num;
    }
}
