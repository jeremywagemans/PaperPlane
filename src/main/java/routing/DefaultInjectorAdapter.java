package routing;

import exceptions.ServerError;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class DefaultInjectorAdapter implements InjectorAdapter {

    public <T> T getInstance(Class<T> type) {
        Constructor<T> constructor = null;
        try {
            constructor = type.getDeclaredConstructor();
        } catch (NoSuchMethodException ex) {
            throw new ServerError("No default constructor found in " + type.getName(), ex);
        }
        constructor.setAccessible(true);
        try {
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new ServerError("Impossible to create a new instance of " + type.getName(), ex);
        }
    }

}