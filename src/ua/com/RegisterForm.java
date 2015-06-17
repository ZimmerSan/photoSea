package ua.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

import utilites.User;
import utilites.Util;

public class RegisterForm extends HttpServlet {
	private Util util = new Util();
	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession(true);
		String username = null;
		if (session.getAttribute("username") != null)
			username = (String) session.getAttribute("username");
		boolean loged = false;
		if (session.getAttribute("loged") != null)
			loged = session.getAttribute("loged").equals("true");

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println(util.headWithTitle("Sign up"));
		out.println(util.StaticPart(username, loged, false));
		out.println(util.getRegisterForm());
		out.println("</center>");
		out.println("</span>");
		out.println("</body>");
		out.println("</html>");
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		// Session
		HttpSession session = req.getSession(true);
		String username = null;
		if (session.getAttribute("username") != null)
			username = (String) session.getAttribute("username");
		boolean loged = false;
		if (session.getAttribute("loged") != null)
			loged = session.getAttribute("loged").equals("true");
		
		// Get fields
		String login = utilites.MyStringUtils.escapeHTML(req
				.getParameter("login"));
		String password = utilites.MyStringUtils.escapeHTML(req
				.getParameter("password"));
		String confPass = utilites.MyStringUtils.escapeHTML(req
				.getParameter("passwordconf"));
		String drLog = utilites.MyStringUtils.escapeHTML(req
				.getParameter("drpname"));
		String drPass = utilites.MyStringUtils.escapeHTML(req
				.getParameter("drppass"));
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.write("<!DOCTYPE html>");
		out.write("<html><head><title>User</title></head><body>");
		if ((login != null) && (password != null) && (confPass != null)
				&& !(login.equals("")) && !(password.equals(""))
				&& !(confPass.equals("")) && (password.equals(confPass))) {
			User user = new User(login, password, drLog, drPass);
			datastore.put(user.getUserEntity());
			out.write(util.getRegisterForm());
			resp.sendRedirect("sign-in");
		} else {
			out.println(util.headWithTitle("Sign up"));
			out.println(util.StaticPart(username, loged, false));
			out.println(util.getRegisterForm());
			out.write("You have input wrong data");
			out.println("</center>");
			out.println("</span>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}
