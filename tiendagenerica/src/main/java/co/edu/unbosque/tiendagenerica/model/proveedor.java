package co.edu.unbosque.tiendagenerica.model;

public class proveedor {

private long nit;
private String nombre;
private String direccion;
private String telefono;
private String ciudad;
public proveedor(long nit, String nombre, String direccion, String telefono, String ciudad) {
	super();
	this.nit = nit;
	this.nombre = nombre;
	this.direccion = direccion;
	this.telefono = telefono;
	this.ciudad = ciudad;
}
public long getNit() {
	return nit;
}
public void setNit(long nit) {
	this.nit = nit;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
public String getCiudad() {
	return ciudad;
}
public void setCiudad(String ciudad) {
	this.ciudad = ciudad;
}


	
}
