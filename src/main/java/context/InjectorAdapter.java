package context;

public interface InjectorAdapter {
    <T> T getInstance(Class<T> type);
}