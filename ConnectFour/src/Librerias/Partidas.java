/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Librerias;

/**
 *
 * @author rnexer
 */
public class Partidas {
    private int numPartida;
    private String adversario;
    long fecha;
    char estado, resultado, tipoResultado;

    public Partidas(int numPartida, String adversario, long fecha, char estado, char resultado, char tipoResultado) {
        this.numPartida = numPartida;
        this.adversario = adversario;
        this.fecha = fecha;
        this.estado = estado;
        this.resultado = resultado;
        this.tipoResultado = tipoResultado;
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
    
}
