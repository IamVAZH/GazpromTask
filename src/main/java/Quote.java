import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Quote {
    private String wiki;
    private LocalDateTime create_timestamp;
    private LocalDateTime timestamp;
    private String language;
    private List<String> category;
    private String title;
    private List<String> auxiliary_text;

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

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
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
