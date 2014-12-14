/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameVisual;

import Librerias.Usuarios;

import javax.swing.JOptionPane;

/**
 *
 * @author Raim
 */
public class Menu extends javax.swing.JFrame {
    public Usuarios loggedUser;
     
    /**
     * Creates new form Menu
     */
    public Menu(Usuarios u) {
        initComponents();
        loggedUser = u;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDMenus = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jmiPartidaNueva = new javax.swing.JMenuItem();
        jmiCargar = new javax.swing.JMenuItem();
        jmiEliminarPartida = new javax.swing.JMenuItem();
        jmiTransferir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuCerrarSesion = new javax.swing.JMenu();
        jMCerrarSesion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDMenusLayout = new javax.swing.GroupLayout(jDMenus);
        jDMenus.setLayout(jDMenusLayout);
        jDMenusLayout.setHorizontalGroup(
            jDMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        jDMenusLayout.setVerticalGroup(
            jDMenusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );

        jMenu1.setText("Jugar");

        jmiPartidaNueva.setText("Partida Nueva");
        jmiPartidaNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiPartidaNuevaActionPerformed(evt);
            }
        });
        jMenu1.add(jmiPartidaNueva);

        jmiCargar.setText("Cargar Partida");
        jmiCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCargarActionPerformed(evt);
            }
        });
        jMenu1.add(jmiCargar);

        jmiEliminarPartida.setText("Eliminar Partida");
        jmiEliminarPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEliminarPartidaActionPerformed(evt);
            }
        });
        jMenu1.add(jmiEliminarPartida);

        jmiTransferir.setText("Transferir Partida");
        jmiTransferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiTransferirActionPerformed(evt);
            }
        });
        jMenu1.add(jmiTransferir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Reportes");

        jMenuItem5.setText("Mis Ultimos Juegos");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem6.setText("Ranking");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Mi Perfil");

        jMenuItem7.setText("Eliminar Cuenta");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        jMenuCerrarSesion.setText("Cerrar Sesión");
        jMenuCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCerrarSesionActionPerformed(evt);
            }
        });

        jMCerrarSesion.setText("Cerrar Sesión");
        jMCerrarSesion.setToolTipText("");
        jMCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMCerrarSesionActionPerformed(evt);
            }
        });
        jMenuCerrarSesion.add(jMCerrarSesion);

        jMenuBar1.add(jMenuCerrarSesion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDMenus)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDMenus)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiPartidaNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiPartidaNuevaActionPerformed
        // TODO add your handling code here:
        PartidaNueva pn = new PartidaNueva(loggedUser);
        jDMenus.add(pn);
        pn.show();
    }//GEN-LAST:event_jmiPartidaNuevaActionPerformed

    private void jmiCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCargarActionPerformed
        // TODO add your handling code here:
        CargarPartida cp = new CargarPartida();
        jDMenus.add(cp);
        cp.show();
    }//GEN-LAST:event_jmiCargarActionPerformed

    private void jmiEliminarPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEliminarPartidaActionPerformed
        // TODO add your handling code here:
        EliminarPartida ep = new EliminarPartida();
        jDMenus.add(ep);
        ep.show();
    }//GEN-LAST:event_jmiEliminarPartidaActionPerformed

    private void jmiTransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiTransferirActionPerformed
        // TODO add your handling code here:
        EliminarPartida cp = new EliminarPartida();
        jDMenus.add(cp);
        cp.show();
    }//GEN-LAST:event_jmiTransferirActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        LogJuegos cp = new LogJuegos();
        jDMenus.add(cp);
        cp.show();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        Ranking cp = new Ranking();
        jDMenus.add(cp);
        cp.show();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane .showConfirmDialog(this, "¿Desea realmente eliminar su Usuario?",
                "Confirmacion", JOptionPane .YES_NO_OPTION) == JOptionPane .YES_OPTION){
            //EliminarUsuario
        }
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCerrarSesionActionPerformed
        // TODO add your handling code here:
        if (JOptionPane .showConfirmDialog(this, "¿Desea realmente salir del sistema?",
                "Salir del sistema", JOptionPane .YES_NO_OPTION) == JOptionPane .YES_OPTION)
            dispose();
    }//GEN-LAST:event_jMenuCerrarSesionActionPerformed

    private void jMCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMCerrarSesionActionPerformed
        // TODO add your handling code here:
        loggedUser = null;
        new MainConnectFour().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMCerrarSesionActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDMenus;
    private javax.swing.JMenuItem jMCerrarSesion;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuCerrarSesion;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jmiCargar;
    private javax.swing.JMenuItem jmiEliminarPartida;
    private javax.swing.JMenuItem jmiPartidaNueva;
    private javax.swing.JMenuItem jmiTransferir;
    // End of variables declaration//GEN-END:variables
}
