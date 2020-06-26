package de.unidue.inf.is;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.adapters.DBFacade;
import de.unidue.inf.is.adapters.Konto;
import de.unidue.inf.is.adapters.Project;

/**
 * Servlet implementation class NewKommentar
 */
@WebServlet("/NewKommentar")
public class NewKommentar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewKommentar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String user =request.getParameter("user");
		String pid =request.getParameter("pid");
		int id= Integer.parseInt(pid);
		ArrayList<Project> result = new ArrayList<Project>();
		result=DBFacade.getInstance().view(id);
		
		
		if (request.getParameter("action").equals("newComment")   )
		{
			
			
		try {
			request.setAttribute("kennung", result.get(0).getKennung());
			request.setAttribute("user", user);
			request.setAttribute("titel", result.get(0).getTitel());
			request.getRequestDispatcher("new_comment.ftl").forward(request , response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if (request.getParameter("action").equals("newComment")) {
			try {
			String user =(String)request.getParameter("user");
			String pid =request.getParameter("pid");
			String comment =(String)request.getParameter("comment");
			String checkBox =(String)request.getParameter("checkBox");
			
			if("privat".equals(checkBox)) {
				
			}
			
			int pd= Integer.parseInt(pid);

			if("privat".equals(checkBox)) 
					DBFacade.getInstance().comment(user, pd, comment, checkBox);
				else
					DBFacade.getInstance().comment(user, pd, comment, "oeffentlich");
			
			
			response.sendRedirect("http://localhost:8080/projectFunder/ViewProjectServlet?action=viewProject&user="+user+"&pid="+pd);
			
			
		}
			
			catch(IOException e){
				e.printStackTrace();
		}
		}
		
	else {
		doGet(request, response);
	}
	
	}

}
