package com.mercadolibre.itacademy;

import com.google.gson.Gson;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class UserService {

    /*public String doLogin (User user) {
        try {
            Gson gson = new Gson();
            BufferedReader in = createUrl("https://localhost:8081/lopgin");
            gson.fromJson(in,User.class);
            Site[] sites = gson.fromJson(in,Site[].class);
            for(int i = 0; i<sites.length; i++){
                System.out.println(sites[i].toString());
            }
            BufferedReader inC = createUrl("https://api.mercadolibre.com/sites/"+id+"/categories");
            Categorie[] categories = gson.fromJson(inC,Categorie[].class);
            for(int i = 0; i<categories.length; i++){
                System.out.println(categories[i].toString());
            }

        } catch (MalformedURLException excetion) {
            System.out.println(excetion.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static BufferedReader createUrl(String strUrl) throws MalformedURLException, IOException {

        URL url = new URL(strUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
        if (urlConnection instanceof HttpURLConnection) {
            HttpURLConnection connection = (HttpURLConnection) urlConnection;

            OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
            writer.write(data);
            writer.flush();
            String line;
            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            writer.close();
            reader.close();




            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return in;
        } else {
            System.out.println("URL invalida");
            return null;
        }
    }
    */

    public String sendPost(String url) throws Exception {

        URL urlConnection = new URL(url);
        HttpsURLConnection connection = (HttpsURLConnection) urlConnection.openConnection();

        //add reuqest header
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json; utf-8");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        String jsonInputString = "{name:facu,password:1234}";

        // Send post request
        connection.setDoOutput(true);
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        }

    }

}




