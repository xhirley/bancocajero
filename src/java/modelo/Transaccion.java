/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Timestamp;

/**
 *
 * @author lguzman
 */
public class Transaccion {

    /**
     * Id Transaccion
     *
     */
    private int id;
    
    /**
     * Cuenta Id
     */
    private int cueId;
    
    /**
     * Fecha Hora Transaccion
     *
     */
    private Timestamp fechahora;
    /**
     * Monto Transaccion
     *
     */
    private double monto;
    
    /**
     * Accion Deposito Retiro Transferencia
     */
    private String accion;
    /**
     * Tipo Transaccion Debito Credito
     *
     */
    private String tipo;
    /**
     * Banco Transferencia
     *
     */
    private String bancotransfer;
    /**
     * Tipocta Transferencia
     *
     */
    private String tipoctatransfer;
    /**
     * Nrocta Transferencia
     *
     */
    private String nroctatransfer;

    private Cuenta cuenta;

    /**
     * Id Transaccion
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Id Transaccion
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Fecha Hora Transaccion
     * @return the fechahora
     */
    public Timestamp getFechahora() {
        return fechahora;
    }

    /**
     * Fecha Hora Transaccion
     * @param fechahora the fechahora to set
     */
    public void setFechahora(Timestamp fechahora) {
        this.fechahora = fechahora;
    }

    /**
     * Monto Transaccion
     * @return the monto
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Monto Transaccion
     * @param monto the monto to set
     */
    public void setMonto(double monto) {
        this.monto = monto;
    }

    /**
     * Tipo Transaccion
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Tipo Transaccion
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Banco Transferencia
     * @return the bancotransfer
     */
    public String getBancotransfer() {
        return bancotransfer;
    }

    /**
     * Banco Transferencia
     * @param bancotransfer the bancotransfer to set
     */
    public void setBancotransfer(String bancotransfer) {
        this.bancotransfer = bancotransfer;
    }

    /**
     * Tipocta Transferencia
     * @return the tipoctatransfer
     */
    public String getTipoctatransfer() {
        return tipoctatransfer;
    }

    /**
     * Tipocta Transferencia
     * @param tipoctatransfer the tipoctatransfer to set
     */
    public void setTipoctatransfer(String tipoctatransfer) {
        this.tipoctatransfer = tipoctatransfer;
    }

    /**
     * Nrocta Transferencia
     * @return the nroctatransfer
     */
    public String getNroctatransfer() {
        return nroctatransfer;
    }

    /**
     * Nrocta Transferencia
     * @param nroctatransfer the nroctatransfer to set
     */
    public void setNroctatransfer(String nroctatransfer) {
        this.nroctatransfer = nroctatransfer;
    }

    /**
     * @return the cuenta
     */
    public Cuenta getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Accion Deposito Retiro Transferencia
     * @return the accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * Accion Deposito Retiro Transferencia
     * @param accion the accion to set
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    /**
     * Cuenta Id
     * @return the cueId
     */
    public int getCueId() {
        return cueId;
    }

    /**
     * Cuenta Id
     * @param cueId the cueId to set
     */
    public void setCueId(int cueId) {
        this.cueId = cueId;
    }
}
