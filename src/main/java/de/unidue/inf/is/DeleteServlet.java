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
import de.unidue.inf.is.adapters.Spende;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String user =req.getParameter("user");
		String profile =req.getParameter("profile");
		String pid =req.getParameter("pid");
		int id= Integer.parseInt(pid);
		//Benutzer benutzer =DBFacade.getInstance().getBenutzer(user);
		ArrayList<Project> project =new ArrayList<Project>();
		project= DBFacade.getInstance().view(id);
		ArrayList<Spende> result = new ArrayList<Spende>();
		result =project.get(0).getSpende();
		
		
		
		
		if (req.getParameter("action").equals("delete") ) 
		{if(user.equals(profile))
		{
			for(int i=0;i< result.size();i++)
			{
			result.get(i).getBenutzer().getKonto().setGuthaben(result.get(i).getBenutzer().getKonto().getGuthaben()+result.get(i).getSpendenbetrag());
			}
			DBFacade.getInstance().returnfund(result);
			DBFacade.getInstance().deleteKommentar(project.get(0).getKommentar());
			DBFacade.getInstance().deleteSpende(id);
			DBFacade.getInstance().deleteProject(id);
			
			resp.sendRedirect("http://localhost:8080/projectFunder/MainServlet?action=userSelected&user="+user);
		}
		
		
		else {
			req.setAttribute("user", user);
			req.setAttribute("pid", id);
			req.setAttribute("message",
					"Sorry, Sie müssen der Projektersteller sein, um es löschen zu können.");
			try {
				req.getRequestDispatcher("/error.ftl").forward(req, resp);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			};

		}
		}
		
	}

	
	

}
