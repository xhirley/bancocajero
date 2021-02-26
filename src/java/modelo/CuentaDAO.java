
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CuentaDAO {
    

    private Conexion con;
    private Connection connection;
    private int r;

    public CuentaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public List listar() {
        String sql = "select * from cuenta";
        List<Cuenta> lista = new ArrayList<>();
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id"));
                cuenta.setNumero(rs.getString("numero"));;
                cuenta.setTipo(rs.getString("tipo"));
                cuenta.setSaldo(rs.getDouble("saldo"));

                lista.add(cuenta);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public Cuenta getCuenta(int id) {
        String sql = "select * from cuenta where id = " + id;
        Cuenta cuenta = new Cuenta();
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cuenta.setId(rs.getInt("id"));
                cuenta.setNumero(rs.getString("numero"));;
                cuenta.setTipo(rs.getString("tipo"));
                cuenta.setSaldo(rs.getDouble("saldo"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return cuenta;
    }

    public int agregar(Cuenta cuenta) {
        String sql = "insert into cuenta (id, ban_id, cli_id, numero,tipo,saldo) values(?,?,?,?,?,?)";
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cuenta.getId());
            ps.setString(2, "");
            ps.setString(3, "");
            ps.setString(4, cuenta.getTipo());
            ps.setDouble(6, cuenta.getSaldo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return r;
    }

    public int actualizar(Cuenta cuenta) {
        String sql = "update cuenta set saldo=?  where id=?";
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setDouble(1, cuenta.getSaldo());
            ps.setInt(2, cuenta.getId());

            ps.executeUpdate();
        } catch (Exception e) {

        }
        return r;

    }
    

    public void eliminar(int id) {
        String sql = "delete from cuenta where id=" + id;
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.executeUpdate();
        } catch (Exception e) {
        }

    }
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
