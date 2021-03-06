/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameVisual;

import Librerias.CircleLabels;
import Librerias.Partidas;
import Librerias.Usuarios;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Raim
 */
public class GameCFour extends JFrame {    
    private final static byte CANT_ROW = 6;
    private final static byte CANT_COL = 7;
    private final static byte CONNECTED_WIN = 4;//VALOR PARA GANAR
    private final static int SIZE_PIEZA = 70;
    
    private JPanel tablero;
    private JLayeredPane jlp;    
    
    private final Color background = new Color(200, 200, 200);//por si quiero probar algun color
    
    //private JLabel[][] circulos; //potencial a usar
    private CircleLabels cl;
    JPanel [][] square;
    private JButton col1, col2, col3, col4, col5, col6, col7;//para agregar circulos a cada columna
    private JButton pausa, retirar;
    
    private char colorActual = 'R', tipoInicio;
    private Usuarios loggedIn, usuarioActual;
    
    //Variables para la partida
    private String partidaCargada;
    private String tableroCargado;
    //private Partidas actual;
    private int numPartida;
    private Usuarios user1, user2;
    private long fecha;
    private char estadoPartida;
    private char resultadoPartida;
    private char tipoResultado;
    private int turno;
    
    //private Color c = new Color
    /**
     * Creates new form GameCFour
     */
    public GameCFour(Usuarios user1, Usuarios user2) {
        initComponents();
        tipoInicio = 'N';
        loggedIn = user1;
        this.user1 = user1;
        this.user2 = user2;
        createBoard();
        crearSquares();
        usuarioActual = user1;
        turno = 1;
        fecha = Calendar.getInstance().getTimeInMillis();
        numPartida = GameNumeraciones.getNextNumPartida(loggedIn);
    }
    
    public GameCFour(Usuarios loggedIn, String filenamePartida, String filenameCirculos){
        initComponents();
        loadPartida(filenamePartida);
        createBoard();
        tipoInicio = 'C';
        partidaCargada = filenamePartida;
        tableroCargado = filenameCirculos;
        this.loggedIn = loggedIn;
        //Extraer Serializados
        loadSquares(filenameCirculos);
        for (int i = 0; i < 7; i++) {
            System.out.println("Validando columnas llenas");
                switch (i){
                    case 0:
                        col1.setEnabled(validarColumnaLlena(i));
                        break;
                    case 1:
                        col2.setEnabled(validarColumnaLlena(i));
                        break;
                    case 2:
                        col3.setEnabled(validarColumnaLlena(i));
                        break;
                    case 3:
                        col4.setEnabled(validarColumnaLlena(i));
                        break;
                    case 4:
                        col5.setEnabled(validarColumnaLlena(i));
                        break;
                    case 5:
                        col6.setEnabled(validarColumnaLlena(i));
                        break;
                    case 6:
                        col7.setEnabled(validarColumnaLlena(i));
                        break;
                }
        }
        
    }
    
    public void createBoard(){
        setPreferredSize(new java.awt.Dimension(600, 700));
        Dimension boardSize = new Dimension(600, 600);
        jlp = new JLayeredPane();
        getContentPane().add(jlp);
        jlp.setPreferredSize(boardSize);
        
        tablero = new JPanel();
                
        jlp.add(tablero, JLayeredPane.DEFAULT_LAYER);
        tablero.setLayout( new GridLayout(6, 7));
        tablero.setPreferredSize(boardSize);
        tablero.setBounds(0, 70, boardSize.width, boardSize.height);
        
        getContentPane().add(tablero);
        square = new JPanel[CANT_ROW][CANT_COL];
        //MOMENTO DE PRUEBA DE AGREGAR UN CIRCULO
        
        addButtons();
        
        
        
//        
//        try{            
//            CircleLabels cl = new CircleLabels();
//            tablero.add(cl);
//            cl.setColor('D');
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//            ex.printStackTrace();
//        }
    }    
      

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(610, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 661, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 467, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void crearSquares(){
        for (int i = 0; i < CANT_ROW; i++) {
            for(int j = 0; j < CANT_COL; j++){
                square [i][j] = new JPanel( new BorderLayout() );
                CircleLabels cl = new CircleLabels();
                square[i][j].add(cl);
                cl.setColorIcon('D');
                tablero.add(square [i][j]);
                //int row = (i / CANT_ROW) % 2;
                //if (row == 0){//0, 2, 4                  
                square[i][j].setBackground(Color.white );    
                    //square[i][j].setBorder(new Border());
                //}else{                 
                //square[i][j].setBackground((i + j) % 2 == 0 ? Color.white : Color.black ); 
                //}
            }           
        }      
    }
    
    void loadSquares(String filename){
        crearSquares();
        try{
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            System.out.print(filename);
            char[][] colores = ((char[][])in.readObject());
            char color;
            
            for (int i = 0; i < CANT_ROW; i++) {
            for(int j = 0; j < CANT_COL; j++){
                color = colores[i][j];
                ((CircleLabels)square[i][j].getComponent(0)).setColorIcon(color);
            }
        }
        }catch (Exception e){
            System.out.println("Square: "+e.getMessage());
        }
    }
    
    void loadPartida(String filename){
        try{
            RandomAccessFile par = new RandomAccessFile(filename, "rw");
            numPartida = par.readInt();
            user1 = GameUsuarios.searchUser(par.readUTF());
            user2 = GameUsuarios.searchUser(par.readUTF());
            fecha = par.readLong();
            estadoPartida = par.readChar();
            resultadoPartida = par.readChar();
            tipoResultado = par.readChar();
            turno = par.readInt();
            
            colorActual = turno%2==0? 'A' : 'R';
            if (colorActual == 'R')
                usuarioActual = user1;
            else
                usuarioActual = user2;
            
        }catch (Exception e){
            System.out.println("Datos: "+e.getMessage());
        }
    }
    
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
            java.util.logging.Logger.getLogger(GameCFour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameCFour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameCFour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameCFour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameCFour(new Usuarios(), new Usuarios()).setVisible(true);
            }
        });
    }
    
    private boolean downCircle(int col){
        for (int i = CANT_ROW-1; i >= 0; i--) {
            CircleLabels este = (CircleLabels)square[i][col].getComponent(0);
            if (este.descripcion.equalsIgnoreCase("Ficha Blank")){
                este.setColorIcon(colorActual);
                //System.out.println(este.color);
                getContentPane().repaint();
                return fourConnected(i, col, colorActual);
            }
        }
        return false;
    }
    
    public boolean validarColumnaLlena(int col){
        for (int i = CANT_ROW-1; i >= 0; i--) {
            CircleLabels este = (CircleLabels)square[i][col].getComponent(0);
            if (este.descripcion.equalsIgnoreCase("Ficha Blank")){
                return true;
            }
        }
        return false;
    }
    
    private boolean validarGameOver(int col){
        boolean win = true;
        if (downCircle(col)){
            desactivarConectores();
            String nombre = colorActual=='R'?user1.getNombre():user2.getNombre();
            JOptionPane.showMessageDialog(this, "Ha ganado el jugador " + nombre, "ConnectFour", JOptionPane.INFORMATION_MESSAGE);
            if (colorActual == 'R'){
                user1.addWinPoints();
                writeLog(user1, numPartida, 'T', 'G', 'C');
                writeLog(user2, numPartida, 'T', 'P', 'C');
                terminarPartida('T', 'G', 'C');
            }else{
                user2.addWinPoints();
                writeLog(user2, numPartida, 'T', 'G', 'C');
                writeLog(user1, numPartida, 'T', 'P', 'C');
                terminarPartida('T', 'P', 'C');
            }
        }else if (empate()){
            desactivarConectores();
            JOptionPane.showMessageDialog(this, "Empate Declarado, han ganado 1 pt cada uno", "ConnectFour", JOptionPane.INFORMATION_MESSAGE);
            user1.addEmpatePoints();
            user2.addEmpatePoints();
            writeLog(user1, numPartida, 'T', 'E', 'C');
            writeLog(user2, numPartida, 'T', 'E', 'C');
            terminarPartida('T', 'E', 'C');
        }else{
            win = false;
            turno++;
            //colorActual = colorActual=='R'?'A':'R';
            if (colorActual == 'R'){
                colorActual = 'A';
                usuarioActual = user2;
                activarOpciones(false);
            }else{
                colorActual = 'R';
                usuarioActual = user1;
                activarOpciones(true);
            }
        }
        return win;
    }
    
    private void activarOpciones(boolean x){
        pausa.setEnabled(x);
    }
    
    private void col1ActionPerformed(ActionEvent evt) {
        //Aqui el codigo
        int col = 0;
        if (!validarGameOver(col))
            col1.setEnabled(validarColumnaLlena(col));
    }
    
    private void col2ActionPerformed(ActionEvent evt) {
        //Aqui el codigo
        int col = 1;
        if (!validarGameOver(col))
        col2.setEnabled(validarColumnaLlena(col));
    }
    
    private void col3ActionPerformed(ActionEvent evt) {
        //Aqui el codigo
        int col = 2;
        if (!validarGameOver(col))
        col3.setEnabled(validarColumnaLlena(col));
    }
    
    private void col4ActionPerformed(ActionEvent evt) {
        //Aqui el codigo
        int col = 3;
        if (!validarGameOver(col))
        col4.setEnabled(validarColumnaLlena(col));
    }
    
    private void col5ActionPerformed(ActionEvent evt) {
        //Aqui el codigo
        int col = 4;
        if (!validarGameOver(col))
        col5.setEnabled(validarColumnaLlena(col));
    }
    
    private void col6ActionPerformed(ActionEvent evt) {
        //Aqui el codigo
        int col = 5;
        if (!validarGameOver(col))
        col6.setEnabled(validarColumnaLlena(col));
    }
    
    private void col7ActionPerformed(ActionEvent evt) {
        //Aqui el codigo
        int col = 6;
        if (!validarGameOver(col))
        col7.setEnabled(validarColumnaLlena(col));
    }
    
    private void pausaActionPerformed(ActionEvent evt) {
        //Aqui el codigo
        if (JOptionPane .showConfirmDialog(this, "¿Desea realmente pausar la partida?", "Confirmacion", JOptionPane .YES_NO_OPTION) == JOptionPane .YES_OPTION){
            terminarPartida('P', 'V', 'V');
            JOptionPane.showMessageDialog(this, "Partida pausada exitosamente, puede reanudarla desde el menu principal", "ConnectFour", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }
    
    private void retirarActionPerformed(ActionEvent evt) {
        //Aqui el codigo
        if (JOptionPane .showConfirmDialog(this, "¿Desea realmente retirarse de la partida?", "Confirmacion", JOptionPane .YES_NO_OPTION) == JOptionPane .YES_OPTION){
            if (loggedIn == usuarioActual){
                if (user1 == loggedIn){
                    JOptionPane.showMessageDialog(this, "Jugador "+user1.getNombre()+" se retira, " + user2.getNombre()+" ha ganado", "ConnectFour", JOptionPane.INFORMATION_MESSAGE);
                    user2.addWinPoints();
                    writeLog(user2, numPartida, 'T', 'G', 'R');
                    writeLog(user1, numPartida, 'T', 'P', 'R');
                    terminarPartida('T', 'P', 'R');
                }else{
                    JOptionPane.showMessageDialog(this, "Jugador "+user2.getNombre()+" se retira, " + user1.getNombre()+" ha ganado", "ConnectFour", JOptionPane.INFORMATION_MESSAGE);
                    user1.addWinPoints();
                    writeLog(user1, numPartida, 'T', 'G', 'R');
                    writeLog(user2, numPartida, 'T', 'P', 'R');
                    terminarPartida('T', 'G', 'R');
                }
            }else{
                if (user1 != usuarioActual){
                    JOptionPane.showMessageDialog(this, "Jugador "+user1.getNombre()+" se retira, " + user2.getNombre()+" ha ganado", "ConnectFour", JOptionPane.INFORMATION_MESSAGE);
                    user2.addWinPoints();
                    writeLog(user2, numPartida, 'T', 'G', 'R');
                    writeLog(user1, numPartida, 'T', 'P', 'R');
                    terminarPartida('T', 'G', 'R');
                }else{
                    JOptionPane.showMessageDialog(this, "Jugador "+user2.getNombre()+" se retira, " + user1.getNombre()+" ha ganado", "ConnectFour", JOptionPane.INFORMATION_MESSAGE);
                    user1.addWinPoints();
                    writeLog(user1, numPartida, 'T', 'G', 'R');
                    writeLog(user2, numPartida, 'T', 'P', 'R');
                    terminarPartida('T', 'P', 'R');
                }
            }
            dispose();
        }
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void terminarPartida(char estado, char resultado, char tipoResultado){
        System.out.println("Finalizando Partida, tipo Inicio: " + tipoInicio);
        if (tipoInicio == 'N'){
            System.out.println("Guardando nueva partida, estado: " + estado);
            terminarPartidaNueva(estado, resultado, tipoResultado);
        }else{
            if (resultado == 'E'){
                System.out.println("Guardando nueva partida, estado: " + estado + " resultado: Empate");
                terminarPartidaCargada(estado, 'E', tipoResultado);
            }else if (resultado == 'G'){
                if (usuarioActual.getUsername().equals(loggedIn)){
                    System.out.println("Guardando nueva partida, estado: " + estado + " resultado: Ganada");
                    terminarPartidaCargada(estado, 'P', tipoResultado);
                }else{
                    System.out.println("Guardando nueva partida, estado: " + estado + " resultado: Perdida");
                    terminarPartidaCargada(estado, 'G', tipoResultado);
                }
            }else if (resultado == 'P'){
                if (usuarioActual.getUsername().equals(loggedIn)){
                    System.out.println("Guardando nueva partida, estado: " + estado + " resultado: Perdida");
                    terminarPartidaCargada(estado, 'G', tipoResultado);
                }else{
                    System.out.println("Guardando nueva partida, estado: " + estado + " resultado: Ganada");
                    terminarPartidaCargada(estado, 'P', tipoResultado);
                }
            }else if (resultado == 'V'){
                System.out.println("Guardando nueva partida, estado: " + estado + " resultado: Pendiente");
                terminarPartidaCargada(estado, resultado, tipoResultado);
            }
        }
    }
    
    private long searchLog(int numP){
        try{
            File in = new File("GameFiles" + File.separator + "usuarios" + File.separator + loggedIn.getUsername()+ File.separator + "juegos.log");
            RandomAccessFile juegos = new RandomAccessFile(in, "rw");
            while (juegos.getFilePointer()<=juegos.length()){
                int num = juegos.readInt();
                if (num == numP)
                    return juegos.getFilePointer()-4;
                juegos.readUTF();
                juegos.readUTF();
                juegos.skipBytes(18);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return -1;
    }
    
    private void writeLog(Usuarios loggedIn, int numP, char estado, char resultado, char tipoResultado){
        try{
            File in = new File("GameFiles" + File.separator + "usuarios" + File.separator + loggedIn.getUsername()+ File.separator + "juegos.cfl");
            RandomAccessFile juegos = new RandomAccessFile(in, "rw");
            
            juegos.seek(juegos.length());
            juegos.writeInt(numP);
            juegos.writeUTF(user1.getUsername());
            juegos.writeUTF(user2.getUsername());
            juegos.writeLong(fecha);
            juegos.writeChar(estado);
            juegos.writeChar(resultado);
            juegos.writeChar(tipoResultado);
            juegos.writeInt(turno);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
//    private void editLog(Usuarios loggedIn, int numP, char estado, char resultado, char tipoResultado){
//        try{
//            File in = new File("GameFiles" + File.separator + "usuarios" + File.separator + loggedIn.getUsername()+ File.separator + "juegos.cfl");
//            RandomAccessFile juegos = new RandomAccessFile(in, "rw");
//            long x = searchLog(numP);
//            if (x >= 0){
//                juegos.seek(x);
//                juegos.writeInt(numP);
//                juegos.readUTF();
//                juegos.readUTF();
//                juegos.readLong();
//                juegos.writeChar(estado);
//                juegos.writeChar(resultado);
//                juegos.writeChar(tipoResultado);
//                juegos.writeInt(turno);
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
    
    private void terminarPartidaNueva(char estado, char resultado, char tipoResultado){
        int numP = numPartida;
        int numT = GameNumeraciones.getNextNumTablero(loggedIn);
        //actual = new Partidas(numP, user1.getUsername(), user2.getUsername(), fecha, 'T', resultado, tipoResultado, turno);
        
        File n = new File("GameFiles" + File.separator + "usuarios" + File.separator + loggedIn.getUsername() + File.separator + "partida#"+ numP + ".par");
        try {
            RandomAccessFile m = new RandomAccessFile(n, "rw");
            m.writeInt(numP);
            m.writeUTF(user1.getUsername());
            m.writeUTF(user2.getUsername());
            m.writeLong(fecha);
            m.writeChar(estado);
            m.writeChar(resultado);
            m.writeChar(tipoResultado);
            m.writeInt(turno);
            m.close();
//            if (estado!='P'){
//                writeLog(usuarioActual, numP, estado, resultado, tipoResultado);
//                Usuarios usu2 = usuarioActual==user1?user2:user1;
//                resultado = resultado=='G'?'P':'G';
//                writeLog(usu2, numP, estado, resultado, tipoResultado);
//            }
            
            String dir = "GameFiles" + File.separator + "usuarios" + File.separator + loggedIn.getUsername() + File.separator + "tableros" + File.separator + numT +".ser";
            FileOutputStream fileOut = new FileOutputStream(dir);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            
            char[][] circulos = guardarSquares();
            
            
            out.writeObject(circulos);
            out.close();
            fileOut.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private char[][] guardarSquares(){
        char[][] circulos = new char[CANT_ROW][CANT_COL];
        for (int i = 0; i < CANT_ROW; i++) {
            for(int j = 0; j < CANT_COL; j++){
                circulos[i][j] = ((CircleLabels)square[i][j].getComponent(0)).color;
            }
        }
        return circulos;
    }
    
    private int getNumeroPartida(String filename){
        File n = new File(filename);
        int num = -1;
        try {
            RandomAccessFile m = new RandomAccessFile(n, "rw");
            num = m.readInt();
            m.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return num;
    }
    
    private void terminarPartidaCargada(char estado, char resultado, char tipoResultado){
        int num = getNumeroPartida(partidaCargada);
        File partida = new File(partidaCargada);
        partida.delete();
        try {
            RandomAccessFile m = new RandomAccessFile(partida, "rw");
            m.writeInt(num);
            m.writeUTF(user1.getUsername());
            m.writeUTF(user2.getUsername());
            m.writeLong(fecha);
            m.writeChar(estado);
            m.writeChar(resultado);
            m.writeChar(tipoResultado);
            m.writeInt(turno);
            
//            if (estado!='P'){
//                writeLog(usuarioActual, num, estado, resultado, tipoResultado);
//                Usuarios usu2 = usuarioActual==user1?user2:user1;
//                resultado = resultado=='G'?'P':'G';
//                writeLog(usu2, num, estado, resultado, tipoResultado);
//            }
            
            FileOutputStream fileOut = new FileOutputStream(tableroCargado);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            char[][] circulos = guardarSquares();
            
            out.writeObject(circulos);
            
            out.close();
            fileOut.close();
            m.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    private boolean fourConnected(int row, int col, char color){
        if (connectedLineH(row, color) || connectedLineV(col, color) || connectedLineDA(row, col, color) || connectedLineDD(row, col, color))
            return true;
        return false;
    }
    
    private boolean connectedLineH(int row, char color){
        CircleLabels h;
        int conectados = 0;
        for (int contador = 0; contador < 7; contador++) {
            h = (CircleLabels)square[row][contador].getComponent(0);
            if (h.color == color){
                conectados++;
                if (conectados>=4)
                    break;
            }else
                conectados=0;
        }
        if (conectados >= 4)
            return true;
        return false;
    }
    
    private boolean connectedLineV(int col, char color){
        CircleLabels h;
        int conectados = 0;
        for (int contador = 0; contador < 6; contador++) {
            h = (CircleLabels)square[contador][col].getComponent(0);
            if (h.color == color){
                conectados++;
                if (conectados>=4)
                    break;
            }else
                conectados = 0;
        }
        
        if (conectados >= 4)
            return true;
        return false;
    }
    
    private boolean connectedLineDD(int row, int col, char color){
        System.out.println(color);
        while(col>0 && row>0){
            col--;
            row--;
        }
        int contador = 0;
        CircleLabels h;
        
        while(col<=6 && row<=5){
            System.out.println(row + " - " + col);
            try{
                h = (CircleLabels)square[row][col].getComponent(0);
                System.out.println(h.color);
            }catch(Exception e){
                break;
            }
            if (h.color == color){
                contador++;
                if (contador>=4){
                    break;
                }
            }else
                contador = 0;
            col++; 
            row++;
        }
        if (contador>=4)
            return true;
        return false;
    }
    
    private boolean connectedLineDA(int row, int col, char color){
        while(col>0 && row<5){
            col--;
            row++;
        }
        int contador = 0;
        CircleLabels h;
        
        while(col<=6 && row>=0){
            try{
                h= (CircleLabels)square[row][col].getComponent(0);
            }catch(Exception e){
                break;
            }
            if (h.color == color){
                contador++;
                if (contador>=4){
                    break;
                }
            }else
                contador = 0;
            col++; 
            row--;
        }
        
        if (contador>=4)
            return true;
        return false;
    }
    
    private boolean empate(){
        CircleLabels h;
        for (int i = 0; i < CANT_ROW; i++) {
            for(int j = 0; j < CANT_COL; j++){
                h = (CircleLabels)square[i][j].getComponent(0);
                if (h.descripcion == "Ficha Blank")
                    return false;
            }
        }
        return true;
    }
    
    private void desactivarConectores(){
        col1.setEnabled(false);
        col2.setEnabled(false);
        col3.setEnabled(false);
        col4.setEnabled(false);
        col5.setEnabled(false);
        col6.setEnabled(false);
        col7.setEnabled(false);
        pausa.setEnabled(false);
        retirar.setEnabled(false);
    }
    
    private void addButtons() {
        String texto = "Conectar";
        Dimension dbotones = new Dimension(80, 30);
        
        col1 = new JButton();
        col1.setText(texto);
        col1.setSize(dbotones);
        getContentPane().add(col1);
        col1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                col1ActionPerformed(evt);
            }
        });
        
        col2 = new JButton();
        col2.setText(texto);
        col2.setSize(dbotones);
        getContentPane().add(col2);
        col2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                col2ActionPerformed(evt);
            }
        });
        
        col3 = new JButton();
        col3.setText(texto);
        col3.setSize(dbotones);
        getContentPane().add(col3);
        col3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                col3ActionPerformed(evt);
            }
        });
        
        col4 = new JButton();
        col4.setText(texto);
        col4.setSize(dbotones);
        getContentPane().add(col4);
        col4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                col4ActionPerformed(evt);
            }
        });
        
        col5 = new JButton();
        col5.setText(texto);
        col5.setSize(dbotones);
        getContentPane().add(col5);
        col5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                col5ActionPerformed(evt);
            }
        });
        
        col6 = new JButton();
        col6.setText(texto);
        col6.setSize(dbotones);
        getContentPane().add(col6);
        col6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                col6ActionPerformed(evt);
            }
        });
        
        col7 = new JButton();
        col7.setText(texto);
        col7.setSize(dbotones);
        getContentPane().add(col7);
        col7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                col7ActionPerformed(evt);
            }
        });
        int y = 35;
        col1.setLocation(0, y);
        col2.setLocation(85, y);
        col3.setLocation(170, y);
        col4.setLocation(255, y);
        col5.setLocation(340, y);
        col6.setLocation(425, y);
        col7.setLocation(510, y);
        
        JLabel etiqueta = new JLabel();
        etiqueta.setText("ConnectFour: " + user1.getUsername() + " vs " + user2.getUsername());
        etiqueta.setLocation(5, 5);
        etiqueta.setSize(250, 30);
        getContentPane().add(etiqueta);
        
         pausa = new JButton();
         pausa.setText("Pausar");
         pausa.setLocation(425, 5);
         pausa.setSize(80, 30);
         pausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pausaActionPerformed(evt);
            }
        });
         getContentPane().add(pausa);
         
         retirar = new JButton();
         retirar.setText("Retirar");
         retirar.setLocation(510, 5);
         retirar.setSize(80, 30);
         retirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                retirarActionPerformed(evt);
            }
        });
         getContentPane().add(retirar);
    }
}
