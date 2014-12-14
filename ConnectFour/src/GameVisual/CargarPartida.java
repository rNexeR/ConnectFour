/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameVisual;

import Librerias.Usuarios;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author KELVIN
 */
public class CargarPartida extends javax.swing.JInternalFrame {

    private Usuarios loggedUser;
    private String dir;
    /**
     * Creates new form CargarPartida
     * @param usuario
     */
    public CargarPartida(Usuarios usuario) {
        initComponents();        
        loggedUser = usuario;        
        dir = "GameFiles" + File.separator + "usuarios" + File.separator + loggedUser.getUsername();
        loadPartidasPendientes();        
    }
    
    private void loadPartidasPendientes(){
        File fi = new File(dir);                     
                       
        String [] files = fi.list();        
        for (String s : files){
            if (s.startsWith("partida")){                
                fi = new File(dir + File.separator + s);
                try {
                    RandomAccessFile rPartida = new RandomAccessFile(fi, "r");
                    //Correlativo del juego – Juego vs JUGADOR CONTRARIO iniciado el FECHA – Turno #
                    int numPartida = rPartida.readInt();
                    String userActual = rPartida.readUTF();
                    String adversario = rPartida.readUTF();
                    Date fecha = new Date(rPartida.readLong());
                    rPartida.skipBytes(4);
                    char estado = rPartida.readChar();
                    int turno = rPartida.readInt();
                    
                    rPartida.close();
                    Formatter formato = new Formatter();
                    formato.format("%d - %s VS %s - Iniciado en: %tc - Turno %d", numPartida, userActual, adversario 
                            , fecha, turno);
                    if (estado == 'P')
                        JCPartidas.addItem(formato.toString());                    
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Error",
                            "Error con los archivos", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        }        
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

        btnaceptar.setText("Cargar");
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JCPartidas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(btnaceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)
                        .addGap(0, 352, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(JCPartidas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnaceptar)
                    .addComponent(btnCancelar))
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        // TODO add your handling code here:
        try{            
            char correlativo = JCPartidas.getSelectedItem().toString().charAt(0);
            String filenamePartida = dir + File.separator +
                    "partida#" + correlativo + ".par";
            String filenameCirculos = dir + File.separator + "tableros" + File.separator 
                    + correlativo + ".ser";
            new GameCFour(loggedUser, filenamePartida, filenameCirculos /*anterior, partida; aqui, filename circulos */).setVisible(true);
            dispose();
        }catch (Exception ex){                           
            JOptionPane.showMessageDialog(this, "Error", "Error buscando archivos", 
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();

        }
        
    }//GEN-LAST:event_btnaceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JCPartidas;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnaceptar;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
