/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiscinaIdeas;

import Librerias.Partidas;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author rnexer
 */
public class CargarRegistroPartidas {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        ArrayList<Partidas> partidas = new ArrayList<>();
        File directorio = new File("GameFiles"+File.separator+"usuarios"+File.separator+"usuariox");
        File partida[] = directorio.listFiles();
        FileInputStream fileIn;
        ObjectInputStream parts;
        
        for (int i = 0; i < partida.length; i++) {
            fileIn = new FileInputStream(partida[i]);
            parts = new ObjectInputStream(fileIn);
            partidas.add((Partidas)parts.readObject());
            if (i == partida.length-1){
                parts.close();
                fileIn.close();
            }
        }
    }
    
}
