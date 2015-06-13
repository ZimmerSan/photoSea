package ua.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilites.Util;

public class Saved extends HttpServlet{
	private Util util = new Util();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println(util.headWithTitle("Saved photos"));
			out.println(util.StaticPart(true));
			out.println(util.getSavedPhotos());
			out.println("</center>");
			out.println("</span>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
