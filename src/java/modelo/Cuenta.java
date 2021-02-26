/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Collection;


public class Cuenta {
    /** Id cuenta
    * 
  */
   public int id;
   /** Numero Cuenta
    * 
    */
   public String numero;
   /** Tipo Cuenta
    * 
   */
   public String tipo;
   /** Saldo Cuenta
    * 
    */
   public double saldo;
   
  
   public java.util.Collection<Transaccion> transaccion;
  
   public Cliente cliente;
   
   public Banco banco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Collection<Transaccion> getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Collection<Transaccion> transaccion) {
        this.transaccion = transaccion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }
   
   
}
