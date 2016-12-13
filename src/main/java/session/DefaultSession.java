package session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DefaultSession implements Session {

    private HttpSession session;

    public DefaultSession(HttpServletRequest req) {
        this.session = req.getSession();
    }

    public Object getAttribute(String key) {
        return session.getAttribute(key);
    }

    public void setAttribute(String key, Object value) {
        session.setAttribute(key, value);
    }

}
