package cinema.Customer;

import java.util.Date;

/**
 *
 * @author DELL
 */
public class Reservations {
    private int userId;
    private int seatId;
    private int hallId;
    private int movId;
    private Date res_date;
    private String status;

    public Reservations(int userId, int seatId, int hallId, int movId) {
        this.userId = userId;
        this.seatId = seatId;
        this.hallId = hallId;
        this.movId = movId;
    }

    public int getMovId() {
        return movId;
    }

    public void setMovId(int movId) {
        this.movId = movId;
    }


    public Reservations(int userId, int seatId, int hallId, Date res_date, String status) {
        this.userId = userId;
        this.seatId = seatId;
        this.hallId = hallId;
        this.res_date = res_date;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public Date getRes_date() {
        return res_date;
    }

    public void setRes_date(Date res_date) {
        this.res_date = res_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
