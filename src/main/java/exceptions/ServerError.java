package exceptions;

public class ServerError extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ServerError() {
        super();
    }

    public ServerError(String message) {
        super(message);
    }

    public ServerError(Throwable cause) {
        super(cause);
    }

    public ServerError(String message, Throwable cause) {
        super(message, cause);
    }

}
