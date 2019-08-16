package com.mercadolibre.itacademy;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.io.*;
import java.net.*;

public class UserService {

    public String get(String strUrl,String username,String token) throws Exception {

        StringBuilder stringBuilder = new StringBuilder(strUrl);
        stringBuilder.append("?username=");
        stringBuilder.append(URLEncoder.encode(username, "UTF-8"));
        stringBuilder.append("&token=");
        stringBuilder.append(URLEncoder.encode(token, "UTF-8"));

        URL url = new URL(stringBuilder.toString());

        URLConnection con = url.openConnection();
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        if (con instanceof HttpURLConnection) {
            HttpURLConnection connection = (HttpURLConnection) con;
            System.out.println("\nSending request to URL : " + url);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            return response.toString();
        } else {
            System.out.println("URL invalida");
            return null;
        }
    }


    public static String postLogin(String urlGet, User user) {
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
}




