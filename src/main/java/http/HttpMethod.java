package http;

public enum HttpMethod {
    GET("GET"),
    HEAD("HEAD"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    TRACE("TRACE"),
    OPTIONS("OPTIONS"),
    CONNECT("CONNECT"),
    PATCH("PATCH");

    private String name;

    private HttpMethod(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
