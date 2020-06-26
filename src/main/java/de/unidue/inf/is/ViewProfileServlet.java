package de.unidue.inf.is;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.adapters.Benutzer;
import de.unidue.inf.is.adapters.DBFacade;
import de.unidue.inf.is.adapters.Project;

/**
 * Servlet implementation class ViewProfileServlet
 */
@WebServlet("/ViewProfileServlet")
public class ViewProfileServlet extends HttpServlet {
       
	@Override

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String profile =request.getParameter("profile");
		Benutzer benutzer = DBFacade.getInstance().getBenutzer(profile);
		ArrayList<Project> result1 = new ArrayList<Project>();
		result1=DBFacade.getInstance().getUserProjects(benutzer.getEmail());
		ArrayList<Project> result2 = new ArrayList<Project>();
		result2 =DBFacade.getInstance().getUserSpende(profile);
		
		if (request.getParameter("action").equals("viewProfile")) {		
		
		try {
			
			  
			   request.setAttribute("user",request.getParameter("user"));
			   request.setAttribute("profile",profile);
			   request.setAttribute("name", benutzer.getName());
			   request.setAttribute("AnzahlE", result1.size());
			   request.setAttribute("AnzahlU", result2.size());
			   request.setAttribute("createdProjects",result1);
			   request.setAttribute("supportedProjects", result2);
			   request.getRequestDispatcher("view_profile.ftl").forward(request, response);

			} catch (ServletException | IOException e) {
				e.printStackTrace();
		
			}

		
		
		}
	}


	
	
}