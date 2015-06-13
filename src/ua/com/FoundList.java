package ua.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

import utilites.Util;

@SuppressWarnings("serial")
public class FoundList extends HttpServlet {
	private Util util = new Util();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println(util.headWithTitle("Profile photos"));
			out.println(util.StaticPart(false));
			String link = req.getParameter("user");
			link=util.makeLink(link);
			if (link.contains(".com/p/")){
				resp.sendRedirect("image?img="+link);
			} else {
				try {
					out.println(util.getFullList(util.makeUserLink(link)));
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
