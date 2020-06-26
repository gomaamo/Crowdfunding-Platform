package de.unidue.inf.is;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.unidue.inf.is.adapters.DBFacade;
import de.unidue.inf.is.adapters.Project;


@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = (req.getParameter("action") == null) ? "" : req.getParameter("action");
		
		ArrayList<Project> resultOffen = new ArrayList<Project>();
		resultOffen = DBFacade.getInstance().viewOffen();
		
		ArrayList<Project> resultGeschlossen = new ArrayList<Project>();
		resultGeschlossen = DBFacade.getInstance().viewGeschlossen();

		// Dispatch message to template engine
		
		
		if (action.equals("userSelected")) {
			// Set request attributes
			req.setAttribute("user", req.getParameter("user"));

			// Dispatch request to template engine
			try {
				req.setAttribute("openProjects",resultOffen);
			    req.setAttribute("closedProjects",resultGeschlossen);
			    req.getRequestDispatcher("view_main.ftl").forward(req , resp);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

			// Append parameter of request
					

	
	}
	
}