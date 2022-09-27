package com.gigawattstechnology.e_lib;

public class LinkModal {
    String Title,url;

    public LinkModal(String title, String url) {
        Title = title;
        this.url = url;
    }

    public String getTitle() {
        return Title;
    }

    public String getUrl() {
        return url;
    }
}
