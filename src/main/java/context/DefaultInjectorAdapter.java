package context;

import exceptions.FatalException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DefaultInjectorAdapter implements InjectorAdapter {

    public <T> T getInstance(Class<T> type) {
        Constructor<T> constructor = null;
        try {
            constructor = type.getDeclaredConstructor();
        } catch (NoSuchMethodException ex) {
            throw new FatalException("No default constructor found in " + type.getName(), ex);
        }
        constructor.setAccessible(true);
        try {
            return constructor.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new FatalException("Impossible to create a new instance of " + type.getName(), ex);
        }
    }

}
