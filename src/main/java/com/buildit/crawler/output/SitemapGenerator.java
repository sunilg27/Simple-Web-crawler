package com.buildit.crawler.output;

import com.buildit.crawler.model.VisitedSubdomainsRepository;

/**
 * Interface to generate sitemap
 * 
 * @author sgollapinni
 */
public interface SitemapGenerator {

	void create(String website, VisitedSubdomainsRepository visitedSubdomainsRepository);

}
