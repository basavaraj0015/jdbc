import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class Execute {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("DB_URL");
        String userName = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            Statement statement = connection.createStatement();
            String sql = "update students set age = 99 where id = 1 ";
            boolean status = statement.execute(sql);
            if (status) {
                ResultSet resultSet = statement.getResultSet();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    String course = resultSet.getString("course");

                    System.out.println(
                            id + " | " + name + " | " + age + " | " + course
                    );
                }
            } else {
                int rowsAffected = statement.getUpdateCount();
                if (rowsAffected == 0) {
                    System.out.println("No rows were affected.");
                } else {
                    System.out.println(
                            "Operation successful! Rows affected: " + rowsAffected
                    );
                }
            }
        } catch (
                SQLException e
        ) {
            System.out.println(e.getMessage());
        }
    }
}
