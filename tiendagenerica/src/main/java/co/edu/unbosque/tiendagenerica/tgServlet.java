package co.edu.unbosque.tiendagenerica;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import co.edu.unbosque.tiendagenerica.model.modelUsuarios;

/**
 * Servlet implementation class tgServlet
 */
@WebServlet("/tgServlet")
public class tgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	modelUsuarios mU = new modelUsuarios();
    	
       
    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
    public tgServlet() throws IOException {
        super();
        // TODO Auto-generated constructor stub
        mU.CrearLista();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Se accede al modelUsuarios.java y se revisa en el CSV si existe el usuario.
		response.setContentType("text/html");
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String mensaje = "Usuario no existe, o credenciales incorrectas";
		try {
		if (mU.AprobarUsuario(usuario, password)==true){
			
			response.sendRedirect("tiendagenerica.jsp");
		}
		else {
			//response.sendRedirect("indexError.jsp");
		    request.setAttribute("mensaje", mensaje);
		    request.getRequestDispatcher("index.jsp").forward(request,response);
			
		}
		}catch(Exception e){
			
			
		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
