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
import de.unidue.inf.is.adapters.Konto;
import de.unidue.inf.is.adapters.Project;
import de.unidue.inf.is.adapters.Spende;

@WebServlet("/DonateServlet")

public class DonateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String user =req.getParameter("user");
		String pid =req.getParameter("pid");
		int id= Integer.parseInt(pid);
		ArrayList<Project> result = new ArrayList<Project>();
		result=DBFacade.getInstance().view(id);
		
		boolean f = true;
		
		for(Spende s: result.get(0).getSpende())
		{
			if (s.getBenutzer().getEmail().equals(user))
			{
				f=false ;
				break;
			}		
		}
		
		if (req.getParameter("action").equals("donate")   )
		{
			
			if(result.get(0).getStatus().equals("offen")&& f == true)
			{
		try {
			req.setAttribute("kennung", result.get(0).getKennung());
			req.setAttribute("user", user);
			req.setAttribute("titel", result.get(0).getTitel());
			req.getRequestDispatcher("new_donate.ftl").forward(req , resp);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
			}
			else
			{
				req.setAttribute("user", user);
				req.setAttribute("pid", id);
				if(result.get(0).getStatus().equals("geschlossen")) {
					req.setAttribute("message",
							"Sorry, Der Projektstatus ist leider nicht mehr \"Offen\".");
					req.getRequestDispatcher("error.ftl").forward(req , resp);
				}else {
					req.setAttribute("message",
							"Sie haben schon einmal für dieses Projekt gespendet.");
					req.getRequestDispatcher("error.ftl").forward(req , resp);
				}
			}
		}
		
		
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		
		if (req.getParameter("action").equals("donate")) {
			try {
			String user =(String)req.getParameter("user");
			Konto konto = DBFacade.getInstance().getKonto(user);
			String pid =req.getParameter("pid");
			String fund =req.getParameter("fund");
			String checkBox =(String)req.getParameter("checkBox");
			
			
			int pd= Integer.parseInt(pid);
			double donation = Double.parseDouble(fund);
			
			double guthaben =konto.getGuthaben();
			double d =konto.getGuthaben()-donation;
			
			if(guthaben>donation)
			{
				if("privat".equals(checkBox)) 
					DBFacade.getInstance().donate(user, pd, donation, checkBox);
				else
					DBFacade.getInstance().donate(user, pd, donation, "oeffentlich");
				DBFacade.getInstance().updatekonto(user, d);
			
			
			resp.sendRedirect("http://localhost:8080/projectFunder/ViewProjectServlet?action=viewProject&user="+user+"&pid="+pd);
			}
			
			
			else {
				req.setAttribute("user", user);
				req.setAttribute("pid", pd);
				req.setAttribute("message",
						"Sorry, Ihr Guthaben ist nicht ausreichend. Bitte versuchen Sie es erneut.");	
				try {
					req.getRequestDispatcher("/error.ftl").forward(req, resp);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				};
			}
			
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
