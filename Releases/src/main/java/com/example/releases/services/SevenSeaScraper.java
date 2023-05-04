package com.example.releases.services;

import com.example.releases.model.Book;
import com.example.releases.utilities.ImageDownloader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TODO: From the information gathered, create a Book object then make sure it is put into the database
 */

/**
 * Scrapes book information from SevenSeas websites.
 * TODO FUTURE UPDATES: Possibly check if book series info is updated (ex. upcoming release of new book)
 */
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


        // <!-------- GETS THE AUTHOR AND GENRES AND SERIES NAME  -------->
        Document doc = Jsoup.connect(url).userAgent(USER_AGENT).headers(HTTP_HEADERS).get();
        Element bookContainer = doc.getElementById("series-meta");
        String seriesName = doc.selectFirst(".topper").ownText().substring(7);
        if(seriesName.contains("(")){
            int index = seriesName.indexOf("(");
            seriesName = seriesName.substring(0, index);
        }
        Element copyRight = bookContainer.getElementById("copyright");
        Elements bookInfo = copyRight.nextElementSiblings().select("a");
        List<String> genres = new ArrayList<>();
        List<String> authors = new ArrayList<>();

        for(Element a : bookInfo){
            String href = a.attr("href");
            int start = href.indexOf(".com/") + 5;
            int end = href.indexOf("/", start);
            String type = href.substring(start, end);

            if(type.equals("tag")){
                genres.add(a.text());
            }
            else{
                authors.add(a.text());
            }
        }
        System.out.println("AUTHOR & ARTIST: " + authors.toString());
        System.out.println("GENRE: " + genres.toString());

        // <!------- GETS ALL THE VOLUMES NAME IN THE SERIES, RELEASE DATE, PRICE, FORMAT ------->
        Elements volumes = doc.select(".series-volume");
        for(Element volume : volumes){
            String volumeName = volume.selectFirst("h3").child(1).ownText();
            Element image = volume.child(0).child(0);
            String imageURL = image.attr("src");
            //volume.ownText().substring(2) ==> because it has ":  " as some start and need to get rid of it
            //VolumeInfo contains info such as price, format, ISBN, releaseDate
            String[] volumeInfo = volume.ownText().substring(2).split(" ");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate releaseDate = LocalDate.parse(volumeInfo[0], dateFormatter);
            double price = Double.parseDouble(volumeInfo[1].substring(1));
            String format = volumeInfo[2];
            String ISBN = volumeInfo[3];

            ImageDownloader.downloadImageCovers(doc, seriesName, volumeName, imageURL);
            System.out.println(volumeName);


        }
    }

    public static void main(String[] args) throws IOException {
        getAllLinks();

        for(int i = 0; i < 2; i++){
            String s = links.get(i);
            seriesScrape(s);
        }

    }
}

