package com.testing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraper{

    //Connect to wikipedia, receive list of countries and their population
    void populateCountries() throws Exception{
        final Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_countries_by_population_(United_Nations)").get();
        Elements table = doc.select(".wikitable");
        Elements rows = table.select("tr");
        for(int i = 1; i < rows.size(); i++){
            Element row = rows.get(i);
            Elements cols = row.select("td");
            System.out.println(cols.get(1).text() + " " + cols.get(5).text());
        }
    }
}
