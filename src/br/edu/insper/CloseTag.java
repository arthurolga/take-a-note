package br.edu.insper;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CloseTag
 */
@WebServlet("/CloseTag")
public class CloseTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service (HttpServletRequest request,
			 HttpServletResponse response) throws IOException, ServletException {
		
			int id =Integer.parseInt(request.getParameter("id"));
			Notas nota = new Notas();
			nota.setId(id);
			nota.setTag(null);
			
			DAO dao = new DAO();
			System.out.println("Conectou cm o DAO");
			try {
				System.out.println("Tentou rodar o remove");
				dao.alteraTagNota(nota);
				RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/index.jsp");
				try {
					RequetsDispatcherObj.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				System.out.println("Nem tentou rodar o remove");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CloseTag() {
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
