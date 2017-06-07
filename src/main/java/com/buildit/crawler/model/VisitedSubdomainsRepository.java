package com.buildit.crawler.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Model to hold the visited urls
 * 
 * @author sgollapinni
 */
public class VisitedSubdomainsRepository {

	private Set<String> visitedSet;
	private Map<String, WorkerQueue> repoMap;
	
	public VisitedSubdomainsRepository(int maxSize) {
		visitedSet = new HashSet<String>(maxSize);
		repoMap = new HashMap<String, WorkerQueue>();
	}

	public void add(String url) {
		visitedSet.add(url);
	}
	
	public void add(String url, WorkerQueue queue) {
		visitedSet.add(url);
		repoMap.put(url, queue);
	}
	
	public boolean isNotVisitedUrl(String url) {
		if(visitedSet.contains(url) || visitedSet.contains(trimHashReferences(url))) {
			return false; // then it is visited and return false
		}
		return true;
	}
	
	public Set<String> getCollection() {
		return visitedSet;
	}
	
	public Map<String, WorkerQueue> getFullCollection() {
		return repoMap;
	}

	public void print() {
		System.out.println(visitedSet);
	}
	
	private String trimHashReferences(String url) {
		int endPos;
	    if (url.indexOf("?") > 0) {
	        endPos = url.indexOf("?");
	    } else if (url.indexOf("#") > 0) {
	        endPos = url.indexOf("#");
	    } else {
	        endPos = url.length();
	    }

	    return url.substring(0, endPos);
	}

}
