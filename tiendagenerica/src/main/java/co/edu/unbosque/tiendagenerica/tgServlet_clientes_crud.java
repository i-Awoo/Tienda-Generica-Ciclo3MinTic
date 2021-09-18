package co.edu.unbosque.tiendagenerica;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.unbosque.tiendagenerica.model.cliente;
import co.edu.unbosque.tiendagenerica.model.modelClientes;


/**
 * Servlet implementation class tgServlet_clientes_crud
 */
@WebServlet("/tgServlet_clientes_crud")
public class tgServlet_clientes_crud extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private cliente cacheCliente; 
	modelClientes mC = new modelClientes();
	
	
       
    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
    public tgServlet_clientes_crud() throws IOException {
        super();
        // TODO Auto-generated constructor stub
        mC.CrearLista();
        mC.ObtenerDeCSV();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Información del cliente: ").append(request.getContextPath());
		
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
		
		
		//Switch usado para el manejo de los botones del JSP de clientes.
		switch (botonPulsado) {
		//Consultar los datos del cliente.
		case 0:
			if (request.getParameter("cedula").isBlank()){
				System.out.println("Error");
				mensaje = "El campo de cédula está vacio.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("clientes.jsp").forward(request,response);
				break;				
			}else {
			cacheCliente = null;
			cacheCliente = mC.Buscar(Integer.parseInt(request.getParameter("cedula")));
			response.setContentType("text/html");
			PrintWriter out = null;
			try {
				out=response.getWriter();
				out.println("<center>");
				
				out.println("cliente encontrado:<br>");
				out.println("Cédula: "+cacheCliente.getCedula());
				out.println("<br>Nombre: "+cacheCliente.getNombre());
				out.println("<br>Dirección: "+cacheCliente.getDireccion());
				out.println("<br>Correo: "+cacheCliente.getCorreo());
				out.println("<br>Telefono: "+cacheCliente.getTelefono());
				
				break;
			}
			catch(Exception e) {
				mensaje = "El cliente no existe.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("clientes.jsp").forward(request,response);
				break;
			}
			}
			//Agregar el cliente usando el formulario.
		case 1:
			try {
				
				if(mC.Existe(Long.parseLong(request.getParameter("cedula")))) {
					System.out.println("Error cliente ya existe");
					mensaje = "El cliente ya existe.";
				    request.setAttribute("color", color);
				    request.setAttribute("mensaje", mensaje);
				    request.getRequestDispatcher("clientes.jsp").forward(request,response);
					}
				else {
							mC.Agregar(new cliente(Integer.parseInt(request.getParameter("cedula")),
							request.getParameter("nombre"), request.getParameter("direccion"), 
							request.getParameter("email"), request.getParameter("telefono")));
							color = "blue";
							mensaje = "Cliente agregado.";
						    request.setAttribute("color", color);
						    request.setAttribute("mensaje", mensaje);
						    request.getRequestDispatcher("clientes.jsp").forward(request,response);
				break;
				}
			}
			catch(Exception e){
				System.out.println("Error");
				mensaje = "El campo de cédula está vacio.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("clientes.jsp").forward(request,response);
			}
			//Modificar el cliente usando el formulario, se usa el número de cedula para modificarlo.
		case 2:
			if (request.getParameter("cedula").isBlank()) {
				System.out.println("Error.");
				mensaje = "El campo de cédula está vacio.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("clientes.jsp").forward(request,response);
				break;
				
			}else {
			cliente clienteModificado = null;
			int indicecliente = mC.ObtenerIndice(Long.parseLong(request.getParameter("cedula")));
			clienteModificado = mC.Buscar(Long.parseLong(request.getParameter("cedula")));
			if (indicecliente >= 0) {
				clienteModificado.setNombre(request.getParameter("nombre"));
				clienteModificado.setDireccion(request.getParameter("direccion"));
				clienteModificado.setCorreo(request.getParameter("email"));
				clienteModificado.setTelefono(request.getParameter("telefono"));
				
				//Envia la clase modificada de vuelta al CSV.
				mC.Modificar(indicecliente, clienteModificado);
				
				mensaje = "Cliente actualizado.";
				color = "Blue";
			    request.setAttribute("mensaje", mensaje);
			    request.setAttribute("color", color);
			    request.getRequestDispatcher("clientes.jsp").forward(request,response);
				break;
			}else {
				System.out.println("Error");
				mensaje = "Cliente no encontrado.";
			    request.setAttribute("mensaje", mensaje);
			    request.setAttribute("color", color);
			    request.getRequestDispatcher("clientes.jsp").forward(request,response);
				break;
			}
			}
			//Eliminar el cliente, usando su numero de cedula.
		case 3:
			try {
				mC.Eliminar(mC.ObtenerIndice(Long.parseLong(request.getParameter("cedula"))));
				mensaje = "Cliente eliminado.";
				color = "Blue";
			    request.setAttribute("mensaje", mensaje);
			    request.setAttribute("color", color);
			    request.getRequestDispatcher("clientes.jsp").forward(request,response);
				break;
				
			}
			catch(Exception e) {
				mensaje = "El campo de cédula está vacio.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("clientes.jsp").forward(request,response);
			}
			}
		}
		
}

