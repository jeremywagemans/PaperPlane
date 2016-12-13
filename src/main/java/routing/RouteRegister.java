package routing;

import routing.annotations.Resource;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.SortedSet;

public class RouteRegister {

    SortedSet<Route> routes;

    public void register(String pckage) {
        if(pckage == null || pckage.equals("")) {
            throw new IllegalArgumentException("Package name can't be empty.");
        }

    }

    public void register(Object instance) {

    }

    public void register(Class<?> cl) {
        analyzeClass(cl);
    }

    private void analyzeClass(Class<?> cl) {
        String endpoint = cl.getAnnotation(Resource.class).endpoint();
        for (Method method : cl.getDeclaredMethods()) {
            if (method.isAnnotationPresent(routing.annotations.Route.class)) {
                routing.annotations.Route annotRoute =
                        method.getDeclaredAnnotation(routing.annotations.Route.class);
                PathTemplate pathTemplate = new PathTemplate(endpoint + annotRoute.template());
                Object serviceInstance = null; // TODO
                Route route = new Route(annotRoute.method(), pathTemplate, service, serviceInstance,
                        method, annotRoute.contentType());
                routes.add(route);
            }
        }
    }

    Set<Route> getRoutes() {
        return routes;
    }

}
