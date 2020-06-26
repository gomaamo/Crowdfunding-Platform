package de.unidue.inf.is;

import java.io.IOException;

import de.unidue.inf.is.adapters.DBFacade;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NewProjectServlet")
public class NewProjectServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

	
	public NewProjectServlet() {
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = (req.getParameter("action") == null) ? "" : req.getParameter("action");

		
		if (action.equals("createProject")) {
			// Set request attributes
			String user =  req.getParameter("user");
			

			// Dispatch request to template engine
			try {
				req.setAttribute("user",user);
				req.setAttribute("UserProjects", DBFacade.getInstance().getUserProjects(user));
				req.getRequestDispatcher("new_project.ftl").forward(req , resp);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		if (req.getParameter("action").equals("newProject")) {
			String title = (String) req.getParameter("title");
			String fundingLimit = (String) req.getParameter("fundingLimit");
			String category = (String) req.getParameter("category");
			String vorg = (String) req.getParameter("vorg");
			String desc = (String) req.getParameter("desc");
			String user = (String) req.getParameter("user");
			
			double flSQL = Double.parseDouble(fundingLimit);
			int catSQL = Integer.parseInt(category);
			int vorgangerSQL = Integer.parseInt(vorg);
			
			int id = DBFacade.getInstance().createP(title, flSQL, catSQL, vorgangerSQL, desc, user);
			
			
			try {
				resp.sendRedirect("http://localhost:8080/projectFunder/ViewProjectServlet?action=viewProject&user="+user+"&pid="+id);
				/*resp.sendRedirect("http://localhost:8080/example/test?name="+req.getParameter("name"));
				req.getSession().setAttribute("pid", id);
				req.getRequestDispatcher("view_project.ftl").forward(req, resp);*/
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