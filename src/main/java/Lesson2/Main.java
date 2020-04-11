package Lesson2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Connection connection;
    private static Statement stmt;

    public static void main(String[] args) throws FileNotFoundException {

        try {
            connect();
            //работаем с БД с использованием созданных методов, создаем, модифицируем таблицы, делаем запросы
            createTable("People", "id INTEGER PRIMARY KEY AUTOINCREMENT", "name TEXT", "surname TEXT", "birthdate DATE");
            createTable("Dogs", "id INTEGER PRIMARY KEY AUTOINCREMENT", "breed TEXT", "name TEXT", "age INTEGER");
            addField("Dogs", "cost INTEGER");
            insert("Dogs", "breed, name, age, cost", "\"Лабрадор\", \"Джек\", 5, 500");
            insert("Dogs", "breed, name, age, cost", "\"Доберман\", \"Арчи\", 2, 1000");
            insert("Dogs", "breed, name, age, cost", "\"Овчарка\", \"Рекс\", 3, 800");
            insert("Dogs", "breed, name, age, cost", "\"Пудель\", \"Кузя\", 4, 400");
            update("Dogs", "cost", "900", "name", "\"Рекс\"");
            System.out.println("\nВот собачки в нашей БД):");
            ResultSet rs = select("Dogs", "id, breed, name, age, cost");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("breed") + " " + rs.getString("name") +
                        ", возраст - " + rs.getString("age") + ", цена - " + rs.getInt("cost"));
            }

            System.out.println("\nА теперь только собаки дороже 500$:");
            rs = selectWhere("Dogs", "cost > 500", "id, breed, name, age, cost");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("breed") + " " + rs.getString("name") +
                        ", возраст - " + rs.getString("age") + ", цена - " + rs.getInt("cost"));
            }
            dropTable("People");
            dropTable("Dogs");

            //работаем с таблицей students
            clearTable("students");
            insert("students", "name, score", "\"Bob6\", 50");
            insert("students", "name, score", "\"Bob15\", 50");
            insert("students", "name, score", "\"Bob4\", 50");
            insert("students", "name, score", "\"Bob1\", 50");
            insert("students", "name, score", "\"Bob8\", 50");
            insert("students", "name, score", "\"Bob53\", 50");
            System.out.println("\nСписок студентов и их баллов:");
            rs = select("students", "*");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("name") + " " + rs.getInt("score"));
            }

            System.out.println("\nОбновляем данные студентов...");
            FileReader fr = new FileReader("DZ_update.txt");
            Scanner scanner = new Scanner(fr);
            String[] columns = scanner.nextLine().split("\\s+");
            String[] values;
            while (scanner.hasNextLine()) {
                values = scanner.nextLine().split("\\s+");
                try {
                    if (update("students", "score", values[2], columns[1], "\"" + values[1] + "\"") > 0) {
                        System.out.println("Данные студента " + values[1] + " обновлены. Новое значение: " + values[2]);
                    } else {
                        System.out.println("Студент " + values[1] + " не найден");
                    }
                } catch (SQLException e) {
                    System.out.println("Произошла ошибка при обновлении данных студента " + values[1]);
                    e.printStackTrace();
                }
            }

            System.out.println("\nОбновленный список студентов:");
            rs = select("students", "*");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("name") + " " + rs.getInt("score"));
            }

            disconnect();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //DDL: CREATE TABLE, ALTER TABLE, DROP TABLE
    public static int createTable(String tableName, String... fields) throws SQLException {
        StringBuilder sqlSB = new StringBuilder("CREATE TABLE ");
        sqlSB.append(tableName);
        sqlSB.append(" (");
        for (String fieldDesc : fields) {
            sqlSB.append(fieldDesc).append(",");
        }
        sqlSB.setLength(sqlSB.length() - 1);
        sqlSB.append(")");
        return stmt.executeUpdate(sqlSB.toString());
    }

    public static int addField(String tableName, String fieldDesc) throws SQLException {
        StringBuilder sqlSB = new StringBuilder("ALTER TABLE ");
        sqlSB.append(tableName).append(" ADD ").append(fieldDesc);
        return stmt.executeUpdate(sqlSB.toString());
    }

    public static int deleteField(String tableName, String fieldName) throws SQLException {
        StringBuilder sqlSB = new StringBuilder("ALTER TABLE ");
        sqlSB.append(tableName).append(" DROP ").append(fieldName);
        return stmt.executeUpdate(sqlSB.toString());
    }

    public static int dropTable(String tableName) throws SQLException {
        StringBuilder sqlSB = new StringBuilder("DROP TABLE ");
        sqlSB.append(tableName);
        return stmt.executeUpdate(sqlSB.toString());
    }

    //DML: SELECT, INSERT, UPDATE, DELETE
    public static ResultSet select(String tableName, String... fields) throws SQLException {
        StringBuilder sqlSB = new StringBuilder("SELECT ");
        for (String fieldName : fields) {
            sqlSB.append(fieldName).append(",");
        }
        sqlSB.setLength(sqlSB.length() - 1);
        sqlSB.append(" FROM ").append(tableName);
        return stmt.executeQuery(sqlSB.toString());
    }

    public static ResultSet selectWhere(String tableName, String whereCondition, String... fields) throws SQLException {
        StringBuilder sqlSB = new StringBuilder("SELECT ");
        for (String fieldName : fields) {
            sqlSB.append(fieldName).append(",");
        }
        sqlSB.setLength(sqlSB.length() - 1);
        sqlSB.append(" FROM ").append(tableName).append(" WHERE ").append(whereCondition);
        return stmt.executeQuery(sqlSB.toString());
    }

    public static int insert(String tableName, String fields, String values) throws SQLException {
        StringBuilder sqlSB = new StringBuilder("INSERT INTO ");
        sqlSB.append(tableName).append(" (").append(fields).append(") ");
        sqlSB.append(" VALUES (").append(values).append(")");
        return stmt.executeUpdate(sqlSB.toString());
    }

    public static int update(String tableName, String fieldName, String newValue,
                             String conditionField, String conditionValue) throws SQLException {
        StringBuilder sqlSB = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        sqlSB.append(fieldName).append(" = ").append(newValue);
        sqlSB.append(" WHERE ").append(conditionField).append(" = ").append(conditionValue);
        return stmt.executeUpdate(sqlSB.toString());
    }

    public static int delete(String tableName, String conditionField, String conditionValue) throws SQLException {
        StringBuilder sqlSB = new StringBuilder("DELETE FROM ").append(tableName);
        sqlSB.append(" WHERE ").append(conditionField).append(" = ").append(conditionValue);
        return stmt.executeUpdate(sqlSB.toString());
    }

    public static int clearTable(String tableName) throws SQLException {
        StringBuilder sqlSB = new StringBuilder("DELETE FROM ").append(tableName);
        return stmt.executeUpdate(sqlSB.toString());
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
        stmt = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
