package routing;

public interface InjectorAdapter {
    <T> T getInstance(Class<T> type);
}