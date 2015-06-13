package ua.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.*;

import utilites.Parser;
import utilites.Util;

@SuppressWarnings("serial")
public class Image extends HttpServlet {
	
	private Parser parser = new Parser();
	private Util util = new Util();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(util.headWithTitle("Photo view"));
		
		out.println(util.StaticPart(false));
		if(req.getParameter("img")!=null)
			out.println(util.getSinglePhoto(req.getParameter("img")));
		else {
			out.println(util.getSinglePhoto(req.getParameter("photo"), parser.convertDate(req.getParameter("date")), req.getParameter("user"), req.getParameter("userImg")));
		}
		out.println("</center>");
		out.println("</span>");
		out.println("</body>");
		out.println("</html>");
	}
}
