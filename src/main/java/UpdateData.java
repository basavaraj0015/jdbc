import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class UpdateData {
  public static void main(String[] args) {

    Dotenv dotenv = Dotenv.load();

    String url = dotenv.get("DB_URL");
    String userName = dotenv.get("DB_USERNAME");
    String password = dotenv.get("DB_PASSWORD");
    try {
      Connection connection = DriverManager.getConnection(url, userName, password);
      System.out.println("Database connected successfully");
      Statement statement = connection.createStatement();
      {
        {
          String updateQuery = "update students set name = 'Ramu' where id = 6";
          int rowsAffected = statement.executeUpdate(updateQuery);
          if (rowsAffected > 0) System.out.println("Data updated successfully ");
          else System.out.println("Data update failed !");
        }
        {
          String retriveQuery = "select * from students";
          ResultSet resultSet = statement.executeQuery(retriveQuery);
          while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int age = resultSet.getInt("age");
            String course = resultSet.getString("course");
            System.out.println(id + " | " + name + " | " + age + " | " + course);
          }
        }
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
}
