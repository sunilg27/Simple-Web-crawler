package com.buildit.crawler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;


/**
 * Test simple crawler
 * 
 * @author sgollapinni
 */
public class SimpleCrawlerTest {

	private String website = "http://wiprodigital.com";
	
	@Test
	public void test_crawl_singleLink() throws IOException {
		loadProperties();
		SimpleCrawler p = new SimpleCrawler(this.website);
		p.crawl();	
	}
	
	
	
    public void loadProperties() {
	    Properties prop = new Properties();
		InputStream input = null;
	
		try {
			input = new FileInputStream("resources\\config.properties");
			
			// load a properties file
			prop.load(input);
			this.website = prop.getProperty("website");
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
	
}
