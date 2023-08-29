import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:8081";
        String username = "your_username";
        String password = "your_password";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            // INSERT запит
            String insertQuery = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, "John");
            insertStatement.setString(2, "john@example.com");
            int rowsInserted = insertStatement.executeUpdate();
            System.out.println(rowsInserted + " row(s) inserted.");

            // SELECT запит
            String selectQuery = "SELECT * FROM users";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("name") + ", Email: " + resultSet.getString("email"));
            }

            // UPDATE запит
            String updateQuery = "UPDATE users SET email = ? WHERE name = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, "newemail@example.com");
            updateStatement.setString(2, "John");
            int rowsUpdated = updateStatement.executeUpdate();
            System.out.println(rowsUpdated + " row(s) updated.");

            // DELETE запит
            String deleteQuery = "DELETE FROM users WHERE name = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setString(1, "John");
            int rowsDeleted = deleteStatement.executeUpdate();
            System.out.println(rowsDeleted + " row(s) deleted.");

            resultSet.close();
            insertStatement.close();
            selectStatement.close();
            updateStatement.close();
            deleteStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
