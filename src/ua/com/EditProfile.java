package ua.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilites.Util;

public class EditProfile extends HttpServlet{
	private Util util = new Util();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println(util.headWithTitle("Edit profile"));
			out.println(util.StaticPart(true));
			out.println(util.getProfileEditor(null));
			out.println("</center>");
			out.println("</span>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}