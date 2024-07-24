public class Content {
    private String data;
    private long lastModified;

    public Content(String data){
        this.data = data;
        this.lastModified = System.currentTimeMillis();
    }
    public String getData(){
        return data;
    }
    public long getLastModified(){
        return lastModified;
    }
}
