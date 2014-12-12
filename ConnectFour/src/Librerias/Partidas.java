/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Librerias;

import java.util.Date;

/**
 *
 * @author rnexer
 */
public class Partidas implements java.io.Serializable{
    private int numPartida, turno;
    private String usuario, adversario;
    long fecha;
    char estado, resultado, tipoResultado;

    public Partidas(int numPartida, String usuario, String adversario, long fecha, char estado, char resultado, char tipoResultado, int turno) {
        this.numPartida = numPartida;
        this.usuario = usuario;
        this.adversario = adversario;
        this.fecha = fecha;
        this.estado = estado;
        this.resultado = resultado;
        this.tipoResultado = tipoResultado;
        this.turno = turno;
    }

    public int getNumPartida() {
        return numPartida;
    }

    public void setNumPartida(int numPartida) {
        this.numPartida = numPartida;
    }

    public String getAdversario() {
        return adversario;
    }

    public void setAdversario(String adversario) {
        this.adversario = adversario;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public char getResultado() {
        return resultado;
    }

    public void setResultado(char resultado) {
        this.resultado = resultado;
    }

    public char getTipoResultado() {
        return tipoResultado;
    }

    public void setTipoResultado(char tipoResultado) {
        this.tipoResultado = tipoResultado;
    }

    @Override
    public String toString() {
        return numPartida + "Juego vs " + adversario + "Iniciando el" + new Date(fecha) + "- Turno: " + turno;
    }
    
    
}
