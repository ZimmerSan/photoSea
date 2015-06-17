package ua.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import utilites.Util;

public class LoginForm extends HttpServlet {
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
		out.println(util.headWithTitle("Sign in"));
		out.println(util.StaticPart(username, loged, false));
		out.println(util.getLoginForm(null));
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
		resp.setContentType("text/html");
		
		// To home if loged
		if(loged)
			resp.sendRedirect("/photosea");
		
		PrintWriter out = resp.getWriter();
		
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		Query q = new Query("User");
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			String l = (String) result.getProperty("username");
			String p = (String) result.getProperty("password");
			if ((login != null) && (password != null) && !(login.equals(""))
					&& !(password.equals("")) && (l.equals(login))
					&& (p.equals(password))) {
				session.setAttribute("loged", "true");
				session.setAttribute("username", l);
				loged = true;
				resp.sendRedirect("/photosea");
				break;
			}
		}
		if(!loged){
			out.println(util.headWithTitle("Sign in"));
			out.println(util.StaticPart(username, loged, false));
			out.println(util.getLoginForm(login));
			out.println("</center>");
			out.println("</span>");
			out.println("</body>");
			out.println("</html>");
		}
	}

	void deleteUser(String login, String password) {
		// User user=new User(login, password,);
		Query query = new Query(login);
		query.setKeysOnly();
		ArrayList<Key> keys = new ArrayList<Key>();
		for (final Entity entity : datastore.prepare(query).asIterable(
				FetchOptions.Builder.withLimit(100000))) {
			keys.add(entity.getKey());
		}
		// if(login.equals(datastore.get(keys))){
		datastore.delete(keys);
		// }

	}
}
