package co.edu.unbosque.tiendagenerica;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.unbosque.tiendagenerica.model.modelProveedores;
import co.edu.unbosque.tiendagenerica.model.proveedor;


/**
 * Servlet implementation class tgServlet_proveedores_crud
 */
@WebServlet("/tgServlet_proveedores_crud")
public class tgServlet_proveedores_crud extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private proveedor cacheproveedor; 
	modelProveedores mR = new modelProveedores();
	
	
       
    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
    public tgServlet_proveedores_crud() throws IOException {
        super();
        // TODO Auto-generated constructor stub
        mR.CrearLista();
        mR.ObtenerDeCSV();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Información del proveedor: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println( "pulsaste el botón " + request.getParameter("button"));
		int botonPulsado = Integer.parseInt(request.getParameter("button"));
		String color = "red";
		String mensaje = "Error";
		
		
		//Switch usado para el manejo de los botones del JSP de proveedores.
		switch (botonPulsado) {
		//Consultar los datos del proveedor.
		case 0:
			if (request.getParameter("nit").isBlank()){
				System.out.println("Error");
				mensaje = "El campo de nit está¡ vacio.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("proveedores.jsp").forward(request,response);
				break;				
			}else {
			cacheproveedor = null;
			cacheproveedor = mR.Buscar(Integer.parseInt(request.getParameter("nit")));
			response.setContentType("text/html");
			PrintWriter out = null;
			try {
				out=response.getWriter();
				out.println("<center>");
				
				out.println("Proveedor encontrado:<br>");
				out.println("nit: "+cacheproveedor.getNit());
				out.println("<br>Nombre: "+cacheproveedor.getNombre());
				out.println("<br>Dirección: "+cacheproveedor.getDireccion());
				out.println("<br>Teléfono: "+cacheproveedor.getTelefono());
				out.println("<br>Ciudad: "+cacheproveedor.getCiudad());
				
				break;
			}
			catch(Exception e) {
				mensaje = "El proveedor no existe.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("proveedores.jsp").forward(request,response);
				break;
			}
			}
			//Agregar el proveedor usando el formulario.
		case 1:
			try {
				
				if(mR.Existe(Long.parseLong(request.getParameter("nit")))) {
					System.out.println("Error proveedor ya existe");
					mensaje = "El proveedor ya existe.";
				    request.setAttribute("color", color);
				    request.setAttribute("mensaje", mensaje);
				    request.getRequestDispatcher("proveedores.jsp").forward(request,response);
					}
				else {
							mR.Agregar(new proveedor(Integer.parseInt(request.getParameter("nit")),
							request.getParameter("nombre"), request.getParameter("direccion"), 
							request.getParameter("telefono"), request.getParameter("ciudad")));
							color = "blue";
							mensaje = "Proveedor agregado.";
						    request.setAttribute("color", color);
						    request.setAttribute("mensaje", mensaje);
						    request.getRequestDispatcher("proveedores.jsp").forward(request,response);
				break;
				}
			}
			catch(Exception e){
				System.out.println("Error");
				mensaje = "El campo de nit está¡ vacio.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("proveedores.jsp").forward(request,response);
			}
			//Modificar el proveedor usando el formulario, se usa el náºmero de nit para modificarlo.
		case 2:
			if (request.getParameter("nit").isBlank()) {
				System.out.println("Error.");
				mensaje = "El campo de nit está¡ vacio.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("proveedores.jsp").forward(request,response);
				break;
				
			}else {
			proveedor proveedorModificado = null;
			int indiceproveedor = mR.ObtenerIndice(Long.parseLong(request.getParameter("nit")));
			proveedorModificado = mR.Buscar(Long.parseLong(request.getParameter("nit")));
			if (indiceproveedor >= 0) {
				proveedorModificado.setNombre(request.getParameter("nombre"));
				proveedorModificado.setDireccion(request.getParameter("direccion"));
				proveedorModificado.setTelefono(request.getParameter("telefono"));
				proveedorModificado.setCiudad(request.getParameter("ciudad"));
				
				//Envia la clase modificada de vuelta al CSV.
				mR.Modificar(indiceproveedor, proveedorModificado);
				
				mensaje = "Proveedor actualizado.";
				color = "Blue";
			    request.setAttribute("mensaje", mensaje);
			    request.setAttribute("color", color);
			    request.getRequestDispatcher("proveedores.jsp").forward(request,response);
				break;
			}else {
				System.out.println("Error");
				mensaje = "Proveedor no encontrado.";
			    request.setAttribute("mensaje", mensaje);
			    request.setAttribute("color", color);
			    request.getRequestDispatcher("proveedores.jsp").forward(request,response);
				break;
			}
			}
			//Eliminar el proveedor, usando su numero de nit.
		case 3:
			try {
				mR.Eliminar(mR.ObtenerIndice(Long.parseLong(request.getParameter("nit"))));
				mensaje = "Proveedor eliminado.";
				color = "Blue";
			    request.setAttribute("mensaje", mensaje);
			    request.setAttribute("color", color);
			    request.getRequestDispatcher("proveedores.jsp").forward(request,response);
				break;
				
			}
			catch(Exception e) {
				mensaje = "El campo de Nit está¡ vacio o no existe.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("proveedores.jsp").forward(request,response);
			}
			}
		}
		
}

