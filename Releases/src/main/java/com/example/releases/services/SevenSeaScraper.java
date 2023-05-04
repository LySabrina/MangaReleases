package com.example.releases.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class SevenSeaScraper {
    private static Set<String> bookCollection = new HashSet<String>();
    private static ArrayList<String> links = new ArrayList<>();
    private int PAGES_LIMIT = 10;
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
    private static Map<String, String> HTTP_HEADERS = new HashMap<>(){{put("Accept-Language", "*");put("Referer", "https://sevenseasentertainment.com/series-list/");}};
    private static String BASE_URL = "https://sevenseasentertainment.com/series-list/";

    /**
     * Gets all the links of books information
     * @throws IOException
     */
    public static void getAllLinks() throws IOException {
        Document doc = Jsoup.connect(BASE_URL).userAgent(USER_AGENT).headers(HTTP_HEADERS).get();
        Elements bookLinks = doc.select("#volumes");
        for(Element e: bookLinks){
            String linkURL= e.child(0).child(0).attributes().get("href");
            links.add(linkURL);
        }
    }

    /**
     * From the gathered links to the book information page, Get the folowing information
     * 1) Book Format (ex. manga, light novel) 2) Release date 3) Author and artist 4) Genres
     * Possible Case: Story and art is by the same person or two different people
     * @param url
     * @throws IOException
     */
    private static void seriesScrape(String url) throws IOException {
        //FUTURE POSSIBLE IMPROVEMENTS = THREADS
//        ExecutorService service = Executors.newFixedThreadPool(10); //create 10 threads ready to scrape
        //wait for around 1 second before sending another request


        // GETS THE AUTHOR AND GENRES
        Document doc = Jsoup.connect(url).userAgent(USER_AGENT).headers(HTTP_HEADERS).get();
//        Element bookContainer = doc.getElementById("series-meta");
//        Element copyRight = bookContainer.getElementById("copyright");
//        Elements bookInfo = copyRight.nextElementSiblings().select("a");
//        List<String> genres = new ArrayList<>();
//        List<String> authors = new ArrayList<>();
//        for(Element a : bookInfo){
//            String href = a.attr("href");
//            int start = href.indexOf(".com/") + 5;
//            int end = href.indexOf("/", start);
//            String type = href.substring(start, end);
//
//            if(type.equals("tag")){
//                genres.add(a.text());
//            }
//            else{
//                authors.add(a.text());
//            }
//        }
//        System.out.println("AUTHOR & ARTIST: " + authors.toString());
//        System.out.println("GENRE: " + genres.toString());

        //GETS ALL THE VOLUMES IN THE SERIES, RELEASE DATE, PRICE, FORMAT
        //TODO: Parse the string to get the release date, price, format and ISBN
        Elements volumes = doc.select(".series-volume");
        for(Element volume : volumes){
            Element volumeName = volume.select("h3").first();
            String s = volume.ownText().substring(2); //substring(2) because it has ":  " as some starter
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws IOException {
        getAllLinks();
        for(int i = 0; i < 3; i++){
            String s = links.get(i);
            seriesScrape(s);
        }
    }
}
