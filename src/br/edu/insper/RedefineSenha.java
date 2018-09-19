package br.edu.insper;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Login
 */
@WebServlet("/redefine")
public class RedefineSenha extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service (HttpServletRequest request,
			 HttpServletResponse response) throws IOException, ServletException {

			
			Usuarios usuario = new Usuarios();
			String name = request.getParameter("nome");
			String senha = request.getParameter("senha");
			String novasenha = request.getParameter("novasenha");
			usuario.setUser(name);
			usuario.setPassword(senha);
			DAO dao = new DAO();
			try {
				if (dao.login(usuario)){
					dao.altera(usuario, novasenha);
					RequestDispatcher RequetsDispatcherObj = request.getRequestDispatcher("/logincerto.jsp");
					try {
						RequetsDispatcherObj.forward(request, response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}


















    /**
     * @see HttpServlet#HttpServlet()
     */




    public RedefineSenha() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
