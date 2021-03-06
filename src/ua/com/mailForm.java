package ua.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.*;

import utilites.Util;

@SuppressWarnings("serial")
public class MailForm extends HttpServlet {
	private Util util = new Util();
	private StringBuilder sb = new StringBuilder();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		HttpSession session = req.getSession(true);
		String username = null;
		if(session.getAttribute("username")!=null)
			username = (String)session.getAttribute("username");
		boolean loged = false;
		if(session.getAttribute("loged")!=null)
			loged = session.getAttribute("loged").equals("true");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.println(util.headWithTitle("Send mail"));
		
		out.println(util.StaticPart(username, loged, false));
		out.println(util.getMailForm(req.getParameter("img")));
		out.println("</center>");
		out.println("</span>");
		out.println("</body>");
		out.println("</html>");
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession(true);
		String name = req.getParameter("rec_mail");
		String text = req.getParameter("mess_text");
		String im = req.getParameter("img");
		if(name!=null&&!name.equals("")){
			if(text!=null) MailSend.mailSend(req,resp,name,text,im);
			else MailSend.mailSend(req,resp,req.getParameter("rec_mail"),"",req.getParameter("img"));
		}else{
			resp.getWriter().println("Please enter adress of reciever");
			doGet(req,resp);
			
		}
			
			
	}
}
