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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.QueryResultIterable;

import utilites.User;
import utilites.Util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.geronimo.mail.util.Hex;

public class RegisterForm extends HttpServlet {
	
	private static final String secretKey = "PRIE7$oG2uS-Yf17kEnUEpi5hvW/#AFo";
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
		int loginAlreadyUsed=0;
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
		Query q = new Query("User");
		PreparedQuery pq = datastore.prepare(q);
		QueryResultIterable<Entity> results=pq.asQueryResultIterable();
		String check=null;
		for (Entity entity:results) {
			String l = (String) entity.getProperty("username");
			if(l.equals(req.getParameter("login"))){
				check=check+l;
				break;
			}
		}
		if ((check==null)&&(login != null) && (password != null) && (confPass != null)
				&& !(login.equals("")) && !(password.equals(""))
				&& !(confPass.equals("")) && (password.equals(confPass))) {
			String hashPassword=hmacSha1(password, secretKey);
			String hashDrPass=hmacSha1(drPass, secretKey);
			User user = new User(login, hashPassword, drLog, hashDrPass);
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
	public static String hmacSha1(String value, String key) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();           
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes());

            // Convert raw bytes to Hex
            byte[] hexBytes = new Hex().encode(rawHmac);

            //  Covert array of Hex bytes to a String
            return new String(hexBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
       
    }
//	public User check(String username){
//		Query q = new Query("User");
//		PreparedQuery pq = datastore.prepare(q);
//		QueryResultIterable<Entity> results=pq.asQueryResultIterable();
//		for(Entity entity:results){
//			if(entity.getProperty("username").equals(username)){
//				return new User((String) entity.getProperty("username"),(String) entity.getProperty("password"),(String) entity.getProperty("drLog"),(String) entity.getProperty("drPass"));
//			}
//		}
//		return null;
//	}
//	
}
