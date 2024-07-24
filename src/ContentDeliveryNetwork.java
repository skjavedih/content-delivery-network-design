public class ContentDeliveryNetwork {
    private OriginServer originServer;
    private LoadBalancer loadBalancer;
    private ContentManager contentManager;

    public ContentDeliveryNetwork(){
        this.originServer = new OriginServer();
        this.loadBalancer = new LoadBalancer();
        this.contentManager = new ContentManager(originServer);
    }

    public void addEdgeServer(String region){
        EdgeServer edgeServer = new EdgeServer(region, originServer);
        loadBalancer.addEdgeServer(region, edgeServer);
        contentManager.addEdgeServer(edgeServer);
    }

    public void addContent(String key, String data){
        contentManager.updateContent(key, data);
    }

    public Content getContent(String key, String userRegion){
        EdgeServer closestServer = loadBalancer.getClosestEdgeServer(userRegion);
        return closestServer.getContent(key);
    }

    public static void main(String [] args){
        ContentDeliveryNetwork cdn = new ContentDeliveryNetwork();

        // Add edge servers
        cdn.addEdgeServer("IND-East");
        cdn.addEdgeServer("IND-West");
        cdn.addEdgeServer("Europe");

        // Add content
        cdn.addContent("example.com/index.html", "<html><body>Hello, Javed!</body></html>");

        // Simulate user requests
        Content content1 = cdn.getContent("example.com/index.html", "IND-East");
        System.out.println("User in IND-East received: "+ content1.getData());

        Content content2 = cdn.getContent("example.com/index.html", "IND-West");
        System.out.println("User in IND-West received: "+ content2.getData());

        // Update content
        cdn.addContent("example.com/index.html", "<html><body>Hello, Updated Javed!</body></html>");

        // Simulate user requests after update
        Content content3 = cdn.getContent("example.com/index.html", "IND-West");
        System.out.println("User in IND-West received updated content: " + content3.getData());
    }
}
