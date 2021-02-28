/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

public class Cuenta {

    /**
     * Id cuenta
     *
     */
    /**
     * Id Banco
     */
    private int banId;
    /**
     * Id Cliente
     */
    private int cliId;

    private int id;
    /**
     * Numero Cuenta
     *
     */
    private String numero;
    /**
     * Tipo Cuenta
     *
     */
    private String tipo;
    /**
     * Saldo Cuenta
     *
     */
    private double saldo;

    private List<Transaccion> transaccion;

    private Cliente cliente;

    private Banco banco;

    /**
     * Id cuenta
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Id cuenta
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Numero Cuenta
     *
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Numero Cuenta
     *
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Tipo Cuenta
     *
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Tipo Cuenta
     *
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Saldo Cuenta
     *
     * @return the saldo
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Saldo Cuenta
     *
     * @param saldo the saldo to set
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * @return the transaccion
     */
    public List<Transaccion> getTransaccion() {
        return transaccion;
    }

    /**
     * @param transaccion the transaccion to set
     */
    public void setTransaccion(List<Transaccion> transaccion) {
        this.transaccion = transaccion;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the banco
     */
    public Banco getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    /**
     * Id Banco
     *
     * @return the banId
     */
    public int getBanId() {
        return banId;
    }

    /**
     * Id Banco
     *
     * @param banId the banId to set
     */
    public void setBanId(int banId) {
        this.banId = banId;
    }

    /**
     * Id Cliente
     *
     * @return the cliId
     */
    public int getCliId() {
        return cliId;
    }

    /**
     * Id Cliente
     *
     * @param cliId the cliId to set
     */
    public void setCliId(int cliId) {
        this.cliId = cliId;
    }

}
