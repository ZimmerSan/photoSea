package utilites;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.appengine.api.datastore.Entity;

public class Image {
	private String url, date, author, authorImg;
	
	public Image(String url, String date, String author, String authorImg){
		this.url = url;
		this.date = date;
		this.author = author;
		this.authorImg = authorImg;
	}
	
	public Entity getEntity(){
		Entity img = new Entity("Image");
		img.setProperty("url", url);
		img.setProperty("date", date);
		img.setProperty("author", author);
		img.setProperty("authorImg", authorImg);
		img.setProperty("saved", getCurrentTimeStamp());
		return img;
	}
	
	public static String getCurrentTimeStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}
}
