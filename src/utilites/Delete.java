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
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Delete extends HttpServlet{
	private Util util = new Util();
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	private static Cache cache;
	
	public Delete(){
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
		
		Query q = new Query("Image");
		q.addFilter("url", Query.FilterOperator.EQUAL, (String)req.getParameter("img"));
		q.addFilter("username", Query.FilterOperator.EQUAL, (String)req.getParameter("user"));
		PreparedQuery pq = datastore.prepare(q);
		Entity result = pq.asSingleEntity();
		
		datastore.delete(result.getKey());
		resp.sendRedirect(req.getParameter("lastPage"));
	}
}
