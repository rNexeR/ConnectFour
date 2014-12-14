/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Librerias;

/**
 *
 * @author Raim
 */
public class UserNoLongerExistsException extends Exception{

    public UserNoLongerExistsException(String username) {
        super("Usuario " + username +" ya no existe.");
    }
    
}
