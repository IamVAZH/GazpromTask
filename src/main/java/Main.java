import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        Wiki.parse("/Users/valerijzarkov/GazpromTask/src/main/resources/wiki.json");

        MongoClient client = MongoClients.create("");

    }
}
