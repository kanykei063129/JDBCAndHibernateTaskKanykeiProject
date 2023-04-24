package peaksoft.dao;

import peaksoft.model.User;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String sql = "create table if not exists userss(" +
                "id serial primary key," +
                "name varchar," +
                "last_name varchar," +
                "age int)";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(sql);
            statement.close();
            System.out.println("Table is created");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void dropUsersTable() {
        String sql = "drop table if exists userss;";
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("userss is table deleted!!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into userss (" +
                "name, last_name,age)" +
                "values (?,?,?);";

        try(Connection connection = Util.connection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();
            System.out.println(name+" successfully saved!");
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
    }
    public void removeUserById(long id) {
        String sql = "delete from userss where id = ?;";
        try (Connection connection = Util.connection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("id " + id + " deleted!!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<User> getAllUsers() {
        String sql = "select * from userss;";
        List<User> getAll = new ArrayList<>();
        try (Connection connection = Util.connection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while ((resultSet.next())) {
                getAll.add(new User(
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return getAll;
    }

    public void cleanUsersTable() {
        String sql = "truncate userss;";
        try(Connection connection = Util.connection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
            System.out.println("Cleaned!!!");
        }catch(SQLException e ){
            System.out.println(e.getMessage());
        }
    }
}