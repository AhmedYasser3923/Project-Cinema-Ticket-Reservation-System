package cinema.Ticket_attendant;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author DELL
 */
public class Movies {
    private String movie_name;
    private String duration;
    private LocalDate releaseData;
    private String img;
    private String price;
    private int id;

    public Movies(String movie_name, String duration, LocalDate releaseData, String img, String price) {
        this.movie_name = movie_name;
        this.duration = duration;
        this.releaseData = releaseData;
        this.img = img;
        this.price = price;
    }

    public Movies(String movie_name, String duration, LocalDate releaseData, String img, String price, int id) {
        this.movie_name = movie_name;
        this.duration = duration;
        this.releaseData = releaseData;
        this.img = img;
        this.price = price;
        this.id = id;
    }

    public Movies(String movie_name) {
        this.movie_name = movie_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseData() {
        return releaseData;
    }

    public void setReleaseData(LocalDate releaseData) {
        this.releaseData = releaseData;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
}
