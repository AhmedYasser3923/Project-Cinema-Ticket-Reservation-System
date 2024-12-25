
package cinema.Ticket_attendant;

/**
 *
 * @author DELL
 */
public class Seats {
    private int seatNumber;
    private int hallId;
    private int MovieId;
    private int availability;
    private String hall_name;

    public Seats(String hall_name) {
        this.hall_name = hall_name;
    }


    public Seats(int seatNumber, int hallId, int MovieId, int availability) {
        this.seatNumber = seatNumber;
        this.hallId = hallId;
        this.MovieId = MovieId;
        this.availability = availability;
    }

    public Seats(int seatNumber, int hallId, int MovieId) {
        this.seatNumber = seatNumber;
        this.hallId = hallId;
        this.MovieId = MovieId;
    }

    public Seats(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    public String getHall_name() {
        return hall_name;
    }

    public void setHall_name(String hall_name) {
        this.hall_name = hall_name;
    }
    
 
    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public int getMovieId() {
        return MovieId;
    }

    public void setMovieId(int MovieId) {
        this.MovieId = MovieId;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }
    
}
