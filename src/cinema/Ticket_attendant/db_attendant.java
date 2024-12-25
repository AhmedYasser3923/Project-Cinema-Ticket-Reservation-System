package cinema.Ticket_attendant;

import cinema.DB_Connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DELL
 */
public class db_attendant {

    public void register(TicketAttendant t) {
        DB_Connection db = new DB_Connection();
        try {
            String insertQuery = "INSERT INTO users (username, password , role_id) VALUES (?, ?, ?)";
            db.setConnection(db.connectDb());
            db.setPrepared(db.getConnection().prepareStatement(insertQuery));
            // Set the parameters for the prepared statement
            db.getPrepared().setString(1, t.getUserName());
            db.getPrepared().setString(2, t.getPassword());
            db.getPrepared().setInt(3, t.getRole_id());

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

    public boolean login(String userName , String password) {
        DB_Connection db = new DB_Connection();
        boolean isLoggedIn = false;

        try {
            // Define the SQL query for checking username and password
            String selectQuery = "SELECT * FROM users WHERE username = ? AND password = ? AND role_id = 2";

            // Establish a database connection
            db.setConnection(db.connectDb());

            // Prepare the SQL statement
            db.setPrepared(db.getConnection().prepareStatement(selectQuery));

            // Set the parameters for the prepared statement
            db.getPrepared().setString(1, userName);
            db.getPrepared().setString(2, password);

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

//    insert Movie
    public void insertMovie(Movies m) {
        DB_Connection db = new DB_Connection();
        try {
            String insertQuery = "INSERT INTO movies (movie_name, duration , release_date , image , price) VALUES (?, ?, ?,?,?)";
            db.setConnection(db.connectDb());
            db.setPrepared(db.getConnection().prepareStatement(insertQuery));
            // Set the parameters for the prepared statement
            db.getPrepared().setString(1, m.getMovie_name());
            db.getPrepared().setString(2, m.getDuration());
            db.getPrepared().setDate(3, java.sql.Date.valueOf(m.getReleaseData()));
            db.getPrepared().setString(4, m.getImg());
            db.getPrepared().setString(5, m.getPrice());

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
//    insert Hall

    public void inserHall(Halls h) {
        DB_Connection db = new DB_Connection();
        try {
            String insertQuery = "INSERT INTO hall (hall_name) VALUES (?)";
            db.setConnection(db.connectDb());
            db.setPrepared(db.getConnection().prepareStatement(insertQuery));
            // Set the parameters for the prepared statement
            db.getPrepared().setString(1, h.getHall());

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

    public ObservableList<Movies> MoviesName() {
        ObservableList<Movies> Movielist = FXCollections.observableArrayList();
        String query = "SELECT movie_name FROM movies";
        DB_Connection db = new DB_Connection();
        try {
            db.setConnection(db.connectDb());
            if (db.getConnection() != null) {
                db.setPrepared(db.getConnection().prepareStatement(query));
                db.setResultset(db.getPrepared().executeQuery());
                while (db.getResultset().next()) {
                    Movies m = new Movies(
                            db.getResultset().getString("movie_name"));

                    Movielist.add(m);
                }
            }
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
        return Movielist;
    }

    public ObservableList<Halls> Hall_name() {
        ObservableList<Halls> hallslist = FXCollections.observableArrayList();
        String query = "SELECT hall_name FROM hall";
        DB_Connection db = new DB_Connection();
        try {
            db.setConnection(db.connectDb());
            if (db.getConnection() != null) {
                db.setPrepared(db.getConnection().prepareStatement(query));
                db.setResultset(db.getPrepared().executeQuery());
                while (db.getResultset().next()) {
                    Halls h = new Halls(
                            db.getResultset().getString("hall_name"));

                    hallslist.add(h);
                }
            }
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
        return hallslist;
    }
//    get id for movie

    public int getMovieId(String movie) {
        String query = "SELECT movie_id FROM movies WHERE movie_name = ?";
        int movieid = 0;
        DB_Connection db = new DB_Connection();
        try (Connection connection = db.connectDb(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, movie);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    movieid = resultSet.getInt("movie_id");
                }
            }

        } catch (Exception ex) {
            System.out.println("Error retrieving category ID: " + ex.getMessage());
        }

        return movieid;
    }
//    get id for Halls

    public int getHallId(String hall) {
        String query = "SELECT hall_id FROM hall WHERE hall_name = ?";
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

//     insert new seat
    public void insertSeat(Seats s) {
        DB_Connection db = new DB_Connection();
        try {
            String insertQuery = "INSERT INTO seats (hall_id, movie_id , seat_number) VALUES (? , ? , ?)";
            db.setConnection(db.connectDb());
            db.setPrepared(db.getConnection().prepareStatement(insertQuery));
            // Set the parameters for the prepared statement
            db.getPrepared().setInt(1, s.getHallId());
            db.getPrepared().setInt(2, s.getMovieId());
            db.getPrepared().setInt(3, s.getSeatNumber());

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
// Select all movies

    public ObservableList<Movies> selectAll_Movies() {
        ObservableList<Movies> movieList = FXCollections.observableArrayList();
        String query = "SELECT * FROM movies";
        DB_Connection db = new DB_Connection();
        try {
            db.setConnection(db.connectDb());
            if (db.getConnection() != null) {
                db.setPrepared(db.getConnection().prepareStatement(query));
                db.setResultset(db.getPrepared().executeQuery());
                while (db.getResultset().next()) {
                    // Convert release_date from java.sql.Date to java.time.LocalDate
                    LocalDate releaseDate = db.getResultset().getDate("release_date").toLocalDate();

                    Movies mv = new Movies(
                            db.getResultset().getString("movie_name"),
                            db.getResultset().getString("duration"),
                            releaseDate, // Use the converted LocalDate
                            db.getResultset().getString("image"),
                            db.getResultset().getString("price"),
                            db.getResultset().getInt("movie_id")
                    );
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
// Select all Pending Tickets

    public ObservableList<PendingTickets> selectAll_PendingTickets() {
        ObservableList<PendingTickets> ticketList = FXCollections.observableArrayList();
        String query = "SELECT * FROM view_pending_tickets";
        DB_Connection db = new DB_Connection();
        try {
            db.setConnection(db.connectDb());
            if (db.getConnection() != null) {
                db.setPrepared(db.getConnection().prepareStatement(query));
                db.setResultset(db.getPrepared().executeQuery());
                while (db.getResultset().next()) {
                    // Convert release_date from java.sql.Date to java.time.LocalDate
                    LocalDate releaseDate = db.getResultset().getDate("release_date").toLocalDate();

                    PendingTickets PT = new PendingTickets(
                            db.getResultset().getString("username"),
                            db.getResultset().getString("movie_name"),
                            db.getResultset().getInt("seat_number"),
                            releaseDate, // Use the converted LocalDate
                            db.getResultset().getInt("reservation_id"),
                            db.getResultset().getString("price"),
                            db.getResultset().getString("status"),
                            db.getResultset().getInt("seat_id"),
                            db.getResultset().getDate("reservation_date")
                    );
                    ticketList.add(PT);
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
        return ticketList;
    }
    public ObservableList<PendingTickets> selectAllTickets() {
        ObservableList<PendingTickets> ticketList = FXCollections.observableArrayList();
        String query = "SELECT * FROM selectalltickets";
        DB_Connection db = new DB_Connection();
        try {
            db.setConnection(db.connectDb());
            if (db.getConnection() != null) {
                db.setPrepared(db.getConnection().prepareStatement(query));
                db.setResultset(db.getPrepared().executeQuery());
                while (db.getResultset().next()) {
                    // Convert release_date from java.sql.Date to java.time.LocalDate
                    LocalDate releaseDate = db.getResultset().getDate("release_date").toLocalDate();

                    PendingTickets PT = new PendingTickets(
                            db.getResultset().getString("username"),
                            db.getResultset().getString("movie_name"),
                            db.getResultset().getInt("seat_number"),
                            releaseDate, // Use the converted LocalDate
                            db.getResultset().getInt("reservation_id"),
                            db.getResultset().getString("price"),
                            db.getResultset().getString("status"),
                            db.getResultset().getInt("seat_id"),
                            db.getResultset().getDate("reservation_date")
                    );
                    ticketList.add(PT);
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
        return ticketList;
    }

         // Update seat availability to 0 where seatId matches
public void updateStatusReserv(int reserv_id) {
    DB_Connection db = new DB_Connection();

    try {

        String updateQuery = "UPDATE reservation SET status = 'Accepted' WHERE reservation_id  = ?";

        db.setConnection(db.connectDb());
        db.setPrepared(db.getConnection().prepareStatement(updateQuery));

        db.getPrepared().setInt(1, reserv_id);


        int rowsAffected = db.getPrepared().executeUpdate();

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

public void deleteReservation(int reserv_id) {
    DB_Connection db = new DB_Connection();

    try {
        // SQL query to delete the reservation
        String deleteQuery = "DELETE FROM reservation WHERE reservation_id = ?";

        db.setConnection(db.connectDb());
        db.setPrepared(db.getConnection().prepareStatement(deleteQuery));

        db.getPrepared().setInt(1, reserv_id); // Set the reservation ID parameter

        int rowsAffected = db.getPrepared().executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Reservation deleted successfully.");
        } else {
            System.out.println("No reservation found with the provided reservation ID.");
        }

    } catch (Exception ex) {
        // Log any errors
        System.out.println("Error deleting reservation: " + ex.getMessage());
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

    
}
