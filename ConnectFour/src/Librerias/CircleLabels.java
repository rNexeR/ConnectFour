/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Librerias;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 *
 * @author Raim
 */
public class CircleLabels extends JLabel{   
    public CircleLabels(){
        super();               
    }    
    
    public void setColorIcon(char color){
        ImageIcon icon;
        if (color == 'A')
            icon  = new ImageIcon("src/assets/YellowCircle.png", "Ficha Amarilla");
        else if (color == 'R')
            icon = new ImageIcon("src/assets/RedCircle.png", "Ficha Roja");
        else             
            icon  = new ImageIcon("src/assets/BlankCircle.png", "Ficha Amarilla");

        setIcon(icon);
    }
}
