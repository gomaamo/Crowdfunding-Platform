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

@WebServlet("/EditProjectServlet")
public class EditProjectServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String user =req.getParameter("user");
		String profile =req.getParameter("profile");
		String pid =req.getParameter("pid");
		int id= Integer.parseInt(pid);
		ArrayList<Project> result = new ArrayList<Project>();
		result=DBFacade.getInstance().view(id);
		String titel = DBFacade.getInstance().vorgangerTitel(result.get(0).getVorganger());

		
		
		if (req.getParameter("action").equals("editProject") ) 	
			{	
			try {
				
		if(user.equals(profile) && result.get(0).getStatus().equals("offen"))
		{
			req.setAttribute("titel",titel);
			req.setAttribute("user", user);
			req.setAttribute("result", result);
			req.setAttribute("pid", id);
			req.setAttribute("UserProjects", DBFacade.getInstance().getUserProjects(user));
			req.getRequestDispatcher("edit_project.ftl").forward(req, resp);
			
			
		}
		else if(!user.equals(profile))
		{
			req.setAttribute("message",
					"Sorry, Sie müssen der Projektersteller sein, um es editieren zu können.");
			req.setAttribute("user", user);
			req.setAttribute("pid", id);
			try {
				req.getRequestDispatcher("/error.ftl").forward(req, resp);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			};
		}
		else if(!result.get(0).getStatus().equals("offen"))
		{
			req.setAttribute("message",
					"Sorry, Der Projektstatus ist leider nicht mehr \"Offen\".");
			req.setAttribute("user", user);
			req.setAttribute("pid", id);
			try {
				req.getRequestDispatcher("/error.ftl").forward(req, resp);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			};
		}
			} catch (ServletException | IOException e) {
				e.printStackTrace();
		
			}
		}
			}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		if (req.getParameter("action").equals("editProject")) {
			try {
			String title = (String) req.getParameter("title");
			String fundingLimit = (String) req.getParameter("fundingLimit");
			String category = (String) req.getParameter("category");
			String vorg = (String) req.getParameter("vorg");
			String desc = (String) req.getParameter("desc");
			String user = (String) req.getParameter("user");
			String pid =(String)req.getParameter("pid");
			int pd= Integer.parseInt(pid);
			
			
			double flSQL = Double.parseDouble(fundingLimit);
			int catSQL = Integer.parseInt(category);
			int vorgangerSQL = Integer.parseInt(vorg);
			
			DBFacade.getInstance().editP(title, flSQL, catSQL, vorgangerSQL, desc, user,pd);
			
			
			resp.sendRedirect("http://localhost:8080/projectFunder/ViewProjectServlet?action=viewProject&user="+user+"&pid="+pid);
				
			}
			catch(IOException e){
				e.printStackTrace();
		}		
	}
		
		
	else {
		doGet(req, resp);
	}
		
		
	}
	
	
	
}