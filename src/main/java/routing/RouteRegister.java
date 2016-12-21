package routing;

import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RouteRegister {

    InjectorAdapter injector;
    Map<String, Resource> resources;

    public RouteRegister(InjectorAdapter injector) {
        this.injector = injector;
        this.resources = new HashMap<String, Resource>();
    }

    public void register(String pckage) {
        final Reflections reflections = new Reflections(pckage);
        final Set<Class<?>> resourceClasses = reflections.getTypesAnnotatedWith(routing.annotations.Resource.class);
        for(Class<?> resourceClass : resourceClasses) {
            register(resourceClass);
        }
    }

    public void register(Class<?> cl) {
        Object instance = injector.getInstance(cl);
        register(instance);
    }

    public void register(Object instance) {
        Resource resource = fetchResource(instance);
        resources.put(resource.getEndpoint(), resource);
    }

    private Resource fetchResource(Object instance) {
        Class<?> implClass = instance.getClass();
        routing.annotations.Resource resourceAnnotation =
                implClass.getDeclaredAnnotation(routing.annotations.Resource.class);
        Resource resource = new Resource(resourceAnnotation.name(), resourceAnnotation.endpoint(),
                resourceAnnotation.description(), instance);
        for(Method method : implClass.getDeclaredMethods()) {
            if(!method.isAnnotationPresent(routing.annotations.SubResource.class)) {
                continue;
            }
            SubResource subResource = fetchSubResource(method, resource);
            resource.addSubResource(subResource);
        }
        return resource;
    }

    private SubResource fetchSubResource(Method implMethod, Resource parentResource) {
        routing.annotations.SubResource subResourceAnnotation =
                implMethod.getDeclaredAnnotation(routing.annotations.SubResource.class);
        PathTemplate pathTemplate = new PathTemplate(subResourceAnnotation.path());
        SubResource subResource = new SubResource(subResourceAnnotation.method(), pathTemplate, implMethod,
                subResourceAnnotation.description(), parentResource);
        return subResource;
    }

    public Map<String, Resource> retrieveRoutes() {
        return this.resources;
    }

}
