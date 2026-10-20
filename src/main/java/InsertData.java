
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class InsertData {

    public static void main(String[] args) {


        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("DB_URL");
        String userName = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        try {
            {
                Connection connection = DriverManager.getConnection(url, userName, password);
                System.out.println("Connected Successfully ! ");

                Statement statement = connection.createStatement();

                String sql = "INSERT INTO students(id, name, age, course) VALUES (7, 'Rohan', 17, 'Java')";
                String query = "select * from students";

                {
                    int rowsAffected = statement.executeUpdate(sql);
                    if (rowsAffected > 0) {
                        System.out.println("Data inserted successfully ");
                    } else {
                        System.out.println("Data is not inserted  ! ");
                    }
                }
                {
                    ResultSet resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int age = resultSet.getInt("age");
                        String course = resultSet.getString("course");

                        System.out.println(
                                id + " | " + name + " | " + age + " | " + course
                        );
                    }
                }
            }

        }
        catch (SQLException e )
        {
            System.out.println(e.getMessage());
        }

    }
}