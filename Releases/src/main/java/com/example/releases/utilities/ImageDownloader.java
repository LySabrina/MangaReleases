package com.example.releases.utilities;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ImageDownloader {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36";
    private static Map<String, String> HTTP_HEADERS = new HashMap<>(){{put("Accept-Language", "*");put("Referer", "https://sevenseasentertainment.com/series-list/");}};
    private static String BASE_URL = "https://sevenseasentertainment.com/series-list/";
    public static void downloadImageCovers(Document doc, String seriesName, String volumeName, String imageURL) {

        try{
            Elements seriesVolume = doc.select(".series-volume");

            //Making a directory with the series name
            File parentDir = new File("./src/main/resources/static/BookCovers/");
            File childDir = new File(parentDir, seriesName);
            if(!childDir.exists()){
                boolean created = childDir.mkdir(); //if the directory failed to be created throw an exception
                if(!created){
                    throw new Exception();
                }
            }
            File f = new File(childDir, volumeName + ".jpg");
            if(f.createNewFile()){
                System.out.println("File created");
                Connection.Response response = Jsoup.connect(imageURL).ignoreContentType(true).execute();
                System.out.println("Currently downloading: " + f);
                FileOutputStream out = new FileOutputStream(f);
                out.write(response.bodyAsBytes());
                out.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
