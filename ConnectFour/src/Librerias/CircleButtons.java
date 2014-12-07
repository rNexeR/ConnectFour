/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Librerias;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Raim
 */
public class CircleButtons extends JButton{
    ImageIcon icon  = new ImageIcon("src/assets/BlankCircle.png", "My Icon Image");
    
    public CircleButtons(){
        super();
        super.setIcon(icon);
        setSelectedIcon(icon);
        setPressedIcon(icon);
        //setBackground(Color.red);
        //setEnabled(false);
    }
    
    public void setColorIcon(){
        setIcon(icon);
    }
}
