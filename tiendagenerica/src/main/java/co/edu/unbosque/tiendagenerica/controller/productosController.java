package co.edu.unbosque.tiendagenerica.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class productosController {
	public String database = "tiendavirtualdb";
	public String url = "jdbc:mysql://localhost:3306/"+database;
	public String hostname = "localhost";
	public String username = "root";
	public String password="SuperUS123*";
	Connection conec = null;
	
	public Connection Conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conec = DriverManager.getConnection(url,username,password);
			System.out.println("Conexión exitosa");
		}catch(Exception e) {
			System.out.println("Conexión fallida "+e);
		}
		return conec;
	}
		
		
}

        