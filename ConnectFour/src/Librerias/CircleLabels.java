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
    public String descripcion;
    public CircleLabels(){
        super();               
    }    
    
    public void setColorIcon(char color){
        ImageIcon icon;
        if (color == 'A'){
            descripcion = "Ficha Amarilla";
            icon  = new ImageIcon("src/assets/YellowCircle.png", descripcion);
        }else if (color == 'R'){
            descripcion = "Ficha Roja";
            icon = new ImageIcon("src/assets/RedCircle.png", descripcion);
        }else{          
            descripcion = "Ficha Blank";
            icon  = new ImageIcon("src/assets/BlankCircle.png", descripcion);
        }
        setIcon(icon);
    }
}
