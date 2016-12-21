package routing;

import http.HttpMethod;

import java.lang.reflect.Method;

public class SubResource {
    private HttpMethod method;
    private PathTemplate pathTemplate;
    private Method implMethod;
    private String description;
    private Resource parentResource;

    public SubResource(HttpMethod method, PathTemplate pathTemplate, Method implMethod, String description,
                       Resource parentResource) {
        this.method = method;
        this.pathTemplate = pathTemplate;
        this.implMethod = implMethod;
        this.description = description;
        this.parentResource = parentResource;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public PathTemplate getPathTemplate() {
        return pathTemplate;
    }

    public void setPathTemplate(PathTemplate pathTemplate) {
        this.pathTemplate = pathTemplate;
    }

    public Method getImplMethod() {
        return implMethod;
    }

    public void setImplMethod(Method implMethod) {
        this.implMethod = implMethod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        final String implClassName = parentResource.getImplInstance().getClass().getName();
        final String implMethodName = method.name();
        return "[" + method + "] " + pathTemplate + " --> " + implClassName + "." + implMethodName;
    }

}