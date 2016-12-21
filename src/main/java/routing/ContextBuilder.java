package routing;

import authentication.Guard;

import javax.servlet.ServletContext;
import java.util.HashSet;
import java.util.Set;

public class ContextBuilder {

    String appName;
    boolean enabledDoc;
    Set<Guard> guards;
    RouteRegister routeRegister;

    public ContextBuilder(InjectorAdapter injector) {
        this.guards = new HashSet<Guard>();
        this.routeRegister = new RouteRegister(injector);
    }

    public ContextBuilder() {
        this(new DefaultInjectorAdapter());
    }

    public ContextBuilder registerAppName(String appName) {
        if(appName == null || appName.equals("")) {
            throw new IllegalArgumentException("The app name can't be null.");
        }
        this.appName = appName;
        return this;
    }

    public ContextBuilder enableDocs(Boolean enabled) {
        this.enabledDoc = enabled;
        return this;
    }

    public ContextBuilder addGuard(Guard guard) {
        if(guard == null) {
            throw new IllegalArgumentException("A guard can't be null.");
        }
        guards.add(guard);
        return this;
    }

    public ContextBuilder register(String pckage) {
        if(pckage == null || pckage.equals("")) {
            throw new IllegalArgumentException("A registered package name can't be null or an empty string.");
        }
        routeRegister.register(pckage);
        return this;
    }

    public ContextBuilder register(Class<?> type) {
        if(type == null) {
            throw new IllegalArgumentException("A registered type can't be null.");
        }
        routeRegister.register(type);
        return this;
    }

    public ContextBuilder register(Object instance) {
        if(instance == null) {
            throw new IllegalArgumentException("A registered instance can't be null.");
        }
        routeRegister.register(instance);
        return this;
    }

    void create(ServletContext context) {
        context.setAttribute(ContextAttribute.GUARDS, guards);
        context.setAttribute(ContextAttribute.ROUTES, routeRegister.retrieveRoutes());
    }

}
