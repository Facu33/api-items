package com.mercadolibre.itacademy;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UserService {

    public String get(String url, User user) throws Exception {
        return "";
    }


    public static String post(String urlGet, User user) {
        try {
            URL url = new URL(urlGet);

            try {
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Accept", "application/json");
                con.setRequestMethod("POST");

                Gson gson = new Gson();
                JsonElement jsonInputString = gson.toJsonTree(user);

                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                wr.write(jsonInputString.toString());
                wr.flush();

                StringBuilder sb = new StringBuilder();
                int HttpResult = con.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(con.getInputStream(), "utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
                } else {
                    System.out.println(con.getResponseMessage());
                }
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }

        } catch (MalformedURLException exception) {
            System.out.println(exception.getMessage());
        }
        return (null);
    }

    public static Item postItems(Item item){


        RestHighLevelClient client = makeConnection();

        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("user",item.getUser());
        dataMap.put("site",item.getSite());
        dataMap.put("categorie",item.getCategorie());
        dataMap.put("name",item.getName());

        IndexRequest indexRequest = new IndexRequest("items").source(dataMap);

        try {
            IndexResponse response = client.index(indexRequest,RequestOptions.DEFAULT);
            return item;
        } catch(ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (java.io.IOException ex){
            ex.getLocalizedMessage();
        }
        try {
            closeConnection(client);
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }

    public static Collection<Item> getItems() {

        RestHighLevelClient client = makeConnection();

        SearchRequest searchRequest = new SearchRequest("items");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        try {

            SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            SearchHit[] searchHits = hits.getHits();
            List<Item> items = new ArrayList<>() ;
            for (SearchHit hit : searchHits) {
                items.add(new Gson().fromJson( hit.getSourceAsString(), Item.class));
            }

            return items;


        } catch(ElasticsearchException e) {
            System.out.println(e.getMessage());
        } catch (java.io.IOException ex){
            System.out.println(ex.getMessage());
        }

        try {
            closeConnection(client);
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }


    public static Collection<Item> getItemsxUser(String user) {

        RestHighLevelClient client = makeConnection();

        SearchRequest searchRequest = new SearchRequest("items");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("user",user));
        searchRequest.source(searchSourceBuilder);

        try {

            SearchResponse response = client.search(searchRequest,RequestOptions.DEFAULT);
            SearchHits hits = response.getHits();
            SearchHit[] searchHits = hits.getHits();
            List<Item> itemsxUser = new ArrayList<>() ;
            for (SearchHit hit : searchHits) {
                itemsxUser.add(new Gson().fromJson( hit.getSourceAsString(), Item.class));
            }

            return itemsxUser;


        } catch(ElasticsearchException e) {
            System.out.println(e.getMessage());
        } catch (java.io.IOException ex){
            System.out.println(ex.getMessage());
        }

        try {
            closeConnection(client);
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
        return null;
    }











    private static RestHighLevelClient makeConnection() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("localhost", 9201, "http")));

        return client;
    }

    private static void closeConnection(RestHighLevelClient client) throws IOException {
        client.close();
    }


}




