package ua.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.*;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;
import utilites.Parser;
import utilites.Util;

@SuppressWarnings("serial")
public class Image extends HttpServlet {
	
	private Parser parser = new Parser();
	private Util util = new Util();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
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
		
		out.println(util.StaticPart(username, loged, false));
		if(req.getParameter("img")!=null)
			out.println(util.getSinglePhoto(loged, req.getParameter("img")));
		else {
			out.println(util.getSinglePhoto(loged, req.getParameter("photo"), req.getParameter("date"), req.getParameter("user"), req.getParameter("userImg")));
		}
		out.println("</center>");
		out.println("</span>");
		out.println("</body>");
		out.println("</html>");
	}
}
