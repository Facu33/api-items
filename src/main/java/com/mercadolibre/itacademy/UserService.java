package com.mercadolibre.itacademy;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

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
}




