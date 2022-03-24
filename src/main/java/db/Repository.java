package db;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public interface Repository<T> {
    void init();
    void insert(T t);
    List<T> getAll();

    default T getNewInstance() {
        try {
            return (T) getType().getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    default Class<?> getType() {
        return ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    default String getTableName(){
        return ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getAnnotation(Table.class).name();
    }

    default Field[] getFields() {
        return ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getDeclaredFields();
    }
}
