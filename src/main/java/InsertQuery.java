import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;

// Inserting data with preparedStatement

public class InsertQuery {
    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        String url = dotenv.get("DB_URL");
        String userName = dotenv.get("DB_USERNAME");
        String password = dotenv.get("DB_PASSWORD");
        String query = "INSERT INTO students (id, name, age, course) VALUES (?, ?, ?, ?)";

        try {
            Connection connection =
                    DriverManager.getConnection(url,userName,password);

            PreparedStatement preparedStatement =
                    connection.prepareStatement(query);

            preparedStatement.setInt(1, 21);
            preparedStatement.setString(2,"lota");
            preparedStatement.setInt(3, 22);
            preparedStatement.setString(4,"java");

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected>0)
                System.out.println("Data inserted successfully");
            else
                System.out.println("Data insertion failed ! ");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}

