package com.buildit.crawler.output;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Iterator;

import com.buildit.crawler.model.VisitedSubdomainsRepository;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

/**
 * Implementation of SitemapGenerator by XML tags
 * 
 * @author sgollapinni
 */
public class XMLSitemapGeneratorImpl implements SitemapGenerator {

	public void create(String website, VisitedSubdomainsRepository visitedSubdomainsRepository) {
		
		try {
			WebSitemapGenerator sitemapGenerator = WebSitemapGenerator.builder(website, new File("C:\\sitemap")).build();

			Iterator<String> itr = visitedSubdomainsRepository.getCollection().iterator();
			while (itr.hasNext()) {
				String url = (String) itr.next();
				WebSitemapUrl sitemapUrl = new WebSitemapUrl(url);
				sitemapGenerator.addUrl(sitemapUrl);
			}
			sitemapGenerator.write();
			System.out.println("Generated the sitemap XML file :-)");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
