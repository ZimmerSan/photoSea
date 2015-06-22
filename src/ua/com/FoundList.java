package ua.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import javax.servlet.http.*;

import utilites.Util;
@SuppressWarnings("serial")
public class FoundList extends HttpServlet {
	private Util util = new Util();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		HttpSession session = req.getSession(true);
		String username = null;
		if(session.getAttribute("username")!=null)
			username = (String)session.getAttribute("username");
		boolean loged = false;
		if(session.getAttribute("loged")!=null)
			loged = session.getAttribute("loged").equals("true");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println(util.headWithTitle("Profile photos"));
			out.println(util.StaticPart(username, loged, false));
			String link = req.getParameter("user");
			link=util.makeLink(link);
			if (link.contains(".com/p/")){
				resp.sendRedirect("image?img="+link);
			} else {
				try {
					out.println(util.getFullList(loged, util.makeUserLink(link)));
					if(util.getFullList(loged, util.makeUserLink(link)).length()==0)
						out.println("<p>URL is not available</p>");
				} catch (IOException e) {
					out.println("<p>URL is not available</p>");
				}
			}
			out.println("</center>");
			out.println("</span>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
