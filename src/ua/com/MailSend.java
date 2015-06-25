package ua.com;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@SuppressWarnings("serial")
public class MailSend  {
	

	public static void mailSend(HttpServletRequest req ,HttpServletResponse resp,String adress, String body,String foto) {
		try{
			 Properties props = new Properties();
		        Session session = Session.getDefaultInstance(props, null);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("salnikova.olia@gmail.com"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(adress));
			Multipart multipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();
			String image = " <span><img src=\'" +foto+ "'></span>";
			messageBodyPart.setContent(body+image, "text/html");
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			Transport.send(msg);
			resp.sendRedirect("/photosea");
		}catch(Exception e){
			e.getMessage();
		}
	}

	
}
	