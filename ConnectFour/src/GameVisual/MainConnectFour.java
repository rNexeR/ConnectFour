/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameVisual;

import Librerias.Usuarios;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author rnexer
 */
public class MainConnectFour extends javax.swing.JFrame {
   private JButton login, addUser, salir, btnaceptar = null, btncancelar = null;
   private Label user, pass, nombre, fecha;
   private JFormattedTextField txtfecha;
   private JTextField txtuser, txtnombre;
   private JPasswordField txtpass;
   private char op;
   private int x = 125, y = 0;
   
   private ArrayList<Usuarios> users;
   private RandomAccessFile rUsers;
    
    /**
     * Creates new form MainConnectFour
     */
    public MainConnectFour() {
        initComponents();
        ImageIcon icono = new ImageIcon("icono.png"); 
        this.setIconImage(icono.getImage()); 
        users = new ArrayList<>();
        loadUsers();
        createOptions();
    }
    
    private void loadUsers(){
        //Verificar que la carpeta exista
        File user = new File("GameFiles");
        if (!user.exists())
            user.mkdir();
            
        
        user = new File("GameFiles" + File.separator + "Usuarios.cfo");
       
        int cod, ppendientes, pterminadas, puntos;
        String nombre, username, password;
        long fecha;
        
        if (user.exists()){
            try {
                //Cargar Usuarios
                 try {
                     rUsers = new RandomAccessFile(user, "rw");
                 } catch (FileNotFoundException ex) {
                     System.out.println("Archivo no encontrado");
                 }
                while(rUsers.getFilePointer() < rUsers.length()){
                    username = rUsers.readUTF();
                    password = rUsers.readUTF();
                    nombre = rUsers.readUTF();
                    fecha = rUsers.readLong();
                    pterminadas = rUsers.readInt();
                    ppendientes = rUsers.readInt();
                    puntos = rUsers.readInt();
                    users.add(new Usuarios(pterminadas, ppendientes, puntos, nombre, username, password, fecha));
                }
            } catch (IOException ex) {
                System.out.println("Usuarios.cfo: Error al cargar");
            }
        }else{
             try {
                 user.createNewFile();
             } catch (IOException ex) {
                 System.out.println("Usuarios.cfo: Error al crear");
             }
        }
       
    }
    
    public void saveUsers(){
       try {
            rUsers.seek(0);
            for (Usuarios x : users){
                rUsers.writeUTF(x.getUsername());
                rUsers.writeUTF(x.getPassword());
                rUsers.writeUTF(x.getNombre());
                rUsers.writeLong(x.getFechaNac());
                rUsers.writeInt(x.getPterminadas());
                rUsers.writeInt(x.getPpendientes());
                rUsers.writeInt(x.getPuntos());
            }
       } catch (IOException ex) {
           System.out.println("Error al guardar Usuarios");
       }
        
    }
    
    public boolean deleteUser(String username){
        for (Usuarios x : users){
            if (x.getUsername().equals(username))
                return users.remove(x);
        }
        return false;
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
    
    private Usuarios searchUser(String user){
        for (Usuarios x : users){
            if (x.getUsername().equalsIgnoreCase(user))
                return x;
        }
        return null;
    }
    
    private void close(){
        if (JOptionPane .showConfirmDialog(rootPane, "¿Desea realmente salir del sistema?",
                "Salir del sistema", JOptionPane .YES_NO_OPTION) == JOptionPane .YES_OPTION){
            System .exit(0);
            //Escribir en el archivo de texto
            saveUsers();
        }
    }   
    
    private void btnaceptarActionPerformed(ActionEvent evt) {
        //Aqui el Codigo
        if (op == 'L'){
            if (users.size() > 0){
                String user1 = txtuser.getText();
                Usuarios buscar = searchUser(user1);
                if (buscar != null){
                    String passwd = new String(txtpass.getPassword());
                    if (buscar.getPassword().equals(passwd)){
                        System.out.println("Sesion Iniciada");
                        //Llamar Formulario de ConnectFour
                        exitLogin();
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this, "No hay registros de Usuarios creados", "Error al Inicar Sesion", JOptionPane.INFORMATION_MESSAGE);
                exitLogin();
            }
        }else{
            //addUser
            String usern = txtuser.getText();
            Usuarios buscar = searchUser(usern);
            if (buscar != null)
                return;
            else{
                String pass = new String(txtpass.getPassword());
                String name = txtnombre.getText();
                long fec = getTime(txtfecha.getText());
                users.add(new Usuarios(name, usern, pass, fec));
                System.out.println("Usuario Creado");
                JOptionPane.showMessageDialog(this, "Usuario Creado Exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                exitAddUser();
            }
        }
    }
    
    private long getTime(String fecha){
        String fech[] = fecha.split("-");
        int d = Integer.parseInt(fech[0]);
        int m = Integer.parseInt(fech[1]);
        int a = Integer.parseInt(fech[2]);
        Calendar tmp = Calendar.getInstance();
        tmp.set(a, m, d);
        return tmp.getTimeInMillis();
    }
    
    private void exitLogin(){
        getContentPane().remove(txtuser);
        getContentPane().remove(txtpass);
        getContentPane().remove(user);
        getContentPane().remove(pass);
        login.setBounds(x, 133 -35 -5, 150, 35);
        addUser.setBounds(x, 133, 150, 35);
        salir.setBounds(x, 133 +35 +5, 150, 35);
        exit();
    }
    
    private void exitAddUser(){
        getContentPane().remove(txtuser);
        getContentPane().remove(txtpass);
        getContentPane().remove(user);
        getContentPane().remove(pass);
        getContentPane().remove(nombre);
        getContentPane().remove(fecha);
        getContentPane().remove(txtnombre);
        getContentPane().remove(txtfecha);
        login.setBounds(x, 133 -35 -5, 150, 35);
        addUser.setBounds(x, 133, 150, 35);
        salir.setBounds(x, 133 +35 +5, 150, 35);
        exit();
    }
    
    private void exit(){
        getContentPane().remove(btnaceptar);
        getContentPane().remove(btncancelar);
        changeOptionEnabled(true);
        getContentPane().repaint();
    }
    
    private void btncancelarActionPerformed(ActionEvent evt) {
        //Aqui el Codigo
        if (op == 'L'){
            exitLogin();
        }else{
            exitAddUser();
        }
            
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
        clicAddUser();
    }
    
    private void clicAddUser(){
        changeOptionEnabled(false);
        moveOption(login, -login.getY()+1);
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
        
        txtpass = new JPasswordField();
        getContentPane().add(txtpass);
        txtpass.setLocation(pass.getX()+pass.getWidth(), pass.getY());
        txtpass.setSize(150, 30);
        
        nombre = new Label();
        nombre.setText("Nombre: ");
        getContentPane().add(nombre);
        nombre.setLocation(pass.getX(), pass.getY()+35);
        nombre.setSize(70, 35);
        
        txtnombre = new JTextField();
        getContentPane().add(txtnombre);
        txtnombre.setLocation(nombre.getX()+nombre.getWidth(), nombre.getY());
        txtnombre.setSize(150, 30);
        
        fecha = new Label();
        fecha.setText("Fecha Nac.: ");
        getContentPane().add(fecha);
        fecha.setLocation(pass.getX(), nombre.getY()+35);
        fecha.setSize(70, 35);
        
        txtfecha = new JFormattedTextField();
        txtfecha.setToolTipText("dia-mes-year");
        txtfecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd-MM-yyyy"))));
        getContentPane().add(txtfecha);
        txtfecha.setLocation(fecha.getX()+fecha.getWidth(), fecha.getY());
        txtfecha.setSize(150, 30);
        
        getContentPane().add(btnaceptar);
        getContentPane().add(btncancelar);
        
        btnaceptar.setLocation(txtuser.getX()+txtuser.getWidth(), txtuser.getY());
        btnaceptar.setText("Agregar");
        btncancelar.setLocation(txtpass.getX()+txtpass.getWidth(), txtpass.getY());
    }
    
    private void salirActionPerformed(ActionEvent evt) {
        //Aqui el Codigo
        System.out.println("Salir");
        int op = JOptionPane.showConfirmDialog(this, "Desea Salir", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (op == JOptionPane.YES_OPTION){
            //Serializacion y Archivos Binarios
            dispose();
        }
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
        
        txtpass = new JPasswordField();
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
        setTitle("Connect Four - by NxRodriguez && RaimMaster");

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


