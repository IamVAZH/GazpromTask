import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Wiki {

    private static ObjectMapper objectMapper = getObjectMapper();
    private static List<Quote> wikis = new ArrayList<>();

    private static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return objectMapper;
    }

    public static void parse(String src) throws JsonProcessingException {
        //JsonNode
        try (BufferedReader br = new BufferedReader(new FileReader(src))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                if (count % 2 == 1) {
                    JsonNode node = objectMapper.readTree(line);
                    Quote q = new Quote();
                    q.setWiki(node.get("wiki").asText());
                    if (node.get("auxiliary_text") != null) {
                        q.setAuxiliary_text(convertToArray((ArrayNode) node.get("auxiliary_text")));
                    }
                    q.setTitle(node.get("title").asText());
                    q.setCategory(convertToArray((ArrayNode) node.get("category")));
                    q.setLanguage(node.get("language").asText());
                    q.setCreate_timestamp(convertToDate(node.get("create_timestamp")));
                    q.setTimestamp(convertToDate(node.get("timestamp")));
                    getWikis().add(q);
                    System.out.println(q);
                }
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        return objectMapper.readTree(src);
    }

    public static List<Quote> getWikis() {
        return wikis;
    }

    public static List<String> convertToArray(ArrayNode array) {
        final List<String> result = new ArrayList<>();
        array.forEach(jsonNode -> result.add(jsonNode.asText()));
        return result;
    }

    public static LocalDateTime convertToDate(JsonNode node) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        LocalDateTime date = LocalDateTime.parse(node.asText(), inputFormatter);
        return date;
    }
}
