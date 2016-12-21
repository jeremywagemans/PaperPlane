package routing;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import java.util.Map;

import static routing.ContextAttribute.*;

public class RoutingServlet extends HttpServlet {

    Map<String, Resource> routes;

    public RoutingServlet() {
        ServletContext context = getServletContext();
        routes = (Map<String, Resource>) context.getAttribute(ROUTES);
    }

}
