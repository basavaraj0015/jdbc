import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class RetriveData {

    public static void main(String[] args) {


        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("DB_URL");
        String userName = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        String sql = "SELECT * FROM students";
        try (
                Connection connection =
                        DriverManager.getConnection(url, userName, password);

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            System.out.println("Connected Successfully!");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String course = resultSet.getString("course");

                System.out.println(
                        id + " | " + name + " | " + age + " | " + course
                );
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}