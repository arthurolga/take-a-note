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
@WebServlet("/logian")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service (HttpServletRequest request,
			 HttpServletResponse response) throws IOException, ServletException {
		
			PrintWriter out = response.getWriter();
			Usuarios usuario = new Usuarios();
			String name = request.getParameter("nome");
			usuario.setUser(name);
			usuario.setPassword(request.getParameter("senha"));
			HttpSession session = request.getSession();
			session.setAttribute( "username", name );
			
			
			DAO dao = new DAO();
			try {
				System.out.println("Tentou fazer login");
				if(dao.checkUser(usuario)) {
					if(dao.login(usuario)) {
						RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/index.jsp");
						try {
							RequetsDispatcherObj.forward(request, response);
						} catch (ServletException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
						RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/logincerto.jsp");
						RequetsDispatcherObj.forward(request, response);
					}
				}
				else {
					System.out.println("user nao existente");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("NAO Tentou fazer login");
			}
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
