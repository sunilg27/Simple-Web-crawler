package com.buildit.crawler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.buildit.crawler.model.VisitedSubdomainsRepository;
import com.buildit.crawler.model.WorkerQueue;
import com.buildit.crawler.output.SitemapGenerator;
import com.buildit.crawler.output.XMLSitemapGeneratorImpl;
import com.buildit.crawler.util.JsoupLinksParserImpl;
import com.buildit.crawler.util.LinksParser;

/**
 * Base simple crawler
 * 
 * @author sgollapinni
 */
public class SimpleCrawler {

	final int MAX_NUM_OF_SUBDOMAINS_TO_BE_PROCESSED = 5; // just to avoid running forever

	// composition
	private String website;
	private WorkerQueue queue;
	private LinksParser parser; // interface to parse links
	private VisitedSubdomainsRepository visitedSubdomainsRepository;
	
	public SimpleCrawler(String website) {
		this.website = website;
		this.queue = new WorkerQueue();
		this.parser = new JsoupLinksParserImpl();
		this.visitedSubdomainsRepository = new VisitedSubdomainsRepository(MAX_NUM_OF_SUBDOMAINS_TO_BE_PROCESSED);
	}
	
    public void crawl() {
    	System.out.println("Started crawling...");
    	int currentCounter = 0;
    	queue.add(website);

    	// BFS implementation
    	while(currentCounter < MAX_NUM_OF_SUBDOMAINS_TO_BE_PROCESSED && queue.hasMore()) {
    		
    		// poll from queue 
    		String currentUrl = queue.poll();
    		if(visitedSubdomainsRepository.isNotVisitedUrl(currentUrl)) {
    			
    			// add it to the visited sub-domain
    			visitedSubdomainsRepository.add(currentUrl); 
    			
    			try {
					queue = parser.parseToQueue(currentUrl, website, queue);
				} catch (IOException e) {
					System.out.println("IO Exception while processing currentLink : " + currentUrl);
					// e.printStackTrace();
				}
    			currentCounter++;
    		} else {
    			System.out.println("already visited link : " + currentUrl);
    		}
    	}
    	
    	SitemapGenerator generator = new XMLSitemapGeneratorImpl(); // Sitemap XML generation
    	generator.create(website, visitedSubdomainsRepository);
    	
    	System.out.println("Done...");
    	//visitedSubdomainsRepository.print();
    }
    
}
