package db;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryMySQL<T> implements Repository<T> {
    static Connection conn;

    public void init(){
        String uri ="jdbc:mysql://localhost/mydatabase?user=myuser&password=mypass";
        try {
            conn = DriverManager.getConnection(uri);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(T t){
        try {
            PreparedStatement statement = conn.prepareStatement("INSERT INTO " + getTableName() + " VALUES(" + "?,".repeat(getFields().length -1) + "?)");
            for (int i=1; i<=getFields().length; i++) {
                statement.setObject(i, t.getClass().getDeclaredField(getFields()[i-1].getName()).get(t));
            }
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<T> getAll() {
        List<T> list = new ArrayList<>();

        try {
            final ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM " +  getTableName());
            while (resultSet.next()) {
                T t = getNewInstance(); //(T) getType().getDeclaredConstructor().newInstance();
                for (Field field: getFields()) {
                    t.getClass().getDeclaredField(field.getName()).set(t, resultSet.getObject(field.getName()));
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
