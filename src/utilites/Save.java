package utilites;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class Save extends HttpServlet{
	
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession(true);
		
		Image image = new Image(req.getParameter("url"), req.getParameter("date"), req.getParameter("author"), req.getParameter("authorImg"));
		Entity ent = image.getEntity();
		ent.setProperty("username", session.getAttribute("username"));
		datastore.put(ent);
		resp.sendRedirect(req.getParameter("lastPage"));
	}
}
