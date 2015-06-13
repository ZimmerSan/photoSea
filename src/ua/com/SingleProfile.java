package ua.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilites.Util;

public class SingleProfile extends HttpServlet{
	private Util util = new Util();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		out.println(util.headWithTitle("Photo view"));
		out.println(util.StaticPart(true));
		out.println(util.getProfileSingle(req.getParameter("img"), req.getParameter("date"), req.getParameter("author"), req.getParameter("userImg")));
		//out.println(util.getProfileSingle(req.getParameter("img"), null, null,null));
		out.println("</center>");
		out.println("</span>");
		out.println("</body>");
		out.println("</html>");
	}
}
