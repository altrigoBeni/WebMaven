package com.cice.dataSourceTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SNIHostName;

import com.cice.dataSourceTest.dao.UserDao;


public class Main {

	static Connection conn = null;
	static PreparedStatement stm = null;
	
	
	
	public static void main(String[] args) {
		
		makeJDBCConnection();
		/*
		UserDao dao = new UserDao("Blas", "De Lezo", "6546843543");
		try {
			addDataToDB(dao);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
		
		
		List<UserDao> miTabla = getDataFromDB();
		if (miTabla != null) {
			for (UserDao DdbbUser : miTabla) {
				System.out.println(DdbbUser.toString());
			}
		}
		
		try {
			findUser(2,null);	
			findUser(null,"Alberto");			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void makeJDBCConnection() {		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			log("Felicidades -  MySQL JDBC Driver Registrado!");
		} catch (ClassNotFoundException e) {
			log("Mierda, no hay pelotas a encontrar el puto JDBC driver");
			e.printStackTrace();
			return;
		}

		try {
			// DriverManager: The basic service for managing a set of JDBC drivers.
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/datatestmaven", "root", "root");
			if (conn != null) {
				log("Connection Successful! Toma toma toma");
			} else {
				log("Failed to make connection!");
			}
		} catch (SQLException e) {
			log("MySQL Connection a pegao un pete de locos!");
			e.printStackTrace();
			return;
		}

	}
	
	
	private static List<UserDao> getDataFromDB() {
		List<UserDao> usuarios = new ArrayList<UserDao>();
		try {
			// MySQL Select Query Tutorial
			String getQueryStatement = "SELECT * FROM users";

			stm = conn.prepareStatement(getQueryStatement);

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				String nombre = rs.getString("name");
				String apellido = rs.getString("surname");
				int id = rs.getInt("id");
				String telefono = rs.getString("telefono");
				String mail = rs.getString("mail");
				String pass = rs.getString("pass");
				UserDao usuarioRegistro = new UserDao(nombre, apellido, telefono,mail,pass, id);
				usuarios.add(usuarioRegistro);

			}

		} catch (

		SQLException e) {
			usuarios = null;
			e.printStackTrace();
		}

		return usuarios;

	}
	
	
	private static void addDataToDB(UserDao userDao) throws SQLException {

		String insertQueryStatement = "INSERT INTO `users`(`name`, `surname`, `telefono`) VALUES (?,?,?)";

		stm = conn.prepareStatement(insertQueryStatement);
		stm.setString(1, userDao.getNombre());
		stm.setString(2, userDao.getApellido());
		stm.setString(3, userDao.getTelefono());		
		stm.executeUpdate();
		log(userDao.toString() + " added successfully soy el rey del mundo");

	}
	
	
	private static void log(String log) {
		System.out.println(log);
	}
	
	
	private static void findUser(Integer id,String userName) throws SQLException{		

		List<UserDao> usuarios = new ArrayList<UserDao>();
		String getQueryStatement=null;
		if (userName!=null) {
			getQueryStatement = "SELECT * FROM users WHERE users.name ='" + userName + "'";
		}else if (id!=null) {
			getQueryStatement = "SELECT * FROM users WHERE users.id =" + id;
		}		
		stm = conn.prepareStatement(getQueryStatement);
		ResultSet rs = stm.executeQuery();			
		
		while ((rs.next()) && (rs!=null)) {
			String nombre = rs.getString("name");
			String apellido = rs.getString("surname");
			String telefono = rs.getString("telefono");
			String mail = rs.getString("mail");
			String pass = rs.getString("pass");
			UserDao usuarioRegistro = new UserDao(nombre, apellido, telefono,mail,pass, id);
			usuarios.add(usuarioRegistro);
		}

		if (usuarios!=null) {
			System.out.println(usuarios.toString());
		}else {
			System.out.println("No user found!!!");
		}
	}
	
	
	

}
