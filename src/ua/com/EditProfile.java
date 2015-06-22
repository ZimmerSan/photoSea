package ua.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import utilites.Util;
import utilites.MyCodding;

public class EditProfile extends HttpServlet{
	private Util util = new Util();
	private static DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	private static final String secretKey = "zimFsHRMndsldnJS-Yf17kEnUEpi5hvW/#AFo";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
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
			out.println(util.headWithTitle("Edit profile"));
			out.println(util.StaticPart(username, loged, true));
			out.println(util.getProfileEditor(session.getAttribute("username")));
			out.println("</center>");
			out.println("</span>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession(true);
		String username = null;
		if(session.getAttribute("username")!=null)
			username = (String)session.getAttribute("username");
		boolean loged = false;
		if(session.getAttribute("loged")!=null)
			loged = session.getAttribute("loged").equals("true");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		
		Query q = new Query("User");
		q.addFilter("username", Query.FilterOperator.EQUAL, (String) session.getAttribute("username"));
		PreparedQuery pq = datastore.prepare(q);
		Entity result = pq.asSingleEntity();
		try {
			out = resp.getWriter();
			out.println(util.headWithTitle("Edit profile"));
			out.println(util.StaticPart(username, loged, true));
			out.println(util.getProfileEditor(session.getAttribute("username")));
			
			// make hashing
			if((MyCodding.hmacSha1(req.getParameter("oldpass"), secretKey).equals(result.getProperty("password")))&&(req.getParameter("newpass").equals(req.getParameter("confnewpass")))){
				Transaction txn = datastore.beginTransaction();
				try {
				result.setProperty("password", utilites.MyCodding.hmacSha1(req.getParameter("newpass"), secretKey));
				if(req.getParameter("duname")!=null)
					result.setProperty("drLog",req.getParameter("duname"));
				if(req.getParameter("dpass")!=null)
					result.setProperty("drPass",req.getParameter("dpass"));
				datastore.put(result);
				txn.commit();
				} finally {
				if (txn.isActive()) {
				txn.rollback();
				}
				}
				out.println("Done!");
			} else {
				out.println("Passwords are incorrect or do not match");
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