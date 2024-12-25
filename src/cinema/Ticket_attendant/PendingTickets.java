
package cinema.Ticket_attendant;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class PendingTickets {
    private String userName;
    private String movieName;
    private int seatNumber;
    private LocalDate reDate;
    private int reserId;
    private String price;
    private String status;
    private int seatId;
    private Date Reser_date;

    public PendingTickets(String userName, String movieName, int seatNumber, LocalDate reDate, int reserId, String price, String status, int seatId, Date Reser_date) {
        this.userName = userName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.reDate = reDate;
        this.reserId = reserId;
        this.price = price;
        this.status = status;
        this.seatId = seatId;
        this.Reser_date = Reser_date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public LocalDate getReDate() {
        return reDate;
    }

    public void setReDate(LocalDate reDate) {
        this.reDate = reDate;
    }

    public int getReserId() {
        return reserId;
    }

    public void setReserId(int reserId) {
        this.reserId = reserId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Date getReser_date() {
        return Reser_date;
    }

    public void setReser_date(Date Reser_date) {
        this.Reser_date = Reser_date;
    }
    
}
