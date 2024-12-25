package cinema;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author DELL
 */
public class DB_Connection {
    // connection
    private Connection connection;
    private PreparedStatement prepared;
    private Statement statement;
    private ResultSet resultset;    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPrepared() {
        return prepared;
    }

    public void setPrepared(PreparedStatement prepared) {
        this.prepared = prepared;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getResultset() {
        return resultset;
    }

    public void setResultset(ResultSet resultset) {
        this.resultset = resultset;
    }
       public Connection connectDb() {
        String url = "jdbc:mysql://localhost:3306/cinema";
        String userName = "root";
        String password = "";
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (Exception ex) {
            System.out.println("Connection failed: " + ex.getMessage());
        }
        return null;
    }
//        public static void main(String[] args) {
//   DB_Connection db = new DB_Connection();
//   
//            System.out.println(db.connectDb());
//    }
}
