package utilites;

import java.util.Date;

import com.google.appengine.api.datastore.Entity;

public class User {
	private String username;
	private String password;
	private String drLog;
	private String drPass;
	
	public User(String u, String p, String dl, String dp){
		this.username=u;
		this.password=p;
		this.drLog=dl;
		this.drPass=dp;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getDrLog() {
		return drLog;
	}
	
	public String getDrPass() {
		return drPass;
	}
	
	public Entity getUserEntity(){
		Entity user = new Entity("User");
		user.setProperty("username", username);
		user.setProperty("password", password);
		user.setProperty("drLog", drLog);
		user.setProperty("drPass", drPass);
		Date date = new Date();
		user.setProperty("date", date.getDate());
		return user;
	}

}
