package de.unidue.inf.is;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.adapters.DBFacade;
import de.unidue.inf.is.adapters.Project;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public final class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static ArrayList<Project> project = new ArrayList<Project>();
    /**
     * @see HttpServlet#HttpServlet()
     */
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		try {
			request.setAttribute("user", "dummy@dummy.com");
			request.setAttribute("availableProjects", project);
			request.getRequestDispatcher("search_projects.ftl").forward(request , response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String search= request.getParameter("title");
		project.clear();
		synchronized (project) {
			project.addAll(DBFacade.getInstance().search(search.toLowerCase()));
		}
		
        doGet(request, response);

	
	}

}
