/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Prueba;

public class PruebaDAO {
	private Conexion con;
	private Connection connection;
 
	public PruebaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) throws SQLException {
		System.out.println(jdbcURL);
		con = new Conexion(jdbcURL, jdbcUsername, jdbcPassword);
	}
 
	// insertar artículo
	public boolean insertar(Prueba prueba) throws SQLException {
		String sql = "INSERT INTO pruebas (codigo, descripcion) VALUES (?, ?)";
		System.out.println(prueba.getDescripcion());
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);		
		statement.setString(1, prueba.getDescripcion());
 
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowInserted;
	}
 
	// listar todos los productos
	public List<Prueba> listarPruebas() throws SQLException {
 
		List<Prueba> listaPruebas = new ArrayList<Prueba>();
		String sql = "SELECT * FROM public.prueba";
		con.conectar();
		connection = con.getJdbcConnection();
		Statement statement = connection.createStatement();
		ResultSet resulSet = statement.executeQuery(sql);
 
		while (resulSet.next()) {
			int codigo = resulSet.getInt("codigo");			
			String descripcion = resulSet.getString("descripcion");			
			Prueba prueba = new Prueba(codigo, descripcion);
			listaPruebas.add(prueba);
		}
		con.desconectar();
		return listaPruebas;
	}
 
	// obtener por id
	public Prueba obtenerPorId(int codigo) throws SQLException {
		Prueba prueba = null;
 
		String sql = "SELECT * FROM pruebas WHERE codigo= ? ";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, codigo);
 
		ResultSet res = statement.executeQuery();
		if (res.next()) {
			prueba = new Prueba(res.getInt("codigo"),res.getString("descripcion"));
		}
		res.close();
		con.desconectar();
 
		return prueba;
	}
 
	// actualizar
	public boolean actualizar(Prueba prueba) throws SQLException {
		boolean rowActualizar = false;
		String sql = "UPDATE pruebas SET descripcion=? WHERE codigo=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);		
		statement.setString(1, prueba.getDescripcion()); 
		rowActualizar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
		return rowActualizar;
	}
	
	//eliminar
	public boolean eliminar(Prueba prueba) throws SQLException {
		boolean rowEliminar = false;
		String sql = "DELETE FROM pruebas WHERE codigo=?";
		con.conectar();
		connection = con.getJdbcConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, prueba.getCodigo());
 
		rowEliminar = statement.executeUpdate() > 0;
		statement.close();
		con.desconectar();
 
		return rowEliminar;
	}
}