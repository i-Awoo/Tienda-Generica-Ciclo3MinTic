package co.edu.unbosque.tiendagenerica.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class modelClientes {
	
	public List<cliente> clientes;
	private final String rutaCSV = "C:/tmp/clientes.csv";
	
	//Se crea la lista en la que se guardaran los clientes
	public void CrearLista() throws IOException {
		clientes = new ArrayList<>();
		System.out.println("Lista creada");

		if (ExisteArchivoCSV()) {System.out.println("CSV:Clientes ya existe, omitiendo creación.");
		}
		else {
			System.out.println("Creando CSV:Clientes");
			this.CrearArchivoCSV();
		}
		
		this.ObtenerDeCSV();
		
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
		String rutaDelArchivo = new File("C:/tmp/").getAbsolutePath();
		System.out.println(rutaDelArchivo);
		FileWriter fw = new FileWriter(rutaCSV);
		try {
			for(cliente cacheCliente : clientes) {
				fw.append(Long.toString(cacheCliente.getCedula())).append(delim);
				fw.append(cacheCliente.getNombre()).append(delim);
				fw.append(cacheCliente.getDireccion()).append(delim);
				fw.append(cacheCliente.getCorreo()).append(delim);
				fw.append(cacheCliente.getTelefono()).append(NEXT_LINE);
			}
			fw.flush();
			fw.close();
			System.out.println("ArchivoCSV:Clientes creado");
		}catch(IOException e) {
		System.out.println("Error al crear el archivo CSV:Clientes");
		e.printStackTrace();
		}
	}
	
	
	//Modelo usado para agregar clientes a la base de datos
	public void Agregar(cliente cliente) throws IOException {
		clientes.add(cliente);
		this.ActualizarLista();
	}
	
	//Modelo usado para actualizar la lista de clientes.
	public void ActualizarLista() throws IOException {
		this.CrearArchivoCSV();
	}
	
	//Modelo usado para obtener el indice del cliente, usado en algunas funciones como borrar, o modificar.
	public int ObtenerIndice(long cedula) {
		int i = 0;
		int id = -1;
		for(i=0;i<clientes.size();i++) {
			if(clientes.get(i).getCedula()==cedula) {
				id = i;
			}
		}
		System.out.println("Indice encontrado: "+id);
		return id;
	}
	
	//Modelo usado para buscar clientes en la lista.
	public cliente Buscar(long cedula) {
		cliente clienteEncontrado = null;
		for(cliente cliente : clientes) {
			if(cliente.getCedula()==cedula) {
				clienteEncontrado = cliente;
			}
		}
		return clienteEncontrado;
	}
	
	//Chequea si el cliente con ese nÃºmero de cedula ya existe.
	public boolean Existe(long cedula) {
		boolean Existe = false;
		for(cliente cliente : clientes) {
			if(cliente.getCedula()==cedula) {
				Existe = true;
			}
		}
		System.out.println("cliente Existe: "+Existe);
		return Existe;
	
	}
	
	
	//Modelo usado para modificar el cliente en la lista.
	public void Modificar(int id, cliente nuevosDatoscliente) throws IOException {
		clientes.set(id, nuevosDatoscliente);
		this.ActualizarLista();
		System.out.println("Se modificó el cliente");
		
	}
	
	//Modelo usado para eliminar el cliente de la lista y el CSV.
	public void Eliminar(int id) throws IOException {
		clientes.remove(id);
		this.ActualizarLista();
		System.out.println("Se eliminó el cliente");
	}
	
	//Modelo para obtener los clientes en el CSV.
	public void ObtenerDeCSV() {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bReader = null;
		ArrayList<cliente> listResult = new ArrayList<cliente>();
		try {
			fis = new FileInputStream(rutaCSV);
			isr = new InputStreamReader(fis);
			bReader = new BufferedReader(isr);
			
		
		String line = null;
		String[]clientesStrings = null;
		
		while(true) {
			line = bReader.readLine();
			if(line == null) {
				break;
			}else {
				clientesStrings = line.split(",");
				cliente tempcliente = new cliente(0, "", "", "", "");
				tempcliente.setCedula(Long.parseLong(clientesStrings[0]));
				try {
				if (clientesStrings[1].isEmpty()) {}
				else tempcliente.setNombre(clientesStrings[1]);
				}
				catch (Exception e) {
					System.out.println("cliente con cedula "+clientesStrings[0]+" no pudo agregarse correctamente, Falta nombre.");
				}
				try {
				if (clientesStrings[2].isEmpty()) {}
				else tempcliente.setDireccion(clientesStrings[2]);
				}
				catch (Exception e) {
					System.out.println("cliente con cedula "+clientesStrings[0]+" no pudo agregarse correctamente, Falta dirección.");
				}
				try {
				if (clientesStrings[3].isEmpty()) {}
				else tempcliente.setCorreo(clientesStrings[3]);
				}
				catch (Exception e) {
					System.out.println("cliente con cedula "+clientesStrings[0]+" no pudo agregarse correctamente, Falta correo.");
				}
				try {
				if (clientesStrings[4].isEmpty()) {}
				else tempcliente.setTelefono(clientesStrings[4]);
				}
				catch (Exception e) {
					System.out.println("cliente con cedula "+clientesStrings[0]+" no pudo agregarse correctamente, Falta teléfono.");
				}
				
				listResult.add(tempcliente);
				
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
		this.clientes = listResult;
	}
}
