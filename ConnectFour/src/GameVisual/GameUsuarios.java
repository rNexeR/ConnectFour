/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameVisual;

import Librerias.Usuarios;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author KELVIN
 */
public class GameUsuarios {
    public static ArrayList<Usuarios> users = new ArrayList<>();
    public static RandomAccessFile rUsers;
    
    public static void loadUsers(){
        //Verificar que la carpeta exista          
        File user = new File("GameFiles"+File.separator+"usuarios");           
        user.mkdirs();
        
        user = new File("GameFiles" + File.separator + "usuarios.cfo");
        int ppendientes, pterminadas, puntos;
        String nombre, username, password;
        long fecha;
        
        try {                           
            rUsers = new RandomAccessFile(user, "rw");             
            while(rUsers.getFilePointer() < rUsers.length()){               
                username = rUsers.readUTF();               
                password = rUsers.readUTF();                 
                nombre = rUsers.readUTF();   
                fecha = rUsers.readLong();     
                pterminadas = rUsers.readInt();
                ppendientes = rUsers.readInt();
                puntos = rUsers.readInt();               
                if (searchUser(username) == null)                  
                    users.add(new Usuarios(pterminadas, ppendientes, puntos, nombre, username, password, fecha));               
            }              
            rUsers.close();            
        } catch (IOException ex) {              
            System.out.println("Usuarios.cfo: Error al cargar");           
        }       
    }
    
    public static void saveUsers(){
        System.out.println("Guardando Usuarios");
        File user = new File("GameFiles"+File.separator+"usuarios.cfo");
        user.delete();
        
        try {
            user.createNewFile();
            rUsers = new RandomAccessFile(user, "rw");
        
            rUsers.seek(0);
            for (Usuarios x : users){
                rUsers.writeUTF(x.getUsername());
                rUsers.writeUTF(x.getPassword());
                rUsers.writeUTF(x.getNombre());
                rUsers.writeLong(x.getFechaNac());
                rUsers.writeInt(x.getPterminadas());
                rUsers.writeInt(x.getPpendientes());
                rUsers.writeInt(x.getPuntos());
            }
            System.out.println("Usuarios guardados");
            rUsers.close();
       } catch (IOException ex) {
           System.out.println("Error al guardar Usuarios");
       }
        
    }
    
    public static boolean deleteUser(String username){
        for (Usuarios x : users){
            if (x.getUsername().equals(username))
                return users.remove(x);
        }
        return false;
    }
    
    public static Usuarios searchUser(String user){
        for (Usuarios x : users){
            if (x.getUsername().equalsIgnoreCase(user))
                return x;
        }
        return null;
    }
}
