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
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
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
    
    /**
     * Creates new form MainConnectFour
     */
    public MainConnectFour() {
        initComponents();
        ImageIcon icono = new ImageIcon("icono.png"); 
        this.setIconImage(icono.getImage());
        GameUsuarios.loadUsers();
        createOptions();
    }
    
    private void createOptions(){
        login = new JButton();
        login.setText("Iniciar Sesion");
        getContentPane().add(login);
        login.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        
        addUser = new JButton();
        addUser.setText("Crear Usuario");
        getContentPane().add(addUser);
        addUser.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserActionPerformed(evt);
            }
        });
        
        salir = new JButton();
        salir.setText("Salir");
        getContentPane().add(salir);
        salir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        
        btnaceptar = new JButton();
        btnaceptar.setText("Aceptar");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });
        
        btncancelar = new JButton();
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
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
        
    /**
     * Depdendiendo de la opción, realizará el aceptar para 
     * agregar usuarios o para el login
     * @param evt 
     */
    private void btnaceptarActionPerformed(ActionEvent evt) {
        //Aqui el Codigo
        if (op == 'L'){
            loginActions();
        }else{
            //addUser
            agregarUsuariosActions();
        }
    }
    
    /**
     * Realiza los procesos necesarios para inicar sesión
     */
    private void loginActions(){
        if (GameUsuarios.users.size() > 0){
            String user1 = txtuser.getText();
            Usuarios buscar = GameUsuarios.searchUser(user1);
            if (buscar != null){
                String passwd = new String(txtpass.getPassword());
                if (buscar.getPassword().equals(passwd)){
                    System.out.println("Sesion Iniciada");
                    //Llamar Formulario de ConnectFour
                    exitLogin();
                    new Menu(buscar).setVisible(true);
                    this.dispose();
                }else
                    JOptionPane.showMessageDialog(this, "Usuario y Contraseña no coinciden", "Error al Inicar Sesion", 
                    JOptionPane.INFORMATION_MESSAGE);                
            }else
                JOptionPane.showMessageDialog(this, "Usuario no Encontrado", "Error al Inicar Sesion", 
                    JOptionPane.INFORMATION_MESSAGE);            
        }else{                
            JOptionPane.showMessageDialog(this, "No hay registros de Usuarios creados", "Error al Inicar Sesion",
                    JOptionPane.INFORMATION_MESSAGE);               
            exitLogin();         
        }
    }
    
    /**
     * Realiza los procesos necesarios para agregar un usuario
     */
    private void agregarUsuariosActions(){
        String usern = txtuser.getText();         
        Usuarios buscar = GameUsuarios.searchUser(usern);
        if (buscar != null){
            JOptionPane.showMessageDialog(this, "Usuario ya Existe", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            exitAddUser();         
        }else{                
            try{
                String password = new String(txtpass.getPassword());
                String name = txtnombre.getText();
                long fec = getTime(txtfecha.getText());
                GameUsuarios.users.add(new Usuarios(name, usern, password, fec));
                System.out.println("Usuario Creado");
                GameUsuarios.saveUsers();                
                    
                File userFile = new File("GameFiles" + File.separator
                        + "usuarios" + File.separator + usern + File.separator + "tableros");
                userFile.mkdirs();
                userFile = new File("GameFiles" + File.separator
                        + "usuarios" + File.separator + usern + File.separator + "juegos.cfl");
                userFile.createNewFile();
                crearNumeraciones(usern);
                JOptionPane.showMessageDialog(this, "Usuario Creado Exitosamente", 
                        "Informacion", JOptionPane.INFORMATION_MESSAGE);
                exitAddUser();                
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(this, "Llene todos los campos con el formato requerido, de tenerlo",                            
                        "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Campos Vacios");               
            }catch(NumberFormatException ex){                  
                JOptionPane.showMessageDialog(this, "Llene todos los campos con el formato requerido, de tenerlo", 
                            "Error", JOptionPane.ERROR_MESSAGE);             
            }catch (Exception ex){               
                JOptionPane.showMessageDialog(this, "Revise sus datos de ingreso.", 
                            "Error", JOptionPane.ERROR_MESSAGE);            
            }   
        }
    }
    
    /**
     * Crea el archivo de numeraciones para el usuario
     * @param name Username del jugador
     */
    private void crearNumeraciones(String name){
        try {
            String dirUsers = "GameFiles" + File.separator + "usuarios"
                    + File.separator + name;
            File user = new File(dirUsers + File.separator + "tableros");
            user.mkdirs();
            RandomAccessFile numSing;   
            //Archivo numeración en la carpeta de tableros
            try ( RandomAccessFile u = new RandomAccessFile(dirUsers + File.separator + "tableros"
                            + File.separator + "numeracion.num", "rw")) {
                u.writeInt(1);
                //Archivo numeración en la carpeta del usuario
                user = new File(dirUsers + File.separator + "numeracion.num");
                numSing = new RandomAccessFile(user, "rw");
                numSing.writeInt(1);
            }
            numSing.close();
        } catch (IOException ex) {
            System.out.println("Error al crear directorios de Usuario");
        }
    }
    
    private long getTime(String fecha){
        String fech[] = fecha.split("-");
        int d = Integer.parseInt(fech[0]);
        int m = Integer.parseInt(fech[1])-1;
        int a = Integer.parseInt(fech[2]);
        Calendar tmp = Calendar.getInstance();
        tmp.set(a, m, d);
        return tmp.getTimeInMillis();
    }
    
    /**
     * Remueve todos los objetos relacionados al login
     */
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
    
    /**
     * Remueve todos los objetos relaciones al
     * agregar usuarios
     */
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
    
    /**
     * Quita los botones de aceptar y cancelar
     * para los forms auxiliares
     */
    private void exit(){
        getContentPane().remove(btnaceptar);
        getContentPane().remove(btncancelar);
        changeOptionEnabled(true);
        getContentPane().repaint();
    }
    
    /**
     * Dependiendo de la opción, se sale del login o agregar usuarios
     * @param evt 
     */
    private void btncancelarActionPerformed(ActionEvent evt) {
        //Aqui el Codigo
        if (op == 'L'){
            exitLogin();
        }else{
            exitAddUser();
        }
            
    }
    
    /**
     * Ejecuta la función <code>clicLogin()</code>
     * @param evt 
     */
    private void loginActionPerformed(ActionEvent evt) {
        //Aqui el codigo
        System.out.println("Login");
        op = 'L';
        clicLogin();
    }
    
    /**
     * Ejecuta la función <code>clicAddUser()</code>
     * @param evt 
     */
    private void addUserActionPerformed(ActionEvent evt) {
        //Aqui el Codigo
        System.out.println("AddUser");
        op = 'C';
        clicAddUser();
    }
    
    /**
     * Settea los fields y botones para agregar
     * más usuarios al juego
     */
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
    
    /**
     * Evento para salir del form (y todo el juego)
     * @param evt 
     */
    private void salirActionPerformed(ActionEvent evt) {
        //Aqui el Codigo
        System.out.println("Salir");
        int op = JOptionPane.showConfirmDialog(this, "Desea Salir", "Confirmacion",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (op == JOptionPane.YES_OPTION){
            //Serializacion y Archivos Binarios
            dispose();
        }
    }
    
    /**
     * Cambia el estado de disponibilidad
     * de las demás opciones
     * @param state 
     */
    private void changeOptionEnabled(boolean state){
        login.setEnabled(state);
        addUser.setEnabled(state);
        salir.setEnabled(state);
    }
    
    /**
     * Muestra los JTextFields y botones para 
     * loggearse en el juego al elegir la opción de iniciar sesión
     */
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
        btnaceptar.setText("Aceptar");
        btncancelar.setLocation(txtpass.getX()+txtpass.getWidth(), txtpass.getY());
    }
    
    /**
     * Settea las posiciones de los botones al cambiar de opción
     * @param x Posición en x del botón
     * @param y Posición en y del botón
     */
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
        setTitle("Connect Four - by NxRodriguez && Raimmaster");

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


