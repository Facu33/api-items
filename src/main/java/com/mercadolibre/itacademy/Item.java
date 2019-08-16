package com.mercadolibre.itacademy;

public class Item {

    private String username;
    private String site;
    private String category;
    private String name;

    public Item() {
    }

    public Item(String username, String site, String category, String name) {
        this.username = username;
        this.site = site;
        this.category = category;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

