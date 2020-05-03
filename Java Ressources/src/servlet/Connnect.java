package servlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Connnect {
    
   	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
   		try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/camelitoLocal", "postgres", "123")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    	}
}
