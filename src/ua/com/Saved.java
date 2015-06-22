package ua.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;
import utilites.Util;

public class Saved extends HttpServlet{
	private Util util = new Util();
	private static Cache cache;
	
	public Saved(){
		if (cache == null)
			initializeMemCache();
		
	}
	
	private void initializeMemCache(){
		try {
			CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
			cache = cacheFactory.createCache(Collections.emptyMap());
		} catch (CacheException e) {
			// ...
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		HttpSession session = req.getSession(true);
		String username = null;
		if(session.getAttribute("username")!=null)
			username = (String)session.getAttribute("username");
		boolean loged = false;
		if(session.getAttribute("loged")!=null)
			loged = session.getAttribute("loged").equals("true");
		
		if(!loged)
			resp.sendRedirect("sign-in");
		
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = resp.getWriter();
			out.println(util.headWithTitle("Saved photos"));
			out.println(util.StaticPart(username, loged, true));
			out.println(util.getSavedPhotos((String)session.getAttribute("username")));
			out.println("</center>");
			out.println("</span>");
			out.println("</body>");
			out.println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
