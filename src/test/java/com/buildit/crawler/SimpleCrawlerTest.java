package com.buildit.crawler;

import java.io.IOException;

import org.junit.Test;


/**
 * Test simple crawler
 * 
 * @author sgollapinni
 */
public class SimpleCrawlerTest {

	@Test
	public void test_crawl_singleLink() throws IOException {
		String website = "http://wiprodigital.com";
		SimpleCrawler p = new SimpleCrawler(website);
		p.crawl();	
	}
	
}
