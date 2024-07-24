import java.util.ArrayList;
import java.util.List;

public class ContentManager {
    private OriginServer originServer;
    private List<EdgeServer> edgeServers = new ArrayList<>();

    public ContentManager(OriginServer originServer){
        this.originServer = originServer;
    }
    public void addEdgeServer(EdgeServer server){
        edgeServers.add(server);
    }
    public void updateContent(String key, String newData){
        originServer.addContent(key, newData);
        for(EdgeServer server : edgeServers){
            server.invalidateCache(key);
        }
    }
}
