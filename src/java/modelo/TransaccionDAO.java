package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransaccionDAO {

    private Conexion con;
    private Connection connection;
    private int r;

    public TransaccionDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
        System.out.println(jdbcURL);
        con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public List listar(String ctaNumero) {
        String sql = "select * from transaccion t inner join cuenta cta on (t.cue_id = cta.id)"
                + " inner join banco b on (cta.ban_id = b.id)"
                + " inner join cliente cli on (cta.cli_id = cli.id)"
                + " where cta.numero = ? "
                + " order by t.fechahora";
        List<Transaccion> lista = new ArrayList<>();
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ctaNumero);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Transaccion transaccion = new Transaccion();
                transaccion.setId(rs.getInt("id"));
                transaccion.setCueId(rs.getInt("cue_id"));
                transaccion.setFechahora(rs.getTimestamp("fechahora"));
                transaccion.setMonto(rs.getDouble("monto"));
                transaccion.setAccion(rs.getString("accion"));
                transaccion.setTipo(rs.getString("tipo"));
                transaccion.setBancotransfer(rs.getString("bancotransfer"));
                transaccion.setTipoctatransfer(rs.getString("tipoctatransfer"));
                transaccion.setNroctatransfer(rs.getString("nroctatransfer"));

                Cuenta cuenta = new Cuenta();
                cuenta.setId(rs.getInt("cue_id"));
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
                transaccion.setCuenta(cuenta);

                lista.add(transaccion);
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

    public int agregar(Transaccion transaccion) {
        String sql = "insert into transaccion ( cue_id,monto,accion,tipo,bancotransfer,tipoctatransfer,nroctatransfer) values(?,?,?,?,?,?,?)";
        try {
            con.conectar();
            connection = con.getJdbcConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, transaccion.getCueId());
            ps.setDouble(2, transaccion.getMonto());
            ps.setString(3, transaccion.getAccion());
            ps.setString(4, transaccion.getTipo());
            ps.setString(5, transaccion.getBancotransfer());
            ps.setString(6, transaccion.getTipoctatransfer());
            ps.setString(7, transaccion.getNroctatransfer());
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
