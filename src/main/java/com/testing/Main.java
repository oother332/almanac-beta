package com.testing;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;


public class Main {
    public static void main(String[] args) throws Exception{
/*      String node = "127.0.0.1";
        int port = 9042;*/


        Scraper scrape = new Scraper();
        scrape.populateCountries();

/*
        String keyspaceName = "Database";
        CassandraConnector db = new CassandraConnector();
        db.connect(node,port);
        db.createKeyspace(keyspaceName,"SimpleStrategy",1);
        db.checkWorking();

        db.close();
*/
     }
}
