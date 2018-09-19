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
 * Servlet implementation class editNota
 */
@WebServlet("/editNota")
public class editNota extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service (HttpServletRequest request,
			 HttpServletResponse response) throws IOException, ServletException {
			

			Notas nota = new Notas();
			Tags tag = new Tags();
			int id =Integer.parseInt(request.getParameter("id"));
			nota.setId(id);
			nota.setTitulo(request.getParameter("titulo"));
			nota.setContent(request.getParameter("content"));
			if ((request.getParameter("tag")!=null)) {
				if (request.getParameter("tag").length() > 0) {
				nota.setTag(request.getParameter("tag"));
				tag.setName(request.getParameter("tag"));
				HttpSession session = request.getSession();
				tag.setUser((String)session.getAttribute( "username" ));
			}
			}
			
			DAO dao = new DAO();
			try {
				//if ((tag.getName() != null)&(!(dao.checkTag(tag))&&(tag.getName().length() > 0) )) {
				if ((tag.getName() != null)) {
					if(tag.getName().length() > 0) {
						
					
					if(!(dao.checkTag(tag))){
						System.out.println("Deu checkotag");
						dao.adicionaTag(tag);
					}
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("Conectou cm o DAO");
			try {
				System.out.println("Tentou rodar o edit");
				
				dao.alteraNota(nota);
				RequestDispatcher RequetsDispatcherObj =request.getRequestDispatcher("/index.jsp");
				try {
					RequetsDispatcherObj.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) {
				System.out.println("Nem tentou rodar o edit");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
			

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editNota() {
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
