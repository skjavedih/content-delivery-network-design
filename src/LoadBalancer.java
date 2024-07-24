import java.util.HashMap;
import java.util.Map;

public class LoadBalancer {
    private Map<String, EdgeServer> edgeServers = new HashMap<>();

    public void addEdgeServer(String region, EdgeServer server){
        edgeServers.put(region, server);
    }
    public EdgeServer getClosestEdgeServer(String userRegion){
        // In a real implementation, this would use geolocation and networks
        return edgeServers.getOrDefault(userRegion, edgeServers.values().iterator().next());
    }
}
