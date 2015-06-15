package ua.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.*;

import utilites.Util;

@SuppressWarnings("serial")
public class PhotoSeaServlet extends HttpServlet {
	private Util util = new Util();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession(true);
		
		//remove later
				session.setAttribute("loged", "true");
				session.setAttribute("username", "zimmersan");
				
		String username = null;
		if(session.getAttribute("username")!=null)
			username = (String)session.getAttribute("username");		
		boolean loged = false;
		if(session.getAttribute("loged")!=null)
			loged = session.getAttribute("loged").equals("true");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(util.headWithTitle("photoSea"));
		
		out.println(util.getIndexStaticPart(loged));
		out.println(util.getSearchForm());

		out.println("</center>");
		out.println("</span>");
		out.println("</body>");
		out.println("</html>");
	}
}
