package routing.annotations;

import http.HttpMethod;

public @interface SubResource {
    String path();
    HttpMethod method();
    String[] accept() default "";
    String description() default "";
}