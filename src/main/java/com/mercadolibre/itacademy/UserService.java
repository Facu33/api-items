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

    public String sendLogin(String url,User user) throws Exception {

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

    public String sendGetSites(String strUrl,String username,String token) throws Exception {

        URL url = new URL(strUrl);
        URLConnection urlConnection = url.openConnection();
        urlConnection.setRequestProperty("Accept", "application/json");
        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
        if (urlConnection instanceof HttpURLConnection) {
            HttpURLConnection connection = (HttpURLConnection) urlConnection;
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return "in";
        } else {
            System.out.println("URL invalida");
            return null;
        }
        //return "ok";
    }

}




