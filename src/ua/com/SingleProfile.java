package ua.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utilites.Util;

public class SingleProfile extends HttpServlet{
	private Util util = new Util();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession(true);
		String username = null;
		if(session.getAttribute("username")!=null)
			username = (String)session.getAttribute("username");
		boolean loged = false;
		if(session.getAttribute("loged")!=null)
			loged = session.getAttribute("loged").equals("true");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.println(util.headWithTitle("Photo view"));
		out.println(util.StaticPart(username, loged, true));
		String temp = util.getProfileSingle(session.getAttribute("username"), req.getParameter("img"), req.getParameter("date"), req.getParameter("author"), req.getParameter("userImg"));
		out.println(temp);
		out.println("</center>");
		out.println("</span>");
		out.println("</body>");
		out.println("</html>");
	}
}
