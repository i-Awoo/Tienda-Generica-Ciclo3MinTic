package co.edu.unbosque.tiendagenerica.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class modelProveedores {
	
	public List<proveedor> proveedores;
	private final String rutaCSV = "C:/tmp/proveedores.csv";
	
	//Se crea la lista en la que se guardaran los proveedores
	public void CrearLista() throws IOException {
		proveedores = new ArrayList<>();
		System.out.println("Lista creada");

		if (ExisteArchivoCSV()) {System.out.println("CSV:proveedores ya existe, omitiendo creación.");
		}
		else {
			System.out.println("Creando CSV:proveedores");
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
			for(proveedor cacheproveedor : proveedores) {
				fw.append(Long.toString(cacheproveedor.getNit())).append(delim);
				fw.append(cacheproveedor.getNombre()).append(delim);
				fw.append(cacheproveedor.getDireccion()).append(delim);
				fw.append(cacheproveedor.getTelefono()).append(delim);
				fw.append(cacheproveedor.getCiudad()).append(NEXT_LINE);
			}
			fw.flush();
			fw.close();
			System.out.println("ArchivoCSV:proveedores creado");
		}catch(IOException e) {
		System.out.println("Error al crear el archivo CSV:proveedores");
		e.printStackTrace();
		}
	}
	
	
	//Modelo usado para agregar proveedores a la base de datos
	public void Agregar(proveedor proveedor) throws IOException {
		proveedores.add(proveedor);
		this.ActualizarLista();
	}
	
	//Modelo usado para actualizar la lista de proveedores.
	public void ActualizarLista() throws IOException {
		this.CrearArchivoCSV();
	}
	
	//Modelo usado para obtener el indice del proveedor, usado en algunas funciones como borrar, o modificar.
	public int ObtenerIndice(long nit) {
		int i = 0;
		int id = -1;
		for(i=0;i<proveedores.size();i++) {
			if(proveedores.get(i).getNit()==nit) {
				id = i;
			}
		}
		System.out.println("Indice encontrado: "+id);
		return id;
	}
	
	//Modelo usado para buscar proveedores en la lista.
	public proveedor Buscar(long nit) {
		proveedor proveedorEncontrado = null;
		for(proveedor proveedor : proveedores) {
			if(proveedor.getNit()==nit) {
				proveedorEncontrado = proveedor;
			}
		}
		return proveedorEncontrado;
	}
	
	//Chequea si el proveedor con ese nÃºmero de nit ya existe.
	public boolean Existe(long nit) {
		boolean Existe = false;
		for(proveedor proveedor : proveedores) {
			if(proveedor.getNit()==nit) {
				Existe = true;
			}
		}
		System.out.println("proveedor Existe: "+Existe);
		return Existe;
	
	}
	
	
	//Modelo usado para modificar el proveedor en la lista.
	public void Modificar(int id, proveedor nuevosDatosproveedor) throws IOException {
		proveedores.set(id, nuevosDatosproveedor);
		this.ActualizarLista();
		System.out.println("Se modificó el proveedor");
		
	}
	
	//Modelo usado para eliminar el proveedor de la lista y el CSV.
	public void Eliminar(int id) throws IOException {
		proveedores.remove(id);
		this.ActualizarLista();
		System.out.println("Se eliminó el proveedor");
	}
	
	//Modelo para obtener los proveedores en el CSV.
	public void ObtenerDeCSV() {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader bReader = null;
		ArrayList<proveedor> listResult = new ArrayList<proveedor>();
		try {
			fis = new FileInputStream(rutaCSV);
			isr = new InputStreamReader(fis);
			bReader = new BufferedReader(isr);
			
		
		String line = null;
		String[]proveedoresStrings = null;
		
		while(true) {
			line = bReader.readLine();
			if(line == null) {
				break;
			}else {
				proveedoresStrings = line.split(",");
				proveedor tempproveedor = new proveedor(0, "", "", "", "");
				tempproveedor.setNit(Long.parseLong(proveedoresStrings[0]));
				try {
				if (proveedoresStrings[1].isEmpty()) {}
				else tempproveedor.setNombre(proveedoresStrings[1]);
				}
				catch (Exception e) {
					System.out.println("proveedor con nit "+proveedoresStrings[0]+" no pudo agregarse correctamente, Falta nombre.");
				}
				try {
				if (proveedoresStrings[2].isEmpty()) {}
				else tempproveedor.setDireccion(proveedoresStrings[2]);
				}
				catch (Exception e) {
					System.out.println("proveedor con nit "+proveedoresStrings[0]+" no pudo agregarse correctamente, Falta dirección.");
				}
				try {
				if (proveedoresStrings[3].isEmpty()) {}
				else tempproveedor.setTelefono(proveedoresStrings[3]);
				}
				catch (Exception e) {
					System.out.println("proveedor con nit "+proveedoresStrings[0]+" no pudo agregarse correctamente, Falta Teléfono.");
				}
				try {
				if (proveedoresStrings[4].isEmpty()) {}
				else tempproveedor.setCiudad(proveedoresStrings[4]);
				}
				catch (Exception e) {
					System.out.println("proveedor con nit "+proveedoresStrings[0]+" no pudo agregarse correctamente, Falta ciudad.");
				}
				
				listResult.add(tempproveedor);
				
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
		this.proveedores = listResult;
	}
}
