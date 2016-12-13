package context;

import context.ContextBuilder;
import static context.ContextAttribute.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConfigServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ContextBuilder contextBuilder = configureServlet();
        ServletContext context = servletContextEvent.getServletContext();
        contextBuilder.populateServletContext(context);
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        context.removeAttribute(GUARDS);
        context.removeAttribute(INJECTOR_ADAPTER);
    }

    protected ContextBuilder configureServlet() {
        return new ContextBuilder();
    }

}
