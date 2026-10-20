import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;
public class DeleteData {

    public static void main(String[] args){
        Dotenv dotenv = Dotenv.load();

        String url = dotenv.get("DB_URL");
        String userName = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");

        try {
            Connection connection = DriverManager.getConnection(url,userName,password);
            Statement statement = connection.createStatement();
            String deleteQuery = "DELETE FROM students where id = 7";
            String selectQuery = "SELECT * FROM students";
            int rowsAffected = statement.executeUpdate(deleteQuery);
            if (rowsAffected>0){
                System.out.println("Student removed successfully ");
            }
            else
                System.out.println("Deletion process failed !");
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");
                String name = resultSet.getString("name");
                String course = resultSet.getString("course");
                System.out.println(
                        id + "|" + name + "|" + age + "|" + course
                );
            }
            statement.close();
            connection.close();
            resultSet.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
