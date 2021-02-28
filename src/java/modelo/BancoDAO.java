package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BancoDAO {
  private Conexion con;
    private Connection connection;
    
    private int registros;

    public BancoDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }

   

    public List listar() {
        String sql = "select * from banco";
        List<Banco> lista = new ArrayList<>();
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Banco ban = new Banco();
                ban.setId(rs.getInt("id"));
                ban.setRazon(rs.getString("razon"));
                ban.setLogo(rs.getString("logo"));

                lista.add(ban);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public Banco getBanco(int id) {
        String sql = "select * from banco where id = " + id;
          Banco ban = new Banco();
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                 ban.setId(rs.getInt("id"));
                ban.setRazon(rs.getString("razon"));
                ban.setLogo(rs.getString("logo"));
                
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return ban;
    }

    public int agregar(Banco ban) {
        String sql = "insert into banco (razon, logo) values(?,?)";
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);           
            ps.setString(1,ban.getRazon());
            ps.setString(2, ban.getLogo());
            
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return registros;
    }

    public int actualizar(Banco ban) {
        String sql = "update banco set  razon=?, logo=? where id=?";
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, ban.getRazon());
            ps.setString(2, ban.getLogo());
            ps.setInt(3, ban.getId());

            ps.executeUpdate();
        } catch (Exception e) {

        }
        return registros;

    }
    

    public void eliminar(int id) {
        String sql = "delete from banco where id=" + id;
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.executeUpdate();
        } catch (Exception e) {
        }        }
        }


    
