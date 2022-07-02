package com.example.Gazprom.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class Quote {
    @Id
    private String id;
    private String wiki;
    private LocalDateTime create_timestamp;
    private LocalDateTime timestamp;
    private String language;
    private String category;
    private String title;
    private List<String> auxiliary_text;

    public Quote() {
    }

    public Quote(String wiki, LocalDateTime create_timestamp, LocalDateTime timestamp, String language, String category, String title, List<String> auxiliary_text) {
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

    public LocalDateTime getCreate_timestamp() {
        return create_timestamp;
    }

    public void setCreate_timestamp(LocalDateTime create_timestamp) {
        this.create_timestamp = create_timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Quote{" +
                "wiki='" + wiki + '\'' +
                ", create_timestamp=" + create_timestamp +
                ", timestamp=" + timestamp +
                ", language='" + language + '\'' +
                ", category=" + category +
                ", title='" + title + '\'' +
                ", auxiliary_text=" + auxiliary_text +
                '}';
    }
}
