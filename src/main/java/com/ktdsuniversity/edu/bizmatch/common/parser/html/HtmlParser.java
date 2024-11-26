package com.ktdsuniversity.edu.bizmatch.common.parser.html;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlParser {

	public static String getHtml(String url) {
		Document doc;
		try {
			doc = Jsoup.parse(new URI(url).toURL(), 3000);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
			return "";
		}
		
		String html = doc.html();
		return html; 
	}
	
	public static String getHtml(String url, String replaceText, String newText) {
		String html = getHtml(url);
		html = html.replace("#{" + replaceText + "}", newText);
		return html;
	}
	
	public static String getHtml(String url, Map<String, Object> replaceMap) {
		String html = getHtml(url);
		
		Set<Map.Entry<String, Object>> entrySet = replaceMap.entrySet();
		for (Map.Entry<String, Object> entry : entrySet) {
			html = html.replace("#{" +entry.getKey() + "}", entry.getValue().toString());
		}
				
		return html;
	}
}
