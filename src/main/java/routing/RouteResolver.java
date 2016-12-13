package routing;

import http.HttpMethod;
import routing.exceptions.RouteNotFoundException;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class RouteResolver {

    private SortedSet<Route> routes;

    public RouteResolver() {
        this.routes = new TreeSet<Route>();
    }

    /**
     * Looks for a route matching both an HTTP method and a path.
     *
     * @param httpMethod the HTTP method with which the request was made.
     * @param path the URL the client sent when it made the request
     * @return the required Route instance
     * @throws RouteNotFoundException if no route matching the request has been found
     */
    public Route findRoute(HttpMethod httpMethod, String path) {
        for (Route route : routes) {
            if (route.getHttpMethod() != httpMethod) {
                continue;
            }
            if (route.getPathTemplate().matches(path)) {
                return route;
            }
        }
        throw new RouteNotFoundException();
    }

    public Set<HttpMethod> findAllowedMethod(String path) {
        Set<HttpMethod> methods = new HashSet<HttpMethod>();
        for (Route route : routes) {
            if (route.getPathTemplate().matches(path)) {
                methods.add(route.getHttpMethod());
            }
        }
        return methods;
    }

    /**
     * Builds a character string representation of all routes initialized. Mainly used for debugging.
     *
     * @return a String
     */
    String dumpRoutes() {
        StringBuilder str = new StringBuilder();
        for (Route route : routes) {
            if (str.length() > 0) {
                str.append("\n");
            }
            str.append("\t" + route);
        }
        return str.toString();
    }

}
