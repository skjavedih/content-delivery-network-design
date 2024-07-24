import java.util.HashMap;
import java.util.Map;

public class OriginServer {
    private Map<String, Content> contentStore = new HashMap<>();

    public  void addContent(String key, String data){
        contentStore.put(key, new Content(data));
    }

    public Content getContent(String key){
        return contentStore.get(key);
    }
}
