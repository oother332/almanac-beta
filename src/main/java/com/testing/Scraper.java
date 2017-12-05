package com.testing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;


public class Scraper{

    //Connect to wikipedia, receive list of countries and their population
    public String[][] populateCountries() throws Exception{
        String[][] tableBuffer;
        CassandraConnector con = new CassandraConnector();
        final Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/List_of_countries_by_population_(United_Nations)").get();
        Elements table = doc.select(".wikitable");
        Elements rows = table.select("tr");
        tableBuffer = new String[rows.size()-1][2];
        for(int i = 2; i < rows.size(); i++){
            Element row = rows.get(i);
            Elements cols = row.select("td");
            System.out.println(cols.get(1).text() + " " + cols.get(5).text());
            tableBuffer[i-2][0] = cols.get(1).text();
            tableBuffer[i-2][1] = cols.get(5).text();
        }

        return tableBuffer;

    }
}
