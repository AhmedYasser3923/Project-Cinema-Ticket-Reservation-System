package cinema.Customer;

import cinema.DB_Connection;
import cinema.Ticket_attendant.Halls;
import cinema.Ticket_attendant.Movies;
import cinema.Ticket_attendant.Seats;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class db_cust {

    public void register(Customer c) {
        DB_Connection db = new DB_Connection();
        try {
            String insertQuery = "INSERT INTO users (username, password , role_id) VALUES (?, ?, ?)";
            db.setConnection(db.connectDb());
            db.setPrepared(db.getConnection().prepareStatement(insertQuery));
            // Set the parameters for the prepared statement
            db.getPrepared().setString(1, c.getUserName());
            db.getPrepared().setString(2, c.getPassword());
            db.getPrepared().setInt(3, c.getRole_id());

            // Execute the insert statement
            db.getPrepared().executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error retrieving products: " + ex.getMessage());
        } finally {
            // Close resources
            try {
                if (db.getResultset() != null) {
                    db.getResultset().close();
                }
                if (db.getPrepared() != null) {
                    db.getPrepared().close();
                }
                if (db.getConnection() != null) {
                    db.getConnection().close();
                }
            } catch (Exception ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }
    }

    public boolean login(String user, String pass ) {
        DB_Connection db = new DB_Connection();
        boolean isLoggedIn = false;

        try {
            // Define the SQL query for checking username and password
            String selectQuery = "SELECT * FROM users WHERE username = ? AND password = ? AND role_id = 1";

            // Establish a database connection
            db.setConnection(db.connectDb());

            // Prepare the SQL statement
            db.setPrepared(db.getConnection().prepareStatement(selectQuery));

            // Set the parameters for the prepared statement
            db.getPrepared().setString(1, user);
            db.getPrepared().setString(2, pass);

            // Execute the query
            db.setResultset(db.getPrepared().executeQuery());

            // Check if a matching record exists
            if (db.getResultset().next()) {
                isLoggedIn = true;
            }

        } catch (Exception ex) {
            System.out.println("Error during login: " + ex.getMessage());
        } finally {
            // Close resources to prevent memory leaks
            try {
                if (db.getResultset() != null) {
                    db.getResultset().close();
                }
                if (db.getPrepared() != null) {
                    db.getPrepared().close();
                }
                if (db.getConnection() != null) {
                    db.getConnection().close();
                }
            } catch (Exception ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }

        return isLoggedIn;
    }

    //    get id for user
    public int getuserid(String userName) {
        String query = "SELECT user_id FROM `users` WHERE username = ?";
        int userid = 0;
        DB_Connection db = new DB_Connection();
        try (Connection connection = db.connectDb(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, userName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userid = resultSet.getInt("user_id");
                }
            }

        } catch (Exception ex) {
            System.out.println("Error retrieving category ID: " + ex.getMessage());
        }

        return userid;
    }

// Select all Seats numbers where movie id
    public ObservableList<Seats> selectSeats(int movId) {
        ObservableList<Seats> seatList = FXCollections.observableArrayList();
        String query = "SELECT seat_number FROM seats WHERE movie_id = ? AND availability = 1";
        DB_Connection db = new DB_Connection();

        // Using try-with-resources to automatically close resources
        try (Connection conn = db.connectDb(); PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the movie ID in the prepared statement
            stmt.setInt(1, movId);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Assuming Seats constructor takes ResultSet or processes it accordingly
                    Seats seat = new Seats(rs.getInt("seat_number"));
                    seatList.add(seat);
                }
            }
        } catch (Exception ex) {
            // Log the error message or use a logger for better error management
            System.err.println("Error retrieving seats: " + ex.getMessage());
        }

        return seatList;
    }
// Select all Hall names where movie id, returning an ObservableList of Seats

    public ObservableList<Seats> selectHalls(int movId) {
        ObservableList<Seats> hallList = FXCollections.observableArrayList();
        String query = "SELECT DISTINCT hall.hall_name FROM seats "
                + "JOIN hall ON seats.hall_id = hall.hall_id WHERE movie_id = ?";
        DB_Connection db = new DB_Connection();

        // Using try-with-resources to automatically close resources
        try (Connection conn = db.connectDb(); PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the movie ID in the prepared statement
            stmt.setInt(1, movId);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    // Assuming the Seats class has a constructor to store hall name
                    String hallName = rs.getString("hall_name");
                    Seats seat = new Seats(hallName);  // Create a new Seats object with hall name
                    hallList.add(seat);  // Add the seat object to the list
                }
            }
        } catch (Exception ex) {
            // Log the error message or use a logger for better error management
            System.err.println("Error retrieving halls: " + ex.getMessage());
        }

        return hallList;
    }

//     insert new Reservation
    public void Reserve(Reservations r) {
        DB_Connection db = new DB_Connection();
        try {
            String insertQuery = "INSERT INTO reservation (user_id , seat_id  , hall_id , movie_id ) VALUES (? , ? , ?,?)";
            db.setConnection(db.connectDb());
            db.setPrepared(db.getConnection().prepareStatement(insertQuery));
            // Set the parameters for the prepared statement
            db.getPrepared().setInt(1, r.getUserId());
            db.getPrepared().setInt(2, r.getSeatId());
            db.getPrepared().setInt(3, r.getHallId());
            db.getPrepared().setInt(4, r.getMovId());

            // Execute the insert statement
            db.getPrepared().executeUpdate();

        } catch (Exception ex) {
            System.out.println("Error retrieving products: " + ex.getMessage());
        } finally {
            // Close resources
            try {
                if (db.getResultset() != null) {
                    db.getResultset().close();
                }
                if (db.getPrepared() != null) {
                    db.getPrepared().close();
                }
                if (db.getConnection() != null) {
                    db.getConnection().close();
                }
            } catch (Exception ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }

    }

    // Update seat availability to 0 where seatId matches
    public void updateSeatAvailability(int seatId, int ava) {
        DB_Connection db = new DB_Connection();

        try {
            // SQL query to update availability
            String updateQuery = "UPDATE seats SET availability = ? WHERE seat_id = ?";

            // Establish the connection and prepare the statement
            db.setConnection(db.connectDb());
            db.setPrepared(db.getConnection().prepareStatement(updateQuery));

            // Set the seatId parameter for the query
            db.getPrepared().setInt(1, ava);
            db.getPrepared().setInt(2, seatId);

            // Execute the update query
            int rowsAffected = db.getPrepared().executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                System.out.println("Seat availability updated successfully.");
            } else {
                System.out.println("No seat found with the provided seatId.");
            }

        } catch (Exception ex) {
            // Log any errors
            System.out.println("Error updating seat availability: " + ex.getMessage());
        } finally {
            // Close resources
            try {
                if (db.getResultset() != null) {
                    db.getResultset().close();
                }
                if (db.getPrepared() != null) {
                    db.getPrepared().close();
                }
                if (db.getConnection() != null) {
                    db.getConnection().close();
                }
            } catch (Exception ex) {
                System.out.println("Error closing resources: " + ex.getMessage());
            }
        }
    }

    //    get id for seat
    public int getseatId(int seatNumber) {
        String query = "SELECT seat_id  FROM `seats` WHERE seat_number  = ?";
        int seatid = 0;
        DB_Connection db = new DB_Connection();
        try (Connection connection = db.connectDb(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, seatNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    seatid = resultSet.getInt("seat_id");
                }
            }

        } catch (Exception ex) {
            System.out.println("Error retrieving category ID: " + ex.getMessage());
        }

        return seatid;
    }
    //    get id for hall

    public int getHallId(String hall) {
        String query = "SELECT hall_id   FROM `hall` WHERE hall_name   = ?";
        int hallid = 0;
        DB_Connection db = new DB_Connection();
        try (Connection connection = db.connectDb(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, hall);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    hallid = resultSet.getInt("hall_id");
                }
            }

        } catch (Exception ex) {
            System.out.println("Error retrieving category ID: " + ex.getMessage());
        }

        return hallid;
    }

public ObservableList<Movies> MovieSearch(String searchKey) {
    ObservableList<Movies> movieList = FXCollections.observableArrayList();
    String query = "SELECT * FROM movies WHERE movie_name LIKE ?"; // SQL query for search
    DB_Connection db = new DB_Connection();
    
    try {
        db.setConnection(db.connectDb());
        if (db.getConnection() != null) {
            db.setPrepared(db.getConnection().prepareStatement(query));
            
            // Use a parameterized query to prevent SQL injection
            db.getPrepared().setString(1, "%" + searchKey + "%");
            db.setResultset(db.getPrepared().executeQuery());
            
            // Process the result set
            while (db.getResultset().next()) {
                // Convert release_date from java.sql.Date to java.time.LocalDate
                LocalDate releaseDate = db.getResultset().getDate("release_date").toLocalDate();
                
                // Create a Movies object with all attributes
                Movies mv = new Movies(
                        db.getResultset().getString("movie_name"),  // Movie name
                        db.getResultset().getString("duration"),    // Duration
                        releaseDate,                                // Release date as LocalDate
                        db.getResultset().getString("image"),       // Image file/path
                        db.getResultset().getString("price"),       // Price
                        db.getResultset().getInt("movie_id")        // Movie ID
                );
                
                // Add the Movies object to the list
                movieList.add(mv);
            }
        }
    } catch (Exception ex) {
        System.out.println("Error retrieving movies: " + ex.getMessage());
    } finally {
        // Close resources
        try {
            if (db.getResultset() != null) {
                db.getResultset().close();
            }
            if (db.getPrepared() != null) {
                db.getPrepared().close();
            }
            if (db.getConnection() != null) {
                db.getConnection().close();
            }
        } catch (Exception ex) {
            System.out.println("Error closing resources: " + ex.getMessage());
        }
    }
    return movieList;
}






}
