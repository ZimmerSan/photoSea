package ua.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilites.Util;

public class Saved extends HttpServlet{
	private Util util = new Util();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession(true);
		boolean loged = false;
		if(session.getAttribute("loged")!=null)
			loged = session.getAttribute("loged").equals("true");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println(util.headWithTitle("Saved photos"));
			out.println(util.StaticPart(loged, true));
			out.println(util.getSavedPhotos(session.getAttribute("username")));
			out.println("</center>");
			out.println("</span>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
