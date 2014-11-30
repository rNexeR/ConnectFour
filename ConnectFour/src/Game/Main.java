/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Librerias.Prints;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author rnexer
 */
public class Main {
    public static void main(String[] args) {
        Scanner rd = new Scanner(System.in);
        int op = 0;
        boolean exit = false;
        
        Prints.printlnWithColor("BLUE","     *******     444");
        Prints.printlnWithColor("BLUE","   ***         444 44");
        Prints.printlnWithColor("BLUE"," ***         444   44");
        Prints.printlnWithColor("BLUE","**** ONNECT 44444444444");
        Prints.printlnWithColor("BLUE"," ***               44");
        Prints.printlnWithColor("BLUE","   ***             44");
        Prints.printlnWithColor("BLUE","     *******       44\n\n");
        
        do{
            try{
                System.out.println("1- Login \n"
                        + "2- Crear Usuario \n"
                        + "3- Salir \n");
                System.out.print("Seleccione: ");
                op = rd.nextInt();
                switch (op){
                    case 1:
                        //Llamar Login
                        break;
                    case 2:
                        //Llamar CrearUsuario
                        break;
                    case 3:
                        exit = true;
                        break;
                }
            }catch (InputMismatchException e){
                rd.next();
                System.out.println("Ingrese un numero\n");
            }
        }while(!exit);
    }
}
