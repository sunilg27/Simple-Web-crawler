package com.buildit.crawler.model;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Model to hold the worker queue with the crawled links 
 * 
 * @author sgollapinni
 */
public class WorkerQueue {

	Queue<String> queue;
	
	public WorkerQueue() {
		this.queue = new LinkedList<String>();
	}
	
	public void add(String link) {
		queue.add(link);
	}
	
	public String poll() {
		return queue.poll();
	}
	
	public void print() {
		System.out.println(queue);
	}

	public boolean hasMore() {
		return !queue.isEmpty();
	}

}
