package com.example.Gazprom.DAO;

import java.time.LocalDateTime;
import java.util.List;

public class QuoteDAO {

    private String wiki;
    private long create_timestamp;
    private long timestamp;
    private String language;
    private String category;
    private String title;
    private List<String> auxiliary_text;

    public QuoteDAO(String wiki, long create_timestamp, long timestamp, String language, String category, String title, List<String> auxiliary_text) {
        this.wiki = wiki;
        this.create_timestamp = create_timestamp;
        this.timestamp = timestamp;
        this.language = language;
        this.category = category;
        this.title = title;
        this.auxiliary_text = auxiliary_text;
    }

    public String getWiki() {
        return wiki;
    }

    public void setWiki(String wiki) {
        this.wiki = wiki;
    }

    public long getCreate_timestamp() {
        return create_timestamp;
    }

    public void setCreate_timestamp(long create_timestamp) {
        this.create_timestamp = create_timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuxiliary_text() {
        return auxiliary_text;
    }

    public void setAuxiliary_text(List<String> auxiliary_text) {
        this.auxiliary_text = auxiliary_text;
    }
}
