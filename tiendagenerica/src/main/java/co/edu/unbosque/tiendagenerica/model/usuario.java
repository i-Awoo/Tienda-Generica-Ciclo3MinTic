package co.edu.unbosque.tiendagenerica.model;

public class usuario {

	private long cedula;
	private String nombre;
	private String correo;
	private String usuario;
	private String password;
	public long getCedula() {
		return cedula;
	}
	public void setCedula(long cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public usuario(long cedula, String nombre, String correo, String usuario, String password) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.correo = correo;
		this.usuario = usuario;
		this.password = password;
	}
	
	
}

