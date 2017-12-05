package com.testing;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;


public class Main {
    public static void main(String[] args) throws Exception{
        String node = "127.0.0.1";
        int port = 9042;
        String[][] tableBuffer;


        String keyspaceName = "Almanac";
        CassandraConnector db = new CassandraConnector();
        db.connect(node,port);
        db.dropEverything();
        db.createKeyspace(keyspaceName,"SimpleStrategy",1);
        db.checkWorking();
        db.useKeyspace(keyspaceName);
        db.createTable("populationTable");
        Scraper scrape = new Scraper();
        tableBuffer = scrape.populateCountries();
        for(int i = 0; i < tableBuffer.length; i++){
            db.insertIntoPopulation(tableBuffer[i][0], tableBuffer[i][1]);
        }

        db.close();

     }
}
