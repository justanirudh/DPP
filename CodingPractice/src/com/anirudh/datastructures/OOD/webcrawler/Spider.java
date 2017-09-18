package com.anirudh.datastructures.OOD.webcrawler;

/**
 * Created by paanir on 9/17/17.
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//http://www.netinstructions.com/how-to-make-a-simple-web-crawler-in-java/
public class Spider {
    private static final int MAX_PAGES_TO_SEARCH = 10;
    private Set<String> pagesVisited = new HashSet<>();
    private List<String> pagesToVisit = new LinkedList<>();


    /**
     * Our main launching point for the Spider's functionality. Internally it creates spider legs
     * that make an HTTP request and parse the response (the web page).
     *
     * @param url        - The starting point of the spider
     * @param searchWord - The word or string that you are searching for
     */
    public void search(String url, String searchWord) {
        while (pagesVisited.size() < MAX_PAGES_TO_SEARCH) {
            String currentUrl;
            SpiderLeg leg = new SpiderLeg();
            if (pagesToVisit.isEmpty()) {
                currentUrl = url;
                pagesVisited.add(url);
            } else {
                currentUrl = nextUrl();
            }
            leg.crawl(currentUrl); // Lots of stuff happening here. Look at the crawl method in SpiderLeg
            boolean success = leg.searchForWord(searchWord);
            if (success) {
                System.out.println(String.format("**Success** Word %s found at %s", searchWord, currentUrl));
                break;
            }
            pagesToVisit.addAll(leg.getLinks());
        }
        System.out.println("\n**Done** Visited " + pagesVisited.size() + " web page(s)");
    }


    /**
     * Returns the next URL to visit (in the order that they were found). We also do a check to make
     * sure this method doesn't return a URL that has already been visited.
     *
     * @return
     */
    private String nextUrl() {
        String nextUrl;
        do {
            nextUrl = pagesToVisit.remove(0);
        } while (pagesVisited.contains(nextUrl));
        pagesVisited.add(nextUrl);
        return nextUrl;
    }
}


