
package modelo;

import java.util.List;


public class Banco {
    /** Id banco
    * 
    */
   private int id;
   /** Nombre del Banco
    * 
    */
   private String razon;
   /** Path logo banco
    * 
    */
   private String logo;
   
   
   private List<Cuenta> cuenta;

    /**
     * Id banco
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Id banco
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Nombre del Banco
     * @return the razon
     */
    public String getRazon() {
        return razon;
    }

    /**
     * Nombre del Banco
     * @param razon the razon to set
     */
    public void setRazon(String razon) {
        this.razon = razon;
    }

    /**
     * Path logo banco
     * @return the logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     * Path logo banco
     * @param logo the logo to set
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * @return the cuenta
     */
    public List<Cuenta> getCuenta() {
        return cuenta;
    }

    /**
     * @param cuenta the cuenta to set
     */
    public void setCuenta(List<Cuenta> cuenta) {
        this.cuenta = cuenta;
    }
   
}
