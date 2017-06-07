package com.buildit.crawler.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.Properties;

import com.buildit.crawler.model.VisitedSubdomainsRepository;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

/**
 * Implementation of SitemapGenerator by XML tags
 * 
 * @author sgollapinni
 */
public class XMLSitemapGeneratorImpl implements SitemapGenerator {

	private String fileDir = "C:\\sitemap";
	
    public void loadProperties() {
	    Properties prop = new Properties();
		InputStream input = null;
	
		try {
			input = new FileInputStream("resources\\config.properties");
			
			// load a properties file
			prop.load(input);
			this.fileDir = prop.getProperty("file.path");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    }

	
	public void create(String website, VisitedSubdomainsRepository visitedSubdomainsRepository) {
		
		try {
			loadProperties();
			WebSitemapGenerator sitemapGenerator = WebSitemapGenerator.builder(website, new File(this.fileDir)).build();

			Iterator<String> itr = visitedSubdomainsRepository.getCollection().iterator();
			while (itr.hasNext()) {
				String url = (String) itr.next();
				WebSitemapUrl sitemapUrl = new WebSitemapUrl(url);
				sitemapGenerator.addUrl(sitemapUrl);
			}
			sitemapGenerator.write();
			System.out.println("Generated the sitemap XML file :-) here : " + this.fileDir);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
