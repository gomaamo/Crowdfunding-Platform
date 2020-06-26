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

@WebServlet("/ViewProjectServlet")
public class ViewProjectServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String pid;
		if (req.getParameter("action").equals("viewProject")) {

			pid =req.getParameter("pid");
		
		
		int id = Integer.parseInt(pid);
		ArrayList<Project> result = new ArrayList<Project>();
		result = DBFacade.getInstance().view(id);
		String titel = DBFacade.getInstance().vorgangerTitel(result.get(0).getVorganger());
		
		try {
			
		   req.setAttribute("user", req.getParameter("user"));
		   req.setAttribute("project",result.get(0));
		   req.setAttribute("titel",titel);
		   req.setAttribute("spende", result.get(0).getSpende());
		   req.setAttribute("kommentar", result.get(0).getKommentar());
		   req.getRequestDispatcher("view_project.ftl").forward(req, resp);

		} catch (ServletException | IOException e) {
			e.printStackTrace();
		
		
		}
	
	}
	}
}