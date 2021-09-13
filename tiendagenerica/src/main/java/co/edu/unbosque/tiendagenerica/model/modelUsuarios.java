package co.edu.unbosque.tiendagenerica.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class modelUsuarios {
	
	public List<usuario> usuarios;
	private final String rutaCSV = "usuarios.csv";
	
	//Se crea la lista en la que se guardaran los usuarios
	public void CrearLista() throws IOException {
		usuarios = new ArrayList<>();
		System.out.println("Lista creada");
		//Agregar usuario por defecto
		usuario admin = new usuario(1, "Administrador", "-", "admininicial" , "admin123456");
		this.AgregarUsuario(admin);		
		System.out.println("Admin agregado");
		this.CrearArchivoCSV();
		this.ObtenerUsuariosDeCSV();
	}
	
	//Se crea un archivo CSV
	private void CrearArchivoCSV() throws IOException {
		final String NEXT_LINE = "\n";
		final String delim = ",";
		String rutaDelArchivo = new File("").getAbsolutePath();
		System.out.println(rutaDelArchivo);
		FileWriter fw = new FileWriter(rutaCSV);
		try {
			for(usuario cacheUsuario : usuarios) {
				fw.append(Long.toString(cacheUsuario.getCedula())).append(delim);
				fw.append(cacheUsuario.getNombre()).append(delim);
				fw.append(cacheUsuario.getCorreo()).append(delim);
				fw.append(cacheUsuario.getUsuario()).append(delim);
				fw.append(cacheUsuario.getPassword()).append(NEXT_LINE);
				}
			fw.flush();
			fw.close();
			System.out.println("ArchivoCSV creado");
		}catch(IOException e) {
		System.out.println("Error al crear el archivo CSV");
		e.printStackTrace();
		}
	}
	
	//Modelo usado para buscar el usuario en la base de datos.
	public boolean AprobarUsuario(String usuario, String password) {
		boolean resultado = false;
		for (usuario usuarioEncontrado : usuarios) {
			if(usuarioEncontrado.getUsuario().equals(usuario) 
			&& usuarioEncontrado.getPassword().equals(password)) {
				resultado = true;
			}else {
				
			}
		}
		return resultado;
	}
	
	//Modelo usado para agregar usuarios a la base de datos
	public void AgregarUsuario(usuario usuario) throws IOException {
		usuarios.add(usuario);
		this.ActualizarListaUsuarios();
	}
	
	//Modelo usado para actualizar la lista de usuarios.
	public void ActualizarListaUsuarios() throws IOException {
		this.CrearArchivoCSV();
	}
	
	//Modelo usado para obtener el indice del usuario, usado en algunas funciones como borrar, o modificar.
	public int ObtenerIndiceUsuario(long cedula) {
		int i = 0;
		int id = -1;
		for(i=0;i<usuarios.size();i++) {
			if(usuarios.get(i).getCedula()==cedula) {
				id = i;
			}
			else id = -1;
		}
		return id;
	}
	
	//Modelo usado para buscar usuarios en la lista.
	public usuario BuscarUsuario(long cedula) {
		usuario usuarioEncontrado = null;
		for(usuario usuario : usuarios) {
			if(usuario.getCedula()==cedula) {
				usuarioEncontrado = usuario;
			}
		}
		return usuarioEncontrado;
	}
	
	
	//Modelo usado para modificar el usuario en la lista.
	public void ModificarUsuario(int id, usuario nuevosDatosUsuario) throws IOException {
		usuarios.set(id, nuevosDatosUsuario);
		this.ActualizarListaUsuarios();
		System.out.println("Se modificó el usuario");
		
	}
	
	//Modelo usado para eliminar el usuario de la lista y el CSV.
	public void EliminarUsuario(int id) throws IOException {
		usuarios.remove(id);
		this.ActualizarListaUsuarios();
		System.out.println("Se eliminó el usuario");
	}
	
	//Modelo para obtener los usuarios en el CSV.
	public void ObtenerUsuariosDeCSV() {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bReader = null;
		ArrayList<usuario> listResult = new ArrayList<usuario>();
		try {
			fis = new FileInputStream(rutaCSV);
			isr = new InputStreamReader(fis);
			bReader = new BufferedReader(isr);
			
		
		String line = null;
		String[]usuariosStrings = null;
		
		while(true) {
			line = bReader.readLine();
			if(line == null) {
				break;
			}else {
				usuariosStrings = line.split(",");
				listResult.add(new usuario(Long.parseLong(usuariosStrings[0]), 
						usuariosStrings[1],usuariosStrings[2] ,usuariosStrings[3], 
						usuariosStrings[4]));
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error leyendo el CSV");
			e.printStackTrace();
		}
		finally {
			try {
				bReader.close();
				isr.close();
				fis.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		this.usuarios = listResult;
	}
}
