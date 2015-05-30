package utilites;

import java.io.IOException;
import java.util.ArrayList;

public class Util {
	private StringBuilder sb = new StringBuilder();

	public String headWithTitle(String title) {
		sb.delete(0, sb.toString().length());
		sb.append("<!DOCTYPE html>\n");
		sb.append("<html>\n");
		sb.append("<head>\n");
		sb.append("<title>");
		sb.append(title);
		sb.append("</title>\n");
		sb.append("<link href='style.css' rel='stylesheet' type='text/css' />");
		sb.append("<meta charset='utf-8'>\n");
		sb.append("</head>\n");
		return sb.toString();
	}

	public String StatPart() {
		sb.delete(0, sb.toString().length());
		sb.append("<body style='height:100%;'>\n");
		sb.append("<span class='container'>\n");
		sb.append("<center>\n");
		sb.append("<ul class='top-menu'>\n");
		sb.append("<li>\n");
		sb.append("<a href='#'>\n");
		sb.append("contacts\n");
		sb.append("</a>\n");
		sb.append("</li>\n");
		sb.append("<li>\n");
		sb.append("<a href='#'>\n");
		sb.append("about service\n");
		sb.append("</a>\n");
		sb.append("</li>\n");
		sb.append("<li>\n");
		sb.append("<a href='sign-in'>\n");
		sb.append("sign in\n");
		sb.append("</a>\n");
		sb.append("</li>\n");
		sb.append("</ul>\n");
		sb.append("<div class='logo'>\n");
		sb.append("<a href='photosea'>");
		sb.append("<img src='https://dl.dropboxusercontent.com/s/8ge1nf1m0d72t14/logo.png'>\n");
		sb.append("</a>");
		sb.append("</div>\n");
		return sb.toString();
	}

	public String getSearchForm(boolean home) {
		sb.delete(0, sb.toString().length());
		sb.append("<div class='form");
		if (home)
			sb.append(" search-form");
		sb.append("'>\n");
		sb.append("<form method='get'" + "action='/foundlist' >\n");
		sb.append("<input placeholder='profile/image url' class='link' type='text' size='40' name='link' />\n");
		sb.append("<br/>\n");
		sb.append("<input class='gobtn' type='submit' value='find'/>\n");
		sb.append("</form>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public String getLoginForm() {
		sb.delete(0, sb.toString().length());
		sb.append("<div class='form login-form'>\n");
		sb.append("<p>Sign in</p>");
		sb.append("<form method='post' action='photosea' >\n");
		sb.append("<input placeholder='username' class='link sgnin' type='text' size='40' name='login' />\n");
		sb.append("<br/>\n");
		sb.append("<input placeholder='password' class='link sgnin' type='password' size='40' name='password' />\n");
		sb.append("<br/>\n");
		sb.append("<p>Not registered? <a href='sign-up'>Sign up</a></p>");
		sb.append("<input class='gobtn' type='submit' value='sign in'/>\n");
		sb.append("</form>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public String getRegisterForm() {
		sb.delete(0, sb.toString().length());
		sb.append("<div class='form login-form'>\n");
		sb.append("<p>Sign up</p>");
		sb.append("<form method='post' action='photosea' >\n");
		sb.append("<input placeholder='username' class='link sgnin' type='text' size='40' name='login' />\n");
		sb.append("<br/>\n");
		sb.append("<input placeholder='password' class='link sgnin' type='password' size='40' name='pass' />\n");
		sb.append("<br/>\n");
		sb.append("<input placeholder='confirm password' class='link sgnin' type='password' size='40' name='conpass' />\n");
		sb.append("<br/>\n");
		sb.append("<p>Already registered? <a href='sign-in'>Sign in</a></p>");
		sb.append("<input class='gobtn' type='submit' value='sign up'/>\n");
		sb.append("</form>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public String singleImgInfo(String url) throws IOException {
		Parser parser = new Parser();
		String code = parser.parse(url);
		String imgURL = parser.findSingleImageURL(code);

		sb.delete(0, sb.toString().length());
		sb.append("<div class='result'>\n");
		sb.append("<table style='float:left;'>\n");
		sb.append("<tbody style='position: relative;'>\n");
		sb.append("<tr>\n");
		sb.append("<td class='image'>\n");
		sb.append("<img src='" + imgURL + "'/>\n");
		sb.append("</td>\n");
		sb.append("<td class='functions'>\n");
		sb.append("<div class='img_description'>\n");
		sb.append("<p>Posted by: <a href='#'>username</a></p>\n");
		sb.append("<p>Date: dd.mm.yyyy</p>\n");
		sb.append("</div>\n");
		sb.append("<div class='img_functions'>\n");
		sb.append("<ul>\n");
		sb.append("<li>Dimensions:</li>\n");
		sb.append("<li class='dimension'><a>612x612</a></li>\n");
		sb.append("<li class='dimension'><a>320x320</a></li>\n");
		sb.append("<li class='dimension'><a>150x150</a></li>\n");
		sb.append("<li class='services'><a href='#'><img src='https://dl.dropboxusercontent.com/s/c5vdkdqm0mt8msl/mail.png?dl=0'></a></li>\n");
		sb.append("<li class='services'><a href='#'><img src='https://dl.dropboxusercontent.com/s/67ouce7y2gh6f8u/dropbox.png'></a></li>\n");
		sb.append("</ul>\n");
		sb.append("</div>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("</tbody>\n");
		sb.append("</table>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public String getFullList(String url) throws IOException {
		Parser parser = new Parser();
		String code = parser.parse(url);
		ArrayList<String> URLs = parser.getAllURLs(code);
		int i = 0;
		sb.delete(0, sb.toString().length());
		sb.append("<div class='result'>\n");
		sb.append("<table style='width:100%;'>\n");
		sb.append("<tbody style='position: relative;'>\n");
		for (String imgUrl : URLs) {
			if (i % 5 == 0) {
				sb.append("<tr class='small-row'>\n");
			}
			sb.append("<td class='small-image'>\n");
			sb.append("<div style='position:relative'>");
			sb.append("<div class='img-tools'>");
			sb.append("<div class='tool'><a href=''><img src='https://dl.dropboxusercontent.com/s/thjhumug8gmbujf/save-img.png?dl=0'></a></div>");
			sb.append("<div class='tool'><a href=''><img src='https://dl.dropboxusercontent.com/s/dxz2otg376cntz6/zoom-img.png?dl=0'></a></div>");
			sb.append("</div>");
			sb.append("<span><center>\n");
			sb.append("<div class='timestamp'>");
			sb.append("<p>22 may 2015</p>");
			sb.append("</div>");
			sb.append("<a href='/photosea?link=" + imgUrl + "'>");
			sb.append("<img src='" + imgUrl + "'/>");
			sb.append("</a>");
			sb.append("</center></span>\n");
			sb.append("</div>");
			sb.append("</td>");
			if (i % 5 == 4 || i == URLs.size()) {
				sb.append("</tr>\n");
			}
			i++;
		}
		sb.append("</tbody>\n");
		sb.append("</table>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public static boolean isLink(String url) {
		if (url.indexOf("instagram.com") > 0) {
			if (url.indexOf("https://") < 0 || url.indexOf("http://") < 0)
				url = "https://" + url.substring(url.indexOf("instagram.com"));
			return true;
		}
		return false;
	}
}