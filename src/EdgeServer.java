import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EdgeServer {
    private String region;
    private Map<String, Content> cache = new ConcurrentHashMap<>();
    private OriginServer originServer;

    public EdgeServer(String region, OriginServer originServer){
        this.region = region;
        this.originServer = originServer;
    }

    public Content getContent(String key){
        if(cache.containsKey(key)){
            return cache.get(key);
        } else{
            Content content = originServer.getContent(key);
            if(content != null){
                cache.put(key, content);
            }
            return content;
        }
    }

    public void invalidateCache(String key){
        cache.remove(key);
    }
}
