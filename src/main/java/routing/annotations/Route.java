package routing.annotations;

import http.HttpMethod;

public @interface Route {
    String path();
    HttpMethod[] methods();
    String[] accept() default "";
}