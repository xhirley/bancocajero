package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private Conexion con;
    private Connection connection;
    private int registros;

    public ClienteDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public Cliente validar(String usuario, String clave) throws SQLException {
        String sql = "select * from cliente where  usuario=? and clave=?";
        Cliente cliente = new Cliente();
        con.conectar();
        connection = con.getJdbcConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, usuario);
        ps.setString(2, clave);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            cliente.setId(rs.getInt("id"));
            cliente.setUsuario(rs.getString("usuario"));
            cliente.setClave("CLAVE PROTEGIDA");
            cliente.setCedula(rs.getString("cedula"));
            cliente.setNombres(rs.getString("nombres"));
            cliente.setApellidos(rs.getString("apellidos"));
            cliente.setCorreo(rs.getString("correo"));
            cliente.setCelular(rs.getString("celular"));
            cliente.setEsadmin(rs.getBoolean("esadmin"));
        }
        rs.close();
        con.desconectar();
        return cliente;
    }

      public List listar() {
        String sql = "select * from cliente";
        List<Cliente> lista = new ArrayList<>();
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setClave("CLAVE PROTEGIDA");
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setEsadmin(rs.getBoolean("esadmin"));

                lista.add(cliente);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public Cliente getCliente(int id) {
        String sql = "select * from cliente where id = " + id;
        Cliente cliente = new Cliente();
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setClave("CLAVE PROTEGIDA");
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setEsadmin(rs.getBoolean("esadmin"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return cliente;
    }

    public Cliente getClientexCedula(String cedula) {
        String sql = "select * from cliente where cedula = '" + cedula + "'";
        Cliente cliente = new Cliente();
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setClave("CLAVE PROTEGIDA");
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setEsadmin(rs.getBoolean("esadmin"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return cliente;
    }

    public int agregar(Cliente cliente) {
        String sql = "insert into cliente (usuario, clave, cedula, nombres, apellidos, correo, celular, esadmin) values(?,?,?,?,?,?,?,?)";
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "");
            ps.setString(2, "");
            ps.setString(3, cliente.getCedula());
            ps.setString(4, cliente.getNombres());
            ps.setString(5, cliente.getApellidos());
            ps.setString(6, cliente.getCorreo());
            ps.setString(7, cliente.getCelular());
            ps.setBoolean(8, cliente.isEsadmin());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return registros;
    }

    public int actualizar(Cliente cliente) {
        String sql = "update cliente set cedula=?, nombres=?, apellidos=?,  correo=?, celular=?, esadmin=? where id=?";
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, cliente.getCedula());
            ps.setString(2, cliente.getNombres());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getCelular());
            ps.setBoolean(6, cliente.isEsadmin());
            ps.setInt(7, cliente.getId());

            ps.executeUpdate();
        } catch (Exception e) {

        }
        return registros;

    }

    public int guardarUsuario(int id, String usuario, String clave) {
        String sql = "update cliente set usuario=?, clave=? where id=?";
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, usuario);
            ps.setString(2, clave);
            ps.setInt(3, id);

            ps.executeUpdate();
        } catch (Exception e) {

        }
        return registros;

    }

    public void eliminar(int id) {
        String sql = "delete from cliente where id=" + id;
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.executeUpdate();
        } catch (Exception e) {
        }

    }

}
