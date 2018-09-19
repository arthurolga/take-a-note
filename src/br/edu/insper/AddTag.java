package br.edu.insper;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddTag
 */
@WebServlet("/AddTag")
public class AddTag extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service (HttpServletRequest request,
			 HttpServletResponse response) throws IOException {
		
		HttpSession session = request.getSession();
		DAO dao = new DAO();
		Tags tag = new Tags();
		tag.setName(request.getParameter("name"));
		tag.setUser((String)session.getAttribute("username"));
		
		try {
			System.out.println("Tag adicionada");
			dao.adicionaTag(tag);
			RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/index.jsp");
			try {
				RequetsDispatcherObj.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Tag n adicionada");
			e.printStackTrace();
		}
		dao.close();
	}

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTag() {
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
