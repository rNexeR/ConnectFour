/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Librerias;

/**
 *
 * @author rnexer
 */
public class Prints {
    
    /**
     * Imprime texto con Color sin salto de linea
     * @param color Color de la fuente del Mensaje:
     *              NEGRO
     *              ROJO
     *              VERDE
     *              AMARILLO
     *              AZUL
     *              MAGENTA
     *              CYAN
     *              BLANCO
     * @param message Mensaje a mostrar
     */
    public static void printWithColor(String color, String message){
        try{
            Colores este = Colores.valueOf(color.toUpperCase());
            System.out.print("\033[" + este.cod + "m" + message + "\033[30m");
        }catch (IllegalArgumentException e){
            System.out.println("\033[31m\tError: " + e.getMessage() + "\n" +
                    "\tDescripcion: Color no Soportado");
        }
    } 
    
    /**
     * Imprime texto con Color con salto de linea
     * @param color Color de la fuente del Mensaje:
     *              WHITE
     *              RED
     *              GREEN
     *              YELOW
     *              BLUE
     *              MAGENTA
     *              CYAN
     *              BLACK
     * @param message Mensaje a mostrar
     */
    public static void printlnWithColor(String color, String message){
        try{
            Colores este = Colores.valueOf(color.toUpperCase());
            System.out.println("\033[" + este.cod + "m" + message + "\033[30m");
        }catch (IllegalArgumentException e){
            System.out.println("\033[31m\tError: " + e.getMessage() + "\n" +
                    "\tDescripcion: Color no Soportado");
        }
    }
}
