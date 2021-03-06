/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameVisual;

import Librerias.GamesPendientes;
import Librerias.UserNoLongerExistsException;
import Librerias.Usuarios;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author KELVIN
 */
public class TransferirPartida extends javax.swing.JInternalFrame {
    private Usuarios loggedUser;
    private String dir;
    /**
     * Creates new form TransferirPartida
     * @param usuario
     */
    public TransferirPartida(Usuarios usuario) {
        initComponents();
        initComponents();        
        loggedUser = usuario;        
        dir = "GameFiles" + File.separator + "usuarios" + File.separator + loggedUser.getUsername();
        loadPartidasPendientes(loggedUser); 
//        loadUsuarios(loggedUser);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        JCPartidas = new javax.swing.JComboBox();
        btnaceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        jLabel1.setText("Seleccione Partida:");

        btnaceptar.setText("Transferir");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JCPartidas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnaceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addGap(0, 264, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(JCPartidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnaceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private String getAdversario(char partida) throws UserNoLongerExistsException{
        String adversario = null;
        dir = "GameFiles" + File.separator + "usuarios" 
                + File.separator + loggedUser.getUsername() + File.separator;
        File fi = new File(dir);
        String files[] = fi.list();
        for (String s : files){
            if (s.startsWith("partida")){
                fi = new File(dir + File.separator + s);
                try {    
                    int numPartida;
                     //Correlativo del juego – Juego vs JUGADOR CONTRARIO iniciado el FECHA – Turno #
                     try (RandomAccessFile rPartida = new RandomAccessFile(fi, "rw")) {
                         //Correlativo del juego – Juego vs JUGADOR CONTRARIO iniciado el FECHA – Turno #
                         numPartida = rPartida.readInt();
                         String userActual = rPartida.readUTF();
                         adversario = rPartida.readUTF();                         
                         
                        if(GameUsuarios.searchUser(adversario) == null){
                            rPartida.close();
                            new File(dir + File.separator + s).delete();
                            throw new UserNoLongerExistsException("El usuario " + 
                                    adversario + " ya no existe. Borrando partida"); 
                        }
                         
                         Date fecha = new Date(rPartida.readLong());
                         char estado = rPartida.readChar();
                         char resultado = rPartida.readChar();
                         char tipoResultado = rPartida.readChar();
                         int turno = rPartida.readInt();
                         //Reescribir el archivo para invertir roles de adversario y user
                         rPartida.seek(4);
                         rPartida.writeUTF(adversario);
                         rPartida.writeUTF(userActual);
                         rPartida.writeLong(fecha.getTime());
                         rPartida.writeChar(estado);                         
                         rPartida.writeChar(resultado);                         
                         rPartida.writeChar(tipoResultado);
                         rPartida.writeInt(turno);
                    }
                    if (numPartida == (int)partida)
                        return adversario;                   
                } catch (IOException ex) {
                    System.out.println("Error generando Partidas Pendientes");
                }
            }
        }
        return adversario;
    }
    
    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        // TODO add your handling code here:
        try{
            if (JCPartidas.getItemCount()>0){
                char partida = JCPartidas.getSelectedItem().toString().charAt(0);
                if (JOptionPane .showConfirmDialog(this, "¿Desea realmente transferir la partida a su adversario?",
                        "Confirmacion", JOptionPane .YES_NO_OPTION) == JOptionPane .YES_OPTION){

                    File par = new File(dir + File.separator + "partida#" + partida + ".par");
                    File ser = new File(dir + File.separator + "tableros" + File.separator + partida + ".ser");
                    String user = getAdversario(partida);
                    int numP = GameNumeraciones.getNextNumPartida(GameUsuarios.searchUser(user));
                    //int numS = GameNumeraciones.getNextNumTablero(GameUsuarios.searchUser(user));

                    String dirPartida = "GameFiles" + File.separator + "usuarios" + File.separator
                            + user + File.separator + "partida#" + numP + ".par";

                    par.renameTo(new File(dirPartida));
                    ser.renameTo(new File("GameFiles" + File.separator + "usuarios"
                            + File.separator + user + File.separator 
                            + "tableros" + File.separator + numP + ".ser"));
                    cambiarNumPartida(numP, dirPartida);

                    JOptionPane.showMessageDialog(this, "Partida Transferida", "ConnectFour", 
                        JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
            }
        }catch (UserNoLongerExistsException unl){
            JOptionPane.showMessageDialog(this, unl.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
        
    }//GEN-LAST:event_btnaceptarActionPerformed
    
    private void cambiarNumPartida(int numP, String filename){
        try{
            File in = new File(filename);
            try (RandomAccessFile partida = new RandomAccessFile(in, "rw")) {
                partida.writeInt(numP);
            }
        }catch(IOException ex){
            JOptionPane.showMessageDialog(this, "Error al Transferir Partida \n" + ex.getMessage(), "Error", 
                    JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    
    private void loadPartidasPendientes(Usuarios logged){
        ArrayList<String> partidas = GamesPendientes.getPartidasPendientes(logged);
        for (String s : partidas){
            JCPartidas.addItem(s);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JCPartidas;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnaceptar;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
