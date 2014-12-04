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
import java.util.Calendar;

/**
 *
 * @author ADMIN
 */
public class Usuarios {
    private String nombre, username, password;
    Calendar fechaNac;

    public Usuarios(String n, String user, String pass, Calendar nac){
        //validar Existe
    	nombre = n;
    	username = user;
    	password = pass;
        fechaNac = nac;
    }

    public Calendar getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Calendar fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNombre(){
    	return nombre;
    }

    public void setNombre(String n){
    	nombre = n;
    }

    public String getUsername(){
    	return username;
    }

    public void setUsername(String user){
    	username = user;
    }

    public String getPassword(){
    	return password;
    }

    public void setPassword(String p){
    	password = p;
    }

    public boolean login(String user, String pass){
        if (user == username && pass == password)
            return true;
        return false;
    }
    /**
     * Imprime los datos del usuario, exceptuando password.
     */
    public void print(){
        System.out.printf("Nombre: %s - Username: %s \n", 
                getNombre(), getUsername());
    }
}
