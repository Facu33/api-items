package com.mercadolibre.itacademy;

public class Item {

    private String username;
    private String site;
    private String categorie;
    private String name;

    public Item() {
    }

    public Item(String user, String site, String categorie, String name) {
        this.username = username;
        this.site = site;
        this.categorie = categorie;
        this.name = name;
    }

    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

