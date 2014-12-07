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
    ImageIcon icon  = new ImageIcon("src/assets/BlankCircle.png", "My Icon Image");
    
    public CircleLabels(){
        super();        
        //buttonPic1 = new JButton( new ImageIcon( getCodeBase().getPath() + "Block.jpg" ) );
        setText("HOLA");
        setBackground(Color.red);
        this.setBackground(Color.red);
        //setIcon(new javax.swing.ImageIcon(getClass().getResource("/src/assets/BlankCircle.png")));        
    }
    
    public void setColor(char color){
        if (color == 'A')
            setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/YellowCircle.png")));
        else if (color == 'R')
            setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/RedCircle.png")));
        else
            setIcon(new javax.swing.ImageIcon("/assets/BlankCircle.png"));
    }
    
    public void setColorIcon(){
        setIcon(icon);
    }
}
