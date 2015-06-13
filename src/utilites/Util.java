package utilites;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Util {
	private StringBuilder sb = new StringBuilder();
	private Parser parser = new Parser();

	public String headWithTitle(String title) {
		sb.delete(0, sb.toString().length());
		sb.append("<!DOCTYPE html>\n");
		sb.append("<html>\n");
		sb.append("<head>\n");
		sb.append("<title>");
		sb.append(title);
		sb.append("</title>\n");
		sb.append("<link href='css/style-work.css' rel='stylesheet' type='text/css' />");
		sb.append("<meta charset='utf-8'>\n");
		sb.append("</head>\n");
		return sb.toString();
	}

	public String StaticPart(boolean profile) {
		sb.delete(0, sb.toString().length());
		sb.append("<body");
		if (!profile)
			sb.append(" style='padding-bottom:25px;");
		sb.append(">\n");
		sb.append("<span class='container'>\n");
		sb.append("<center>\n");
		sb.append("<div class='top_panel'>\n");
		sb.append("<div class='logo_short'>\n");
		sb.append("<span class='top_logo'><a href='/photosea' style='color:#fff; opacity'><img src='https://dl.dropboxusercontent.com/s/6difuypys7lrf7t/whiteLogo.png?dl=0' style='height:30px; width:30px; margin-top:4px;'></a></span>\n");
		sb.append("</div>\n");
		sb.append("<div class='form form-working'>\n");
		sb.append("<form method='get' action='foundlist'>\n");
		sb.append("<input placeholder='Search photos' class='link' type='text' size='40' name='user'/>\n");
		sb.append("<input class='gobtn' type='submit' value=''/>\n");
		sb.append("</form>\n");
		sb.append("</div>\n");
		sb.append("<ul class='top-menu'>\n");
		sb.append("<li><a href='#'>contacts</a></li>\n");
		sb.append("<li><a href='#'>about service</a></li>\n");
		sb.append("<li><a href='sign-in'>sign in</a></li>\n");
		sb.append("</ul>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public String getIndexStaticPart() {
		sb.append("<body style='height:100%; background-color:none;'>\n");
		sb.append("<span class='container'>\n");
		sb.append("<center>\n");
		sb.append("<div class='top_panel' style='background:none;'>\n");
		sb.append("<ul class='top-menu index_top_menu'>\n");
		sb.append("<li><a href='#'>contacts</a></li>\n");
		sb.append("<li><a href='#'>about service</a></li>\n");
		sb.append("<li><a href='/sign-in'>sign in</a></li>\n");
		sb.append("</ul>\n");
		sb.append("</div>\n");
		sb.append("<div class='logo'>\n");
		sb.append("<img src='https://dl.dropboxusercontent.com/s/4xp2u2z2mxq8zq2/newLogo.png?dl=0'>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public String getMailForm(String img) {
		sb.delete(0, sb.toString().length());
		sb.append("<div class='result result-working'>\n");
		sb.append("<form method='get' class='form mailform'>\n");
		sb.append("<table style='width:100%; padding:0 50px;x' >\n");
		sb.append("<tbody style='position: relative; '>\n");
		sb.append("<p style='text-align: left; margin-left:50px; font-size: 24px; color:#36474f;'>Send by mail</p>\n");
		sb.append("<tr style='float:left; margin-bottom:25px;'>\n");
		sb.append("<td>\n");
		sb.append("<p>From: <span style='opacity: 0.5;'>example@gmail.com</span></p>\n");
		sb.append("<span>To: </span><input class='rec_mail' name='rec_mail' placeholder='reciever's mail'/>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("<tr style='position:relative;'>\n");
		sb.append("<td class='mail_text'>\n");
		sb.append("<div class='img_description'>\n");
		sb.append("<input style='display:none;' name='img_src' value='" + img
				+ "'>\n");
		sb.append("<textarea class='mail_text' name='mess_text'></textarea>\n");
		sb.append("</div>\n");
		sb.append("<div class='send_btn'>\n");
		sb.append("<input class='send_btn' type='submit' value='send'/>\n");
		sb.append("</div>\n");
		sb.append("</td>\n");
		sb.append("<td class='image mail-image'>\n");
		sb.append("<img src='" + img + "'/>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("</tbody>\n");
		sb.append("</table>\n");
		sb.append("</form>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public String getSearchForm() {
		sb.delete(0, sb.toString().length());
		sb.append("<div class='");
		sb.append("form-index");
		sb.append("'>\n");
		sb.append("<form method='get'" + "action='/foundlist' >\n");
		sb.append("<input placeholder='profile/image url' class='link' type='text' size='40' name='user' />\n");
		sb.append("<br/>\n");
		sb.append("<input class='gobtn' type='submit' value='search'/>\n");
		sb.append("</form>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public String getLoginForm() {
		sb.delete(0, sb.toString().length());
		sb.append("<div class='result-login result-working'>\n");
		sb.append("<div class='form login-form'>\n");
		sb.append("<p class='page_name' style='margin: 15px 0 20px 0;'>Sign in</p>\n");
		sb.append("<form method='post' action='photosea'>\n");
		sb.append("<input placeholder='Username' class='sgnin' type='text' size='40' name='login' /><br/>\n");
		sb.append("<input placeholder='Password' class='sgnin' type='password' size='40' name='password' /><br/>\n");
		sb.append("<a class='changeLogin' href='sign-up'>Create account</a>\n");
		sb.append("<input class='gobtn' type='submit' value='sign in'/>\n");
		sb.append("</form>\n");
		sb.append("</div>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public String getRegisterForm() {
		sb.delete(0, sb.toString().length());
		sb.append("<div class='result-login result-working'>\n");
		sb.append("<div class='form login-form'>\n");
		sb.append("<p class='page_name' style='margin: 15px 0 20px 0;'>Create an account</p>\n");
		sb.append("<form method='post' action='photosea'>\n");
		sb.append("<input placeholder='Username' class='sgnin' type='text' size='40' name='login' /><br/>\n");
		sb.append("<input placeholder='Password' class='sgnin' type='password' size='40' name='password' /><br/>\n");
		sb.append("<input placeholder='Confirm password*' class='sgnin' type='password' size='40' name='passwordconf' /><br/>\n");
		sb.append("<input placeholder='Dropbox username' class='sgnin' type='text' size='40' name='drpname' /><br/>\n");
		sb.append("<input placeholder='Dropbox password' class='sgnin' type='password' size='40' name='drppass' /><br/>\n");
		sb.append("<a class='changeLogin' href='sign-in'>Already registered</a>\n");
		sb.append("<input class='gobtn' type='submit' value='sign up'/>\n");
		sb.append("</form>\n");
		sb.append("</div>\n");
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

	public String getFullList(String user) throws IOException {
		String url = "https://instagram.com/" + user
				+ "?max_id=9999999999999999999";
		Parser parser = new Parser();
		String code = parser.parse(url);
		ArrayList<String[]> URLs = parser.getAllURLs(code);
		String userImg = parser.getAuthor(code)[1];
		int i = 0;
		sb.delete(0, sb.toString().length());
		sb.append("<div class='result-poly result-working'>\n");
		sb.append("<p class='page_name'>Latest photos of <span class='prof'>"
				+ user + "</span></p>\n");
		sb.append("<table style='width:100%;'>\n");
		sb.append("<tbody style='position: relative;'>\n");
		for (String[] img : URLs) {
			if (i % 4 == 0) {
				sb.append("<tr class='small-row'>\n");
			}
			sb.append("<td class='small-image'>\n");
			sb.append("<div style='position:relative'>");
			sb.append("<div class='img-tools'>");
			sb.append("<div class='tool'><a href='' title='Save to my profile'><img src='https://dl.dropboxusercontent.com/s/yz1x9hi7b8jdo70/save-img2.png'></a></div>");
			sb.append("</div>");
			sb.append("<span><center>\n");
			sb.append("<div class='timestamp'>");
			sb.append("<p>" + parser.convertDate(img[1]) + "</p>");
			sb.append("</div>");
			sb.append("<a href='/image?photo=" + img[0] + "&date=" + img[1]
					+ "&user=" + user + "&userImg=" + userImg + "'>");
			sb.append("<img class='simg' src='" + img[0] + "'/>");
			sb.append("</a>");
			sb.append("</center></span>\n");
			sb.append("</div>");
			sb.append("</td>");
			if (i % 4 == 3 || i == URLs.size()) {
				sb.append("</tr>\n");
			}
			i++;
		}
		sb.append("</tbody>\n");
		sb.append("</table>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public String getSinglePhoto(String url) throws IOException {
		Map info = parser.imgInfo(parser.parse(url));
		sb.delete(0, sb.length());
		sb.append("<div class='result-single result-working'>\n");
		sb.append("<img src='" + info.get("url") + "'/>\n");
		sb.append("<div class='info'>\n");
		sb.append("<div class='instr'>\n");
		sb.append("<div><a href=''><img src='https://dl.dropboxusercontent.com/s/37j9dsgajgf1jjp/save1.png?dl=0'></a></div>\n");
		sb.append("<div><a href='mail?img="
				+ info.get("url")
				+ "'><img src='https://dl.dropboxusercontent.com/s/c5vdkdqm0mt8msl/mail.png?dl=0'></a></div>\n");
		sb.append("<div><a href=''><img src='https://dl.dropboxusercontent.com/s/67ouce7y2gh6f8u/dropbox.png?dl=0'></a></div>\n");
		sb.append("</div>\n");
		sb.append("<div class='desc'>\n");
		sb.append("<img src='" + info.get("authorImg") + "'>\n");
		sb.append("<a class='un' href='foundlist?user=" + info.get("author")
				+ "'><p>" + info.get("author") + "</p></a>\n");
		sb.append("<p class='pd'>" + info.get("date") + "</p>\n");
		sb.append("</div>\n");
		sb.append("</div>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	public String getSinglePhoto(String img, String date, String user,
			String userImg) {
		sb.delete(0, sb.length());
		sb.append("<div class='result-single result-working'>\n");
		sb.append("<img src='" + img + "'/>\n");
		sb.append("<div class='info'>\n");
		sb.append("<div class='instr'>\n");
		sb.append("<div><a href=''><img src='https://dl.dropboxusercontent.com/s/37j9dsgajgf1jjp/save1.png?dl=0'></a></div>\n");
		sb.append("<div><a href='mail?img="
				+ img
				+ "'><img src='https://dl.dropboxusercontent.com/s/c5vdkdqm0mt8msl/mail.png?dl=0'></a></div>\n");
		sb.append("<div><a href=''><img src='https://dl.dropboxusercontent.com/s/67ouce7y2gh6f8u/dropbox.png?dl=0'></a></div>\n");
		sb.append("</div>\n");
		sb.append("<div class='desc'>\n");
		sb.append("<img src='" + userImg + "'>\n");
		sb.append("<a class='un' href='foundlist?user=" + user + "'><p>" + user
				+ "</p></a>\n");
		sb.append("<p class='pd'>" + date + "</p>\n");
		sb.append("</div>\n");
		sb.append("</div>\n");
		sb.append("</div>\n");
		return sb.toString();
	}

	/*
	 * public static boolean isLink(String url) { if
	 * (url.indexOf("instagram.com") >= 0) { if (url.indexOf("https://") < 0 ||
	 * url.indexOf("http://") < 0) url = "https://" +
	 * url.substring(url.indexOf("instagram.com")); return true; } return false;
	 * }
	 */

	public String makeLink(String url) {
		if (url.contains("https://instagram.com/"))
			return url;
		else if (url.contains("instagram.com/"))
			url = "https://" + url.substring(url.indexOf("instagram.com"));
		else
			url = "https://instagram.com/" + url;
		return url;
	}

	public String makeUserLink(String url) {
		String first = "https://instagram.com/";
		String res = url.substring(first.length());
		res.replace("/", "");
		return res;
	}

	public String getSavedPhotos() throws IOException {
		String url = "https://instagram.com/" + "komarynets"
				+ "?max_id=9999999999999999999";
		Parser parser = new Parser();
		String code = parser.parse(url);
		ArrayList<String[]> URLs = parser.getAllURLs(code);
		String userImg = parser.getAuthor(code)[1];
		int i = 0;

		sb.delete(0, sb.length());
		sb.append("<table class='saved'>\n");
		sb.append("<tbody>\n");
		sb.append("<tr class='panel_top'>\n");
		sb.append("<td class='panel panel_top'>\n");
		sb.append("<div class='profile_short'><img src='https://dl.dropboxusercontent.com/s/mxjjvabdqebptuz/profile.png?dl=0'> <span>ZimmerSan</span></div>\n");
		sb.append("</td>\n");
		sb.append("<td class='svdPhotos svd_top'>\n");
		sb.append("<p style='font-size:22px; color:#36474f; margin:0; margin-left:10px; '>Saved photos</p>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("<tr>\n");
		sb.append("<td class='panel'>\n");
		sb.append("<div class='btns'>\n");
		sb.append("<div class='btn btn_active'><a href='saved'><img src='https://dl.dropboxusercontent.com/s/eaoshe03azv9ixa/folder.png?dl=0'> <span>Saved photos</span></a></div>\n");
		sb.append("<div class='btn'><a href='editprofile'><img src='https://dl.dropboxusercontent.com/s/0fc530wkfuysz7b/gear.png?dl=0'> <span>Edit profile</span></a></div>\n");
		sb.append("</div>\n");
		sb.append("</td>\n");
		sb.append("<td class='svdPhotos mainsvd'>\n");
		sb.append("<table class='savedIn' style='width:100%;'>\n");
		sb.append("<tbody style='position: relative;'>\n");

		for (String[] img : URLs) {
			if (i % 5 == 0) {
				sb.append("<tr class='small-row saved'>\n");
			}
			sb.append("<td class='small-image'><span><center>\n");
			sb.append("<a href='view?img=" + img[0] + "'><img src='" + img[0]
					+ "'/></a>\n");
			sb.append("</center></span></td>\n");
			// sb.append("<div class='tool'><a href='' title='Save to my profile'><img src='https://dl.dropboxusercontent.com/s/yz1x9hi7b8jdo70/save-img2.png'></a></div>");
			if (i % 5 == 4 || i == URLs.size()) {
				sb.append("</tr>\n");
			}
			i++;
		}

		sb.append("</tbody>\n");
		sb.append("</table>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("<tr class='paginator'>\n");
		sb.append("<td class='panel'></td>\n");
		sb.append("<td class='svdPhotos'>\n");
		sb.append("<div class='paginator'><a href=''>First page</a><span>  </span><a href=''>Next page >></a></div>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("</tbody>\n");
		sb.append("</table>\n");
		return sb.toString();
	}

	public String getProfileEditor(String duser) {
		sb.delete(0, sb.length());
		sb.append("<table class='saved'>\n");
		sb.append("<tbody>\n");
		sb.append("<tr class='panel_top'>\n");
		sb.append("<td class='panel panel_top'>\n");
		sb.append("<div class='profile_short'><img src='https://dl.dropboxusercontent.com/s/mxjjvabdqebptuz/profile.png?dl=0'> <span>ZimmerSan</span></div>\n");
		sb.append("</td>\n");
		sb.append("<td class='svdPhotos svd_top'>\n");
		sb.append("<p style='font-size:22px; color:#36474f; margin:0; margin-left:10px; '>Saved photos</p>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("<tr>\n");
		sb.append("<td class='panel'>\n");
		sb.append("<div class='btns'>\n");
		sb.append("<div class='btn '><a href='saved'><img src='https://dl.dropboxusercontent.com/s/eaoshe03azv9ixa/folder.png?dl=0'> <span>Saved photos</span></a></div>\n");
		sb.append("<div class='btn btn_active'><a href='editprofile'><img src='https://dl.dropboxusercontent.com/s/0fc530wkfuysz7b/gear.png?dl=0'> <span>Edit profile</span></a></div>\n");
		sb.append("</div>\n");
		sb.append("</td>\n");
		sb.append("<td class='svdPhotos mainsvd'>\n");
		sb.append("<form method='post' class='profedit'>\n");
		sb.append("<input class='in' type='password' name='oldpass' placeholder='Old password'/>\n");
		sb.append("<br/>\n");
		sb.append("<input class='in' type='password' name='newpass' placeholder='New password'/>\n");
		sb.append("<br/>\n");
		sb.append("<input class='in' type='password' name='confnewpass' placeholder='Confirm password'/>\n");
		sb.append("<br/>\n");
		sb.append("<input class='in' name='duname' placeholder='Dropbox username' value='");
		if (duser != null)
			sb.append(duser);
		sb.append("'/>\n");
		sb.append("<br/>\n");
		sb.append("<input class='in' type='password' name='dpass' placeholder='Dropbox password'/>\n");
		sb.append("<br/>\n");
		sb.append("<input class='sb' type='submit' value='Save'>\n");
		sb.append("</form>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("</tbody>\n");
		sb.append("</table>\n");
		return sb.toString();
	}

	public String getProfileSingle(String url, String date, String author,
			String userIcon) {
		sb.append("<table class='saved'>\n");
		sb.append("<tbody>\n");
		sb.append("<tr class='panel_top'>\n");
		sb.append("<td class='panel panel_top'>\n");
		sb.append("<div class='profile_short'><img src='https://dl.dropboxusercontent.com/s/mxjjvabdqebptuz/profile.png?dl=0'> <span>ZimmerSan</span></div>\n");
		sb.append("</td>\n");
		sb.append("<td class='svdPhotos svd_top'>\n");
		sb.append("<p style='font-size:22px; color:#36474f; margin:0; margin-left:10px; '>Saved photos</p>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("<tr>\n");
		sb.append("<td class='panel'>\n");
		sb.append("<div class='btns'>\n");
		sb.append("<div class='btn btn_active'><a href='saved'><img src='https://dl.dropboxusercontent.com/s/eaoshe03azv9ixa/folder.png?dl=0'> <span>Saved photos</span></a></div>\n");
		sb.append("<div class='btn'><a href='editprofile'><img src='https://dl.dropboxusercontent.com/s/0fc530wkfuysz7b/gear.png?dl=0'> <span>Edit profile</span></a></div>\n");
		sb.append("</div>\n");
		sb.append("</td>\n");
		sb.append("<td class='svdPhotos mainsvd'>\n");
		sb.append("<div class='image'>\n");
		sb.append("<img class='i' src='" + url + "'>\n");
		sb.append("<div class='info'>\n");
		sb.append("<div class='instr'>\n");
		sb.append("<div style='background-color: #13b9d5;'><a href=''><img src='https://dl.dropboxusercontent.com/s/65x9m5di52rkexw/delete.png?dl=0'></a></div>\n");
		sb.append("<div style='background-color: #36474f;'><a href='mail&img="
				+ url
				+ "'><img src='https://dl.dropboxusercontent.com/s/z860z31419vftje/mail_white.png?dl=0'></a></div>\n");
		sb.append("<div style='background-color: #1ebba5;'><a href=''><img src='https://dl.dropboxusercontent.com/s/51x4uj6l99rcpsx/dropbox_white.png?dl=0'></a></div>\n");
		sb.append("</div>\n");
		sb.append("<div class='desc'>\n");
		// sb.append("<img src='"+userImg+"'>\n");
		sb.append("<img src='https://igcdn-photos-e-a.akamaihd.net/hphotos-ak-xfp1/t51.2885-19/10957242_547815902025140_73434984_a.jpg'>\n");
		// sb.append("<a class='un' href=''><p>"+author+"</p></a>\n");
		sb.append("<a class='un' href=''><p>username</p></a>\n");
		// sb.append("<p class='pd'>"+date+"</p>\n");
		sb.append("<p class='pd'>dd:mm:yyyy</p>\n");
		sb.append("</div>\n");
		sb.append("</div>\n");
		sb.append("</div>\n");
		sb.append("</td>\n");
		sb.append("</tr>\n");
		sb.append("</tbody>\n");
		sb.append("</table>\n");
		return sb.toString();
	}
}