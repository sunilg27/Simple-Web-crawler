
This is a Simple Web crawler written in Java.

# Agenda

The crawler to be limited to one domain. Given a starting URL – say http://example.com - it should visit all pages within the domain, but not follow the links to external sites such as Google or Twitter.

The output to be a simple structured site map, showing links to other pages under the same domain.

# Usage

This is a maven project with JUnit integration. Clone or Download this repository into local computer. 

Site that need to be crawled can be specified in Simple-Web-crawler/resources/config.properties file for 'website' property.

Build:
	- 'mvn clean install' in the base directory where pom.xml is placed. 
	- using eclipse, you can do build or maven build/install goals.



# Design decisions

-	Designed simple maven project so that dependencies can be handled by pom.xml
-	BFS algorithm is considered to navigate through links when crawling operation is done.
- 	JSoup is considered to Parse/crawl the entire html page from the given url. 
-	Model folder is designed to hold WorkerQueue & VisitedSubdomainsRepository.
-	WorkerQueue holds the all links in the crawling url (In future, multi-threading is considered then it can be enhanced)
-	VisitedSubDomainsRepository is a unique set contains the urls to check if url is already visited. (As of now, In-memory collection and it should be stored into a Database so that even if application crashes it will pickup from where it left since already visited links information is available)
- 	output is sitemap XML and interface is provided so that in future if other implementations (like HTML, PDF) are needed.
-	sitemap xml is generated using the sitemapgen4j. This is again flexible interms of adding additional properties.

  
# Scope & Further improvement

Following can be implemented to make this application more efficient:
	- Validation.
	- Notification Listener (instead of waiting for the crawling operation, it can just send email when its done). 
	- Multi threading.
	- Support multiple sites crawling by multi-threading & storing into DB.
	- Provide VIEW layer (jsp page) to take input(website-name) from user.

# External libraries used

	- jsoup
	- sitemapgen4j

