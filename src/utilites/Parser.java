package utilites;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

public class Parser {
	//final method for getting an image from single page
	public void getImage(String url) throws IOException{
		String code = parse(url);
		String imgURL = findSingleImageURL(code);
		//download(imgURL);
	}
	
	private String getAuthor(String code){
		int lastIndex = 0;
		int nextIndex = 0;
		String res = "";
		String findCode = "\"owner\":{\"username\":\"";
		lastIndex = code.indexOf(findCode);
		if(lastIndex!=-1){
			String newString = code.substring(lastIndex);
			nextIndex = newString.indexOf("\",\"");
			res=code.substring(lastIndex+findCode.length(), lastIndex+nextIndex);
		}
		return res;
	}
	
	public Map imgInfo(String code){
		Map<String, String> map = new HashMap<String, String>();
		String imgURL = findSingleImageURL(code);
		String author = getAuthor(code);
		map.put("url", imgURL);
		map.put("author", author);
		return map;
	}
	
	public void getProfileImages(String url) throws IOException{
		String profile="";
		if(url.indexOf("instagram.com")==-1){
			profile = String.copyValueOf(url.toCharArray());
			url = "https://instagram.com/"+url+"/";
		} else {
			String findCode = ".com/";
			int lastIndex = url.indexOf(findCode);
			if(lastIndex!=-1){
				String newString = url.substring(lastIndex+findCode.length());
				int nextIndex = newString.indexOf("/");
				if(nextIndex!=-1)
					profile=url.substring(lastIndex+findCode.length(), lastIndex+nextIndex+findCode.length());
				else
					profile=url.substring(lastIndex+findCode.length());
			}
		}
		String code = parse(url);
		//downloadAllImages(getAllURLs(code), profile);
	}
	
	//gets HTML code of the page
	public String parse(String url) throws IOException{
		URL page = new URL(url); 
		URLConnection connection = page.openConnection();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
                connection.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuilder builder = new StringBuilder();
        while ((inputLine = reader.readLine()) != null)
        	builder.append(inputLine);
        reader.close();
        return builder.toString();
	}
	
	//returns an URL of the image of HTML page code
	public String findSingleImageURL(String code){
		int lastIndex = 0;
		int nextIndex = 0;
		String res = "";
		String findCode = "<meta property=\"og:image\" content=\"";
		lastIndex = code.indexOf(findCode);
		if(lastIndex!=-1){
			String newString = code.substring(lastIndex);
			nextIndex = newString.indexOf("\" />");
			res=code.substring(lastIndex+findCode.length(), lastIndex+nextIndex);
		}
		return res;
	}
	
	/*// saves the image from URL to entered folder
	public void download(String url, String folder) throws IOException{
		URL page = new URL(url);
		RenderedImage image = null;
		try {
			image = ImageIO.read(page);
		} catch (IOException e) {
			e.printStackTrace();
		}
		File out = new File(folder+"/"+getRandomString()+".jpg");
		ImageIO.write(image, "jpg", out);
	}
	
	// saves the image from URL to folder "downloads"
	public void download(String url) throws IOException{
		download(url,"downloads");
	}*/
	
	//generates random string of 8 chars for image names
	private String getRandomString(){
		char[] chars = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder builder = new StringBuilder();
		Random rand = new Random();
		for(int i=0; i<8; i++){
			char ch = chars[rand.nextInt(chars.length)];
			builder.append(ch);
		}
		return builder.toString();
	}

	//returns ArrayList of all images URLs on the page
	public ArrayList<String> getAllURLs(String code){
		int lastIndex = 0;
		int nextIndex = 0;
		ArrayList<String> list = new ArrayList<String>();
		String findCode = "standard_resolution\":{\"url\":\"";
		while(lastIndex!=-1){
			lastIndex = code.indexOf(findCode);
			if(lastIndex!=-1){
				String newString = code.substring(lastIndex);
				nextIndex = newString.indexOf("\",\"");
				String res=code.substring(lastIndex+findCode.length(), lastIndex+nextIndex);
				res = res.replace("\\", "");
				list.add(res);
				newString = code.substring(lastIndex+nextIndex);
				code = String.copyValueOf(newString.toCharArray());
			}
		}
		return list;
	}
	
	/*//downloads all images from list to folder "downloads/"+username
	public void downloadAllImages(ArrayList<String> urls, String username) throws IOException{
		String newFolder = "downloads/"+username;
		new File(newFolder).mkdir();
		for(String s:urls){
			download(s, newFolder);
		}
	}*/
}
