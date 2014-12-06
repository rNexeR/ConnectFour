/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameVisual;

import java.awt.Label;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

/**
 *
 * @author rnexer
 */
public class MainConnectFour extends javax.swing.JFrame {
   private JButton login, addUser, salir, btnaceptar = null, btncancelar = null;
   private Label user, pass;
   private JTextField txtuser, txtpass;
   private char op;
   private int x = 125, y = 0;
    
    /**
     * Creates new form MainConnectFour
     */
    public MainConnectFour() {
        initComponents();
        createOptions();
    }
    
    private void createOptions(){
        login = new JButton();
        login.setText("Iniciar Sesion");
        getContentPane().add(login);
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        
        addUser = new JButton();
        addUser.setText("Crear Usuario");
        getContentPane().add(addUser);
        addUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserActionPerformed(evt);
            }
        });
        
        salir = new JButton();
        salir.setText("Salir");
        getContentPane().add(salir);
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        
        btnaceptar = new JButton();
        btnaceptar.setText("Aceptar");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });
        
        btncancelar = new JButton();
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });
        
        //
        login.setBounds(x, 133 -35 -5, 150, 35);
        addUser.setBounds(x, 133, 150, 35);
        salir.setBounds(x, 133 +35 +5, 150, 35);
        btnaceptar.setSize(100, 30);
        btncancelar.setSize(100, 30);
    }
    
    private void btnaceptarActionPerformed(ActionEvent evt) {
        //Aqui el Codigo
    }
    
    private void btncancelarActionPerformed(ActionEvent evt) {
        //Aqui el Codigo
        if (op == 'L'){
            getContentPane().remove(txtuser);
            getContentPane().remove(txtpass);
            getContentPane().remove(user);
            getContentPane().remove(pass);
            moveOption(addUser, -90);
            moveOption(salir, -90);
        }else{
            
        }
            getContentPane().remove(btnaceptar);
            getContentPane().remove(btncancelar);
            changeOptionEnabled(true);
            getContentPane().repaint();
    }
    
    private void loginActionPerformed(ActionEvent evt) {
        //Aqui el codigo
        System.out.println("Login");
        op = 'L';
        clicLogin();
    }
    
    private void addUserActionPerformed(ActionEvent evt) {
        //Aqui el Codigo
        System.out.println("AddUser");
        op = 'C';
    }
    
    private void salirActionPerformed(ActionEvent evt) {
        //Aqui el Codigo
        System.out.println("Salir");
    }
    
    private void changeOptionEnabled(boolean state){
        login.setEnabled(state);
        addUser.setEnabled(state);
        salir.setEnabled(state);
    }
    
    private void clicLogin(){
        changeOptionEnabled(false);
        moveOption(addUser, 90);
        moveOption(salir, 90);
        
        user = new Label();
        user.setText("Usuario: ");
        getContentPane().add(user);
        user.setLocation(login.getX()-80, login.getY()+45);
        user.setSize(70, 35);
        
        
        pass = new Label();
        pass.setText("Password: ");
        getContentPane().add(pass);
        pass.setLocation(login.getX()-80, user.getY()+35);
        pass.setSize(70, 35);
        
        txtuser = new JTextField();
        getContentPane().add(txtuser);
        txtuser.setLocation(user.getX()+user.getWidth(), user.getY());
        txtuser.setSize(150, 30);
        
        txtpass = new JTextField();
        getContentPane().add(txtpass);
        txtpass.setLocation(pass.getX()+pass.getWidth(), pass.getY());
        txtpass.setSize(150, 30);
        
        getContentPane().add(btnaceptar);
        getContentPane().add(btncancelar);
        
        btnaceptar.setLocation(txtuser.getX()+txtuser.getWidth(), txtuser.getY());
        btncancelar.setLocation(txtpass.getX()+txtpass.getWidth(), txtpass.getY());
    }
    
    private void moveOption(JButton x, int y){
        x.setLocation(x.getX(), x.getY() + y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainConnectFour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainConnectFour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainConnectFour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainConnectFour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainConnectFour().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}


