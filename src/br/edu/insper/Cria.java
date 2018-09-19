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
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Cria")
public class Cria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service (HttpServletRequest request,
			 HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		DAO dao = new DAO();
		Usuarios usuario = new Usuarios();
		usuario.setUser(request.getParameter("nome"));
		usuario.setPassword(request.getParameter("senha"));
		String confirm = request.getParameter("confirm");
		try {
			if(!dao.checkUser(usuario) && request.getParameter("senha").equals(confirm)  ) {
				System.out.println("usuario adicionado");
				dao.adiciona(usuario);
				HttpSession session = request.getSession();
				session.setAttribute( "username", usuario.getUser() );
				
				RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/index.jsp");
				try {
					RequetsDispatcherObj.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else {
				System.out.println("esse user ja existe");
				String error;
				if (dao.checkUser(usuario)) {
					error = "Usuario ja existe";
				} else {
					error = "Senhas nao coincidem";
				}
				RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/register.jsp?error="+error);
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
