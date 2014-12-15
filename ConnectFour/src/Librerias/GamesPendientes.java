/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Librerias;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Raim
 */
public class GamesPendientes {
    private static Usuarios loggedUser;
    private static String dir;
    
    public static ArrayList<String> getPartidasPendientes(Usuarios logged){
        ArrayList<String> partidas = new ArrayList<>();
        dir = "GameFiles" + File.separator + "usuarios" + File.separator + logged.getUsername() + File.separator;
        File fi = new File(dir);
        String files[] = fi.list();
        for (String s : files){
            if (s.startsWith("partida")){
                 fi = new File(dir + File.separator + s);
                try {
                    RandomAccessFile rPartida = new RandomAccessFile(fi, "r");
                    //Correlativo del juego – Juego vs JUGADOR CONTRARIO iniciado el FECHA – Turno #
                    int numPartida = rPartida.readInt();
                    String userActual = rPartida.readUTF();
                    String adversario = rPartida.readUTF();
                    Date fecha = new Date(rPartida.readLong());                    
                    char estado = rPartida.readChar();
                    rPartida.skipBytes(4);
                    int turno = rPartida.readInt();
                    
                    rPartida.close();
                    Formatter formato = new Formatter();
                    formato.format("%d - %s VS %s - Iniciado en: %tc - Turno %d", numPartida, userActual, adversario 
                            , fecha, turno);
                    if (estado == 'P')
                        partidas.add(formato.toString());                    
                } catch (IOException ex) {
                    System.out.println("Error generando Partidas Pendientes");
                }
            }
        }
        return partidas;
    }
}
