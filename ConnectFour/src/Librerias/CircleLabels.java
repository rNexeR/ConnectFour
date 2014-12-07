/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Librerias;

import javax.swing.JLabel;

/**
 *
 * @author Raim
 */
public class CircleLabels extends JLabel{
    public CircleLabels(){
        super();
        setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/BlankCircle.png")));
    }
    
    public void setColor(char color){
        if (color == 'A')
            setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/YellowCircle.png")));
        else
            setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/RedCircle.png")));

    }
}
