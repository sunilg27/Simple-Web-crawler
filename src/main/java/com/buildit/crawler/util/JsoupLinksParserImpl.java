package com.buildit.crawler.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.buildit.crawler.model.WorkerQueue;

/**
 * Implementation of LinksParser by using JSoup library
 * 
 * @author sgollapinni
 */
public class JsoupLinksParserImpl implements LinksParser {

	public WorkerQueue parseToQueue(String url, String website, WorkerQueue queue) throws IOException {

		Document doc = Jsoup.connect(url).get();
        Elements links = doc.select("a[href]");
        Elements imports = doc.select("link[href]");
        
        for (Element link : imports) {
        	String linkStr = link.attr("abs:href");
        	if(isSameDomain(website, linkStr)) {
        		queue.add(linkStr);
        	}
        }

        for (Element link : links) {
        	String linkStr = link.attr("abs:href");
        	if(isSameDomain(website, linkStr)) {
        		queue.add(linkStr);
        	}
        }
        
        /*
         * SCOPE: Not crawling images
         * Limiting the scope to links
         *
	        Elements media = doc.select("[src]");
	        for (Element src : media) {
	            if (src.tagName().equals("img"))
	                queue.add(src.attr("abs:src"));
	        }
        */
        
		return queue;
		
	}

	private boolean isSameDomain(String website, String link) {
		return getDomainName(link).contains(getDomainName(website));
	}
	
	public static String getDomainName(String url) {
	    URI uri;
		try {
			uri = new URI(url);
			if(uri != null) {
				String domain = uri.getHost();
				if(domain != null) {
					return domain.startsWith("www.") ? domain.substring(4) : domain;
				}
			}
		} catch (URISyntaxException e) {
			//e.printStackTrace();
		}
	    return "";
	}

	private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
