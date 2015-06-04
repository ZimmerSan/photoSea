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
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(util.headWithTitle("photoSea"));
		
		out.println(util.getIndexStaticPart());
		out.println(util.getSearchForm());

		out.println("</center>");
		out.println("</span>");
		out.println("</body>");
		out.println("</html>");
	}
}
