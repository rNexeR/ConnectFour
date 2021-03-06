/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameVisual;

import Librerias.Usuarios;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.Formatter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author KELVIN
 */
public class LogJuegos extends javax.swing.JInternalFrame {

    /**
     * Creates new form LogJuegos
     */
    public LogJuegos(Usuarios loggedUser) {
        initComponents();
        loadJuegos(loggedUser);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbJuegos = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();

        tbJuegos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero", "Usuario", "Adversario", "Fecha", "Estado", "Resultado", "Via"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbJuegos);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCerrar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbJuegos;
    // End of variables declaration//GEN-END:variables

    private void loadJuegos(Usuarios loggedUser) {
        DefaultTableModel modelo = (DefaultTableModel)tbJuegos.getModel();
        try{
            File in = new File("GameFiles" + File.separator + "usuarios" + File.separator + loggedUser.getUsername()+ File.separator + "juegos.cfl");
            RandomAccessFile rPartida = new RandomAccessFile(in, "rw");
            while (rPartida.getFilePointer()<=rPartida.length()){
                System.out.print("Cargando una partida");
                    int numPartida = rPartida.readInt();
                    String userActual = rPartida.readUTF();
                    String adversario = rPartida.readUTF();
                    Date fecha = new Date(rPartida.readLong());                    
                    char estado = rPartida.readChar();
                    char resultado = rPartida.readChar();
                    char tipoResultado = rPartida.readChar();
                    int turno = rPartida.readInt();
                    
                    String state = estado=='T'?"Terminada":"Pendiente";
                    String result = null;
                    String tipoR = "Pendiente";
                    switch (resultado){
                        case 'G':
                            result = "Ganada";
                            break;
                        case 'E':
                            result = "Empatada";
                            break;
                        case 'P':
                            result = "Perdida";
                            break;
                        default:
                            result = "Pendiente";
                    }
                    if (tipoResultado != 'V'){
                        tipoR = tipoResultado == 'C'?"ConnectFour":"Retiro";
                    }
                    modelo.addRow(new Object[]{numPartida, userActual, adversario, fecha, state, result, tipoR});
            }
            rPartida.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
