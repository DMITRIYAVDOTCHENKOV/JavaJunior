package org.example.seminar_4.hw;





import org.example.seminar_4.models.Student;


import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Program {

    private final static Random random = new Random();

    /**
     * Задача 1
     * ========
     * <p>
     * Используя SQL, создайте таблицу students с полями id (ключ), name, и age.
     * Реализация подключения к базе данных через JDBC:
     * Напишите Java-код для подключения к базе данных (например, MySQL или PostgreSQL).
     * Реализуйте вставку, чтение, обновление и удаление данных в таблице Students
     * с использованием провайдера JDBC.
     */

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:33306/";
        String user = "root";
        String password = "root";

        // Подключение к базе данных
        try (Connection connection = DriverManager.getConnection(url, user, password)) {


            // Создание базы данных
            createDatabase(connection);
            System.out.println("Database created successfully");

            // Использование базы данных
            useDatabase(connection);
            System.out.println("Use database successfully");

            // Создание таблицы
            createTable(connection);
            System.out.println("Create table successfully");

            // Добавление данных
            int count = random.nextInt(5, 11);
            for (int i = 0; i < count; i++)
                insertData(connection, Course.create());
            System.out.println("Insert data successfully");

            // Чтение данных
            Collection<Course> courses = readData(connection);
            for (var curse: courses)
                System.out.println(curse);
            System.out.println("Read data successfully");

            // Обновление данных
            for (var course: courses) {
                course.updateTitle();
                course.getDuration();
                updateData(connection, course);
            }
            System.out.println("Update data successfully");

            // Чтение данных
            courses = readData(connection);
            for (var corse: courses)
                System.out.println(corse);
            System.out.println("Read data successfully");


            // Удаление данных
            for (var corse: courses)
                deleteData(connection, corse.getId());
            System.out.println("Delete data successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //region Вспомогательные методы

    private static void createDatabase(Connection connection) throws SQLException {
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS schoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(createDatabaseSQL)) {
            statement.execute();
        }
    }

    private static void useDatabase(Connection connection) throws SQLException {
        String useDatabaseSQL = "USE schoolDB;";
        try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
            statement.execute();
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS courses (id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), duration INT);";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }

    /**
     * Добавление данных в таблицу courses
     * @param connection Соединение с БД
     * @param course название курса
     * @throws SQLException Исключение при выполнении запроса
     */
    private static void insertData(Connection connection, Course course) throws SQLException {
        String insertDataSQL = "INSERT INTO schoolDB.courses (title, duration) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
            statement.setString(1, course.getTitle());
            statement.setInt(2, course.getDuration());
            statement.executeUpdate();
        }
    }

    /**
     * Чтение данных из таблицы courses
     * @param connection Соединение с БД
     * @return Коллекция студентов
     * @throws SQLException Исключение при выполнении запроса
     */
    private static Collection<Course> readData(Connection connection) throws SQLException {
        ArrayList<Course> courseList = new ArrayList<>();
        String readDataSQL = "SELECT * FROM schoolDB.courses;";
        try (PreparedStatement statement = connection.prepareStatement(readDataSQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int duration = resultSet.getInt("duration");
                courseList.add(new Course(id, title, duration));
            }
            return courseList;
        }
    }

    /**
     * Обновление данных в таблице courses по идентификатору
     * @param connection Соединение с БД
     * @param course курс
     * @throws SQLException Исключение при выполнении запроса
     */
    private static void updateData(Connection connection, Course course) throws SQLException {
        String updateDataSQL = "UPDATE schoolDB.courses SET title=?, duration=? WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(updateDataSQL)) {
            statement.setString(1, course.getTitle());
            statement.setInt(2, course.getDuration());
            statement.setInt(3, course.getId());
            statement.executeUpdate();
        }
    }

    /**
     * Удаление записи из таблицы courses по идентификатору
     * @param connection Соединение с БД
     * @param id Идентификатор записи
     * @throws SQLException Исключение при выполнении запроса
     */
    private static void deleteData(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "DELETE FROM schoolDB.courses WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(deleteDataSQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
//

            //endregion

        }
    }
}