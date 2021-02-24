
package modelo;

import java.util.List;

public class Cliente {
    
    /** Id secuencial cliente
    * 
    */
   private int id;
   /** Usuario cliente
    * 
    */
   private String usuario;
   /** Clave cliente
    * 
   */
   private String clave;
   /** Cedula clilente
    * 
   */
   private String cedula;
   /** Nombres cliente
    * 
    */
   private String nombres;
   /** Apellidos cliente
    * 
    */
   private String apellidos;
   /** Correo cliente
    * 
  */
   private String correo;
   /** Celular cliente
    * 
    */
   private String celular;
   
    /** Es administrador
    * 
     */
   private boolean esadmin;
   
   /**
    * Relacion Cuenta
    */
   private List<Cuenta> cuenta;

    /**
     * Id secuencial cliente
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Id secuencial cliente
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Usuario cliente
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Usuario cliente
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * Clave cliente
     * @return the clave
     */
    public String getClave() {
        return clave;
    }

    /**
     * Clave cliente
     * @param clave the clave to set
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Cedula clilente
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Cedula clilente
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Nombres cliente
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Nombres cliente
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Apellidos cliente
     * @return the apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Apellidos cliente
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Correo cliente
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Correo cliente
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Celular cliente
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * Celular cliente
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * Relacion Cuenta
     * @return the cuenta
     */
    public List<Cuenta> getCuenta() {
        return cuenta;
    }

    /**
     * Relacion Cuenta
     * @param cuenta the cuenta to set
     */
    public void setCuenta(List<Cuenta> cuenta) {
        this.cuenta = cuenta;
    }

    /**
     * Es administrador
     * @return the esadmin
     */
    public boolean isEsadmin() {
        return esadmin;
    }

    /**
     * Es administrador
     * @param esadmin the esadmin to set
     */
    public void setEsadmin(boolean esadmin) {
        this.esadmin = esadmin;
    }

    
    
    
    
    
}
