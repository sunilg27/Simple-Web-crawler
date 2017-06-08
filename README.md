
> This is a *Simple Web crawler* written in **Java**.

# Agenda

The crawler to be limited to one domain. Given a starting URL – say http://example.com - it should visit all pages within the domain, but not follow the links to external sites such as Google or Twitter.

The output to be a simple structured site map, showing links to other pages under the same domain.


# Requirements

* *System installation* : Java 1.5+ & Maven 3.3.9 
* *Works on* : 	Linux, Windows, Mac OSX, BSD
* *Maven Dependencies*: JUnit 4.12, Jsoup 1.12.1 and sitemapgen4j 1.0.1

# Usage

This is a maven project with JUnit integration. Clone or Download this repository into local computer. 

Site that need to be crawled can be specified in *Simple-Web-crawler/resources/config.properties* file with `website` property.

#### Build #### : 
`mvn clean install` in the base directory(Simple-Web-crawler) where *pom.xml* is placed. 

After running the maven build it would have crawled the website specified in the config properties. 
Please do check the logs for more information.

# Design decisions

- [x]	Designed simple maven project so that dependencies can be handled by pom.xml
- [x]	BFS algorithm is considered to navigate through links when crawling operation is done.
- [x]	JSoup is considered to Parse/crawl the entire html page from the given url. 
- [x]	Model folder is designed to hold WorkerQueue & VisitedSubdomainsRepository.
- [x]	WorkerQueue holds the all links in the crawling url (In future, multi-threading is considered then it can be enhanced)
- [x]	VisitedSubDomainsRepository is a unique set contains the urls to check if url is already visited. (As of now, In-memory collection and it should be stored into a Database so that even if application crashes it will pickup from where it left since already visited links information is available)
- [x]	output is sitemap XML and interface is provided so that in future if other implementations (like HTML, PDF) are needed.
- [x]	sitemap xml is generated using the sitemapgen4j. This is again flexible interms of adding additional properties.

  
# Scope & Further improvement

Following are not part of the present implementation and can be improved upon to make this application better.
	
- [ ]	Validation.
- [ ]	Notification Listener (instead of waiting for the crawling operation, it can just send email when its done). 
- [ ] Multi threading.
- [ ]	Storing into DB instead of in-memory collection.	
- [ ] Provide VIEW layer (jsp page) to take input(website-name) from user.
- [ ]	Distributed web crawling can be other option: where we can have Crawlers, Task Queue, DB & file system to distribute among multiple systems and work on different sites in parellel. 

# External libraries used

	1. jsoup (https://jsoup.org/download)
	2. sitemapgen4j (https://mvnrepository.com/artifact/com.google.code/sitemapgen4j/1.0.1)
