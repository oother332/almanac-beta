package com.testing;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.ExponentialReconnectionPolicy;

public class CassandraConnector {

    private Cluster cluster;
    private Session session;

    //Initialize connection to cluster and session
    public void connect(String node, Integer port){
        Cluster.Builder b = Cluster.builder().addContactPoint(node).withReconnectionPolicy(new ExponentialReconnectionPolicy(1000,30000));
        if(port != null){
            b.withPort(port);
        }
        cluster = b.build();
        session = cluster.connect();
    }
    public Session getSession(){
        return this.session;
    }


    public void checkWorking(){
        ResultSet rs = session.execute("select release_version from system.local");
        Row row = rs.one();
        System.out.println(row.getString("release_version"));
    }

    public void close(){
        session.close();
        cluster.close();
    }
    void dropEverything(){
        session.execute("DROP KEYSPACE almanac;");
    }

    public void createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor){
        StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ").append(keyspaceName).append(" WITH replication = {").append("'class':'").append(replicationStrategy).append("','replication_factor':").append(replicationFactor).append("};");

        String query = sb.toString();
        session.execute(query);
    }
    public void useKeyspace(String keyspaceName){
        session.execute("USE " + keyspaceName + " ;");
    }

    public void createTable(String tableName){
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (country text PRIMARY KEY, population text);");//.append(columns).append(");");
        String query = sb.toString();
        System.out.println(query);
        session.execute(query);

    }
    public void insertIntoPopulation(String countryName, String pop){
        StringBuilder sb = new StringBuilder("INSERT INTO populationTable (country, population) VALUES ('").append(countryName).append("', '").append(pop).append("');");
        String query = sb.toString();
        System.out.println(query);
        session.execute(query);
    }

}
