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
        File n = new File("GameFiles" + File.separator + "usuarios" + File.separator + loggedIn.getUsername() + File.separator + "numeracion.num");
        int num = -1;
        try {
            RandomAccessFile m = new RandomAccessFile(n, "rw");
            m.seek(0);
            num = m.readInt();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return num;
    }
}
