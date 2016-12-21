package routing;

import docs.DocsServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ConfigServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ContextBuilder contextBuilder = configureServlet();
        ServletContext context = servletContextEvent.getServletContext();
        contextBuilder.create(context);
        context.addServlet("/api", new routing.RoutingServlet());
        if((boolean) context.getAttribute(ContextAttribute.ENABLED_DOCS)) {
            context.addServlet("/docs", new DocsServlet());
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {}

    protected ContextBuilder configureServlet() {
        return new ContextBuilder();
    }

}
