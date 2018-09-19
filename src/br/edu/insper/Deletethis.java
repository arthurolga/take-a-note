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
@WebServlet("/DeleteThis")
public class Deletethis extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service (HttpServletRequest request,
			 HttpServletResponse response) throws IOException, ServletException {

			PrintWriter out = response.getWriter();
			Usuarios usuario = new Usuarios();
			String name = request.getParameter("nome");
			String senha = request.getParameter("senha");
			usuario.setUser(name);
			usuario.setPassword(senha);
			DAO dao = new DAO();
			try {
				if (dao.login(usuario)){

					dao.remove(usuario);
					System.out.println("deletou");
					RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/logincerto.jsp");
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
}
	
