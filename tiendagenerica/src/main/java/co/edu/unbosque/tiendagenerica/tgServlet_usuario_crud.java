package co.edu.unbosque.tiendagenerica;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.unbosque.tiendagenerica.model.modelUsuarios;
import co.edu.unbosque.tiendagenerica.model.usuario;

/**
 * Servlet implementation class tgServlet_usuario_crud
 */
@WebServlet("/tgServlet_usuario_crud")
public class tgServlet_usuario_crud extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private usuario cacheUsuario; 
	modelUsuarios mU = new modelUsuarios();
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tgServlet_usuario_crud() {
        super();
        // TODO Auto-generated constructor stub
        mU.ObtenerUsuariosDeCSV();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Información del usuario: ").append(request.getContextPath());
		
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
		
		
		//Switch usado para el manejo de los botones del JSP de usuarios.
		switch (botonPulsado) {
		//Consultar los datos del usuario.
		case 0:
			if (request.getParameter("cedula").isBlank()){
				System.out.println("Error");
				mensaje = "El campo de cédula está vacio.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("usuarios.jsp").forward(request,response);
				break;				
			}else {
			cacheUsuario = null;
			cacheUsuario = mU.BuscarUsuario(Integer.parseInt(request.getParameter("cedula")));
			response.setContentType("text/html");
			PrintWriter out = null;
			try {
				out=response.getWriter();
				out.println("<center>");
				
				out.println("Usuario encontrado:<br>");
				out.println("Cédula: "+cacheUsuario.getCedula());
				out.println("<br>Nombre: "+cacheUsuario.getNombre());
				out.println("<br>Correo: "+cacheUsuario.getCorreo());
				out.println("<br>Usuario: "+cacheUsuario.getUsuario());
				out.println("<br>Contraseña: "+cacheUsuario.getPassword());
				
				break;
			}
			catch(Exception e) {
				mensaje = "El usuario no existe.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("usuarios.jsp").forward(request,response);
				break;
			}
			}
			//Agregar el usuario usando el formulario.
		case 1:
			try {
				
				if(mU.UsuarioExiste(Long.parseLong(request.getParameter("cedula")))) {
					System.out.println("Error usuario ya existe");
					mensaje = "El usuario ya existe.";
				    request.setAttribute("color", color);
				    request.setAttribute("mensaje", mensaje);
				    request.getRequestDispatcher("usuarios.jsp").forward(request,response);
					}
				else {
							mU.AgregarUsuario(new usuario(Integer.parseInt(request.getParameter("cedula")),
							request.getParameter("nombre"), request.getParameter("email"), 
							request.getParameter("usuario"), request.getParameter("password")));
							color = "blue";
							mensaje = "Usuario agregado.";
						    request.setAttribute("color", color);
						    request.setAttribute("mensaje", mensaje);
						    request.getRequestDispatcher("usuarios.jsp").forward(request,response);
				break;
				}
			}
			catch(Exception e){
				System.out.println("Error");
				mensaje = "El campo de cédula está vacio.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("usuarios.jsp").forward(request,response);
			}
			//Modificar el usuario usando el formulario, se usa el número de cedula para modificarlo.
		case 2:
			if (request.getParameter("cedula").equals("1") || request.getParameter("cedula").isBlank()) {
				System.out.println("Error: No se puede modificar al administrador.");
				if (request.getParameter("cedula").equals("1")) {
				mensaje = "No se puede modificar el administrador.";
				}else mensaje = "El campo de cédula está vacio.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("usuarios.jsp").forward(request,response);
				break;
				
			}else {
			usuario usuarioModificado = null;
			int indiceUsuario = mU.ObtenerIndiceUsuario(Integer.parseInt(request.getParameter("cedula")));
			usuarioModificado = mU.BuscarUsuario(Integer.parseInt(request.getParameter("cedula")));
			if (indiceUsuario >= 0) {
				usuarioModificado.setNombre(request.getParameter("nombre"));
				usuarioModificado.setCorreo(request.getParameter("email"));
				usuarioModificado.setUsuario(request.getParameter("usuario"));
				usuarioModificado.setPassword(request.getParameter("password"));
				mU.ModificarUsuario(indiceUsuario, usuarioModificado);
				mensaje = "Usuario actualizado.";
				color = "Blue";
			    request.setAttribute("mensaje", mensaje);
			    request.setAttribute("color", color);
			    request.getRequestDispatcher("usuarios.jsp").forward(request,response);
				break;
			}else {
				System.out.println("Error");
			    request.setAttribute("mensaje", mensaje);
			    request.setAttribute("color", color);
			    request.getRequestDispatcher("usuarios.jsp").forward(request,response);
				break;
			}
			}
			//Eliminar el usuario, usando su numero de cedula.
		case 3:
			if (request.getParameter("cedula").equals("1")) {
				System.out.println("Error: No se puede eliminar al administrador.");
				mensaje = "No se puede eliminar el administrador.";
			    request.setAttribute("mensaje", mensaje);
			    request.setAttribute("color", color);
			    request.getRequestDispatcher("usuarios.jsp").forward(request,response);
				break;
			}else {
			try {
				mU.EliminarUsuario(mU.ObtenerIndiceUsuario(Integer.parseInt(request.getParameter("cedula"))));
				mensaje = "Usuario eliminado.";
				color = "Blue";
			    request.setAttribute("mensaje", mensaje);
			    request.setAttribute("color", color);
			    request.getRequestDispatcher("usuarios.jsp").forward(request,response);
				break;
				
			}
			catch(Exception e) {
				mensaje = "El campo de cédula está vacio.";
			    request.setAttribute("color", color);
			    request.setAttribute("mensaje", mensaje);
			    request.getRequestDispatcher("usuarios.jsp").forward(request,response);
			}
			}
		}
		
		
	}

}
