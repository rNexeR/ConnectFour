/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Librerias.Prints;
import Librerias.Usuarios;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author rnexer
 */
public class MenuPrincipal {
    //Variables para el Funcionamiento del Programa
    Scanner rd = new Scanner(System.in);
    
    //ArrayLists
    ArrayList<Usuarios> users = new ArrayList<>();
    
    //Variables para RandomAccessFile
    RandomAccessFile usuarios;
    
    //Variables para el Control de Turnos
    String currentUser;
    
    void login(){
        Prints.printWithColor("YELLOW","Usuario: ");
        String user = rd.next();
        Prints.printWithColor("YELLOW","Contraseña: ");
        String pass = rd.next();
        
        //buscarUsuario por nombre
//        if (Usuarios.login()){
//            
//        }
    }
    
    void printMenu(){
        int op = 0;
        boolean accept = false;
        do{
            try{
                Prints.printlnWithColor("CYAN","1- Jugar Connect Four \n"
                        + "2- Reportes \n"
                        + "3- Mi Perfil \n"
                        + "4- Cerrar Sesion \n");
                System.out.print("Seleccione: ");
                op = rd.nextInt();
                
                if (op > 0 && op < 5)
                    accept = true;
                else
                    continue;
                
                switch (op){
                    case 1:
                        //Llamar ConnectFour
                        break;
                    case 2:
                        //Llamar Reportes
                        break;
                    case 3:
                        //Llamar miPerfil
                        break;
                    case 4:
                        //Cerrar Sesion
                        break;
                }
            }catch (InputMismatchException e){
                rd.next();
                System.out.println("Ingrese un numero\n");
            }
        }while(!accept);
                
    }
}
