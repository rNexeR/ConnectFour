/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Librerias;

/**
 *
 * @author ADMIN
 */
public class Usuarios {
    private String nombre, username, password;

    public Usuarios(String n, String user, String pass){
    	nombre = n;
    	username = user;
    	password = pass;
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

    /**
     * Imprime los datos del usuario, exceptuando password.
     */
    public void print(){
        System.out.printf("Nombre: %s - Username: %s \n", 
                getNombre(), getUsername());
    }
}
