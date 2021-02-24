/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


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
}
