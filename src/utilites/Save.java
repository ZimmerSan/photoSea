package utilites;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class Save extends HttpServlet{
	
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private static Cache cache;
	private Util util = new Util();
	
	public Save(){
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
		
		Image image = new Image(req.getParameter("url"), req.getParameter("date"), req.getParameter("author"), req.getParameter("authorImg"));
		Entity ent = image.getEntity();
		ent.setProperty("username", session.getAttribute("username"));
		datastore.put(ent);
		util.updateCache((String)session.getAttribute("username"), ent, true);
		resp.sendRedirect(req.getParameter("lastPage"));
	}
}
