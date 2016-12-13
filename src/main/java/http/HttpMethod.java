package http;

public enum HttpMethod {
    GET("GET", 1),
    HEAD("HEAD", 2),
    POST("POST", 3),
    PUT("PUT", 4),
    DELETE("DELETE", 5),
    TRACE("TRACE", 6),
    OPTIONS("OPTIONS", 7),
    CONNECT("CONNECT", 8),
    PATCH("PATCH", 9);

    private String name;
    private int weight;

    private HttpMethod(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
