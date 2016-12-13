package context;

import authentication.Guard;
import routing.RouteRegister;

import static context.ContextAttribute.*;

import javax.servlet.ServletContext;
import java.util.HashSet;
import java.util.Set;

public class ContextBuilder {

    InjectorAdapter injector;
    Set<Guard> guards;
    RouteRegister routeRegister;

    public ContextBuilder() {
        this.guards = new HashSet<Guard>();
        this.routeRegister = new RouteRegister();
    }

    public ContextBuilder useDependencyInjector(InjectorAdapter injector) {
        if(injector == null) {
            throw new IllegalArgumentException("A dependency injector adapter can't be null.");
        }
        this.injector = injector;
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

    void populateServletContext(ServletContext context) {
        context.setAttribute(GUARDS, guards);
        context.setAttribute(INJECTOR_ADAPTER, injector);
    }

}
