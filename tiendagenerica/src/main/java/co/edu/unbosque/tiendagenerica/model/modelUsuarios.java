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
	private final String rutaCSV = "C:/tmp/usuarios.csv";
	
	//Se crea la lista en la que se guardaran los usuarios
	public void CrearLista() throws IOException {
		usuarios = new ArrayList<>();
		System.out.println("Lista creada");

		if (ExisteArchivoCSV()) {System.out.println("CSV ya existe, omitiendo creación.");
		}
		else {
			System.out.println("Creando CSV");
			this.CrearArchivoCSV();
			//Agregar usuario por defecto
			usuario admin = new usuario(1, "Administrador", "-", "admininicial" , "admin123456");
			this.AgregarUsuario(admin);		
			System.out.println("Admin agregado");
		}
		
		this.ObtenerUsuariosDeCSV();
		
	}
	
	//Revisa si el archivo CSV ya existe.
	private boolean ExisteArchivoCSV() {
		File tmpDir = new File(rutaCSV);
		boolean existe = tmpDir.exists();
		return existe;
		
	}
	
	//Se crea un archivo CSV
	private void CrearArchivoCSV() throws IOException {
		final String NEXT_LINE = "\n";
		final String delim = ",";
		File f1 = new File("C:/tmp/");
		boolean bool = f1.mkdir();
		if (bool) System.out.println("Directorio creado");
		String rutaDelArchivo = new File("C:/tmp/").getAbsolutePath();
		
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
	
	//Chequea si el usuario con ese nÃºmero de cedula ya existe.
	public boolean UsuarioExiste(long cedula) {
		boolean Existe = false;
		for(usuario usuario : usuarios) {
			if(usuario.getCedula()==cedula) {
				Existe = true;
			}
		}
		System.out.println("Usuario Existe: "+Existe);
		return Existe;
	
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
				usuario tempUsuario = new usuario(0, "", "", "", "");
				tempUsuario.setCedula(Long.parseLong(usuariosStrings[0]));
				try {
				if (usuariosStrings[1].isEmpty()) {}
				else tempUsuario.setNombre(usuariosStrings[1]);
				}
				catch (Exception e) {
					System.out.println("Usuario con cedula "+usuariosStrings[0]+" no pudo agregarse correctamente, Falta nombre.");
				}
				try {
				if (usuariosStrings[2].isEmpty()) {}
				else tempUsuario.setCorreo(usuariosStrings[2]);
				}
				catch (Exception e) {
					System.out.println("Usuario con cedula "+usuariosStrings[0]+" no pudo agregarse correctamente, Falta correo.");
				}
				try {
				if (usuariosStrings[3].isEmpty()) {}
				else tempUsuario.setUsuario(usuariosStrings[3]);
				}catch (Exception e) {
					System.out.println("Usuario con cedula "+usuariosStrings[0]+" no pudo agregarse correctamente, Falta Usuario.");
				}
				try {
				if (usuariosStrings[4].isEmpty()) {}
				else tempUsuario.setPassword(usuariosStrings[4]);
				}				catch (Exception e) {
					System.out.println("Usuario con cedula "+usuariosStrings[0]+" no pudo agregarse correctamente, Falta contraseÃ±a.");
				}

				
				listResult.add(tempUsuario);
				
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
