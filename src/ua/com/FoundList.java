package ua.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
			out.println(util.headWithTitle("photoSea"));
			out.println(util.StatPart());
			out.println(util.getSearchForm(false));
			String link = req.getParameter("link");
			if (Util.isLink(link)){
				try {
					out.println(util.getFullList(link));
				} catch (IOException e) {
					out.println("<p>URL is not available</p>");
				}
			} else {
				out.println("<p>URL is not available</p>");
			}
			out.println("</center>");
			out.println("</span>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
