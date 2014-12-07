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
    private int pterminadas, ppendientes, puntos;
    private String nombre, username, password;
    private long fechaNac;

    public Usuarios(String n, String user, String pass, long nac){
        //validar Existe
    	nombre = n;
    	username = user;
    	password = pass;
        fechaNac = nac;
    }

    public int getPterminadas() {
        return pterminadas;
    }

    public void setPterminadas(int pterminadas) {
        this.pterminadas = pterminadas;
    }

    public int getPpendientes() {
        return ppendientes;
    }

    public void setPpendientes(int ppendientes) {
        this.ppendientes = ppendientes;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Usuarios(int pterminadas, int ppendientes, int puntos, String nombre, String username, String password, long fechaNac) {
        this.pterminadas = pterminadas;
        this.ppendientes = ppendientes;
        this.puntos = puntos;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.fechaNac = fechaNac;
    }
    
    public long getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(long fechaNac) {
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
        if (user.equals(username) && pass.equals(password))
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
