package com.buildit.crawler.util;

import java.io.IOException;

import com.buildit.crawler.model.WorkerQueue;

/**
 * Interface to parse the links
 * 
 * @author sgollapinni
 */
public interface LinksParser {

	public WorkerQueue parseToQueue(String url, String website, WorkerQueue queue) throws IOException;

}
