package co.edu.unbosque.tiendagenerica.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.edu.unbosque.tiendagenerica.controller.productosController;

public class modelProductos {
	productosController cn = new productosController();
	Connection con = cn.Conectar();
	PreparedStatement ps = null;
	ResultSet res = null;
	

	public boolean cargar_productos(String url) {
		boolean resultado = false;
		try {
			String sql = "load data infile '?' into table productos fields terminated by ',' lines terminated by '\r\n'";
			ps = con.prepareStatement(sql);
			ps.setString(1, url);
			resultado = ps.executeUpdate()>0;
		}catch(SQLException ex){
			System.out.println("Error al insertar productos"+ex);
		}
		return resultado;
	}
}
