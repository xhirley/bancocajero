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

    public List listar(String filtro) {
        String sql = "select * from cuenta cta inner join banco b on (cta.ban_id = b.id)"
                + " inner join cliente cli on (cta.cli_id = cli.id)"
                + " where cli.cedula like ? "
                + " or lower(cli.apellidos) like ?";
        List<Cuenta> lista = new ArrayList<>();
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, filtro);
            ps.setString(2, filtro);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id"));
                cuenta.setBanId(rs.getInt("ban_id"));
                cuenta.setCliId(rs.getInt("cli_id"));
                cuenta.setNumero(rs.getString("numero"));;
                cuenta.setTipo(rs.getString("tipo"));
                cuenta.setSaldo(rs.getDouble("saldo"));

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("cli_id"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setClave("CLAVE PROTEGIDA");
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setEsadmin(rs.getBoolean("esadmin"));

                Banco banco = new Banco();
                banco.setId(rs.getInt("ban_id"));
                banco.setRazon(rs.getString("razon"));
                banco.setLogo(rs.getString("logo"));

                cuenta.setBanco(banco);
                cuenta.setCliente(cliente);

                lista.add(cuenta);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public List listarCtasCliente(int idCliente) {
        String sql = "select * from cuenta cta inner join banco b on (cta.ban_id = b.id)"
                + " inner join cliente cli on (cta.cli_id = cli.id)"
                + " where cli.id = ? ";
        List<Cuenta> lista = new ArrayList<>();
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idCliente);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("id"));
                cuenta.setBanId(rs.getInt("ban_id"));
                cuenta.setCliId(rs.getInt("cli_id"));
                cuenta.setNumero(rs.getString("numero"));;
                cuenta.setTipo(rs.getString("tipo"));
                cuenta.setSaldo(rs.getDouble("saldo"));

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("cli_id"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setClave("CLAVE PROTEGIDA");
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setEsadmin(rs.getBoolean("esadmin"));

                Banco banco = new Banco();
                banco.setId(rs.getInt("ban_id"));
                banco.setRazon(rs.getString("razon"));
                banco.setLogo(rs.getString("logo"));

                cuenta.setBanco(banco);
                cuenta.setCliente(cliente);

                lista.add(cuenta);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public Cuenta getCuenta(int id) {
        String sql = "select * from cuenta cta inner join banco b on (cta.ban_id = b.id)"
                + " inner join cliente cli on (cta.cli_id = cli.id)"
                + " where cta.id =  ? ";

        Cuenta cuenta = new Cuenta();
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cuenta.setId(rs.getInt("id"));
                cuenta.setBanId(rs.getInt("ban_id"));
                cuenta.setCliId(rs.getInt("cli_id"));
                cuenta.setNumero(rs.getString("numero"));;
                cuenta.setTipo(rs.getString("tipo"));
                cuenta.setSaldo(rs.getDouble("saldo"));

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("cli_id"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setClave("CLAVE PROTEGIDA");
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setEsadmin(rs.getBoolean("esadmin"));

                Banco banco = new Banco();
                banco.setId(rs.getInt("ban_id"));
                banco.setRazon(rs.getString("razon"));
                banco.setLogo(rs.getString("logo"));

                cuenta.setBanco(banco);
                cuenta.setCliente(cliente);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return cuenta;
    }

    public Cuenta getCuentaxNumero(String ctaNumero) {
        String sql = "select * from cuenta cta inner join banco b on (cta.ban_id = b.id)"
                + " inner join cliente cli on (cta.cli_id = cli.id)"
                + " where cta.numero =  ? ";

        Cuenta cuenta = new Cuenta();
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ctaNumero);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cuenta.setId(rs.getInt("id"));
                cuenta.setBanId(rs.getInt("ban_id"));
                cuenta.setCliId(rs.getInt("cli_id"));
                cuenta.setNumero(rs.getString("numero"));;
                cuenta.setTipo(rs.getString("tipo"));
                cuenta.setSaldo(rs.getDouble("saldo"));

                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("cli_id"));
                cliente.setUsuario(rs.getString("usuario"));
                cliente.setClave("CLAVE PROTEGIDA");
                cliente.setCedula(rs.getString("cedula"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setCelular(rs.getString("celular"));
                cliente.setEsadmin(rs.getBoolean("esadmin"));

                Banco banco = new Banco();
                banco.setId(rs.getInt("ban_id"));
                banco.setRazon(rs.getString("razon"));
                banco.setLogo(rs.getString("logo"));

                cuenta.setBanco(banco);
                cuenta.setCliente(cliente);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return cuenta;
    }

    public int agregar(Cuenta cuenta) {
        String sql = "insert into cuenta ( ban_id, cli_id, numero,tipo,saldo) values(?,?,?,?,?)";
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cuenta.getBanId());
            ps.setInt(2, cuenta.getCliId());
            ps.setString(3, cuenta.getNumero());
            ps.setString(4, cuenta.getTipo());
            ps.setDouble(5, cuenta.getSaldo());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return r;
    }

    public int actualizarSaldo(int id, String tipo, double valor) {
        String sql = "update cuenta set saldo= saldo + ?  where id=?";
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            if (tipo.equals("DEBITO")) {
                valor *= -1;
            }
            ps.setDouble(1, valor);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println(e.getMessage());
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
