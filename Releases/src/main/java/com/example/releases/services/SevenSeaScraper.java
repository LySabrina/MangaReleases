package com.example.releases.services;

import com.beust.ah.A;
import com.example.releases.model.Book;
import com.example.releases.model.Type;
import com.example.releases.respository.BookRepository;
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
    private static List<Book> bookCollection = new ArrayList<>();
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
    public static void seriesScrape(String url) throws IOException {
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

        // <!------- GETS ALL THE VOLUMES NAME IN THE SERIES, RELEASE DATE, PRICE, FORMAT ------->
        Elements volumes = doc.select(".series-volume");
        for(Element volume : volumes){

            String volumeName = volume.selectFirst("h3").child(1).ownText();
            Element image = volume.child(0).child(0);
            String imageURL = image.attr("src");
            //volume.ownText().substring(2) ==> because it has ":  " as some start and need to get rid of it
            //VolumeInfo contains info such as price, format, ISBN, releaseDate
            List<String> dirtyVolumeInfo = Arrays.asList(volume.ownText().substring(2).split(" "));
            ArrayList<String> volumeInfo = new ArrayList<>(dirtyVolumeInfo);
            if(volumeInfo.contains("Light")){
                volumeInfo.remove("Light");
            }
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate releaseDate = LocalDate.parse(volumeInfo.get(0), dateFormatter);
            double price;
            String format;
            String ISBN;

            //light novel is having issue with spacing
            System.out.println("VOLUME : " + volumeName);
            for(int i =0 ;i < volumeInfo.size(); i++){
                System.out.print("INDEX " + i +": " +volumeInfo.get(i) + " ");
            }
            System.out.println();
            if(volumeInfo.size() > 4){
                price = Double.parseDouble(volumeInfo.get(2).substring(1));
                format = volumeInfo.get(3).toUpperCase();
                ISBN = volumeInfo.get(4);
            }
            else{
                price = Double.parseDouble(volumeInfo.get(1).substring(1));
                format = volumeInfo.get(2).toUpperCase();
                ISBN = volumeInfo.get(3);
            }
            String pathFile = "src/main/resources/static/BookCovers/" + seriesName +"/" + volumeName;
            ImageDownloader.downloadImageCovers(doc, seriesName, volumeName, imageURL);
            Book book = new Book(volumeName, seriesName, Type.valueOf(format), authors.get(0), authors.get(0), price, ISBN, releaseDate, pathFile);
            bookCollection.add(book);
        }
    }

    public static List<Book> getBooks() throws IOException{
        getAllLinks();
        for(int i = 0; i < 10; i++){
            seriesScrape(links.get(i));
        }
//        for(String url : links){
//
//        }
        return bookCollection;
    }
    public static void main(String[] args) throws IOException {
        getAllLinks();
        for(String url : links){
            seriesScrape(url);
        }
    }
}

