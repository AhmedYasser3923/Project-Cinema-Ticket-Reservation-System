package cinema.Ticket_attendant;

import cinema.Customer.CustomerFXMLController;
import cinema.Customer.db_cust;
import cinema.Server.EventBus;
import cinema.Server.EventBus2;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class Ticket_AttendantFXMLController implements Initializable, EventBus.Observer {

    @FXML
    private TableColumn<Movies, String> Duration_col;

    @FXML
    private TableColumn<Movies, Integer> Movie_id_col;

    @FXML
    private TableColumn<Movies, String> Movie_name_col;

    @FXML
    private TableView<Movies> Movies_Table;

    @FXML
    private TableView<PendingTickets> PendingTable;

    @FXML
    private AnchorPane Movies_pane;

    @FXML
    private DatePicker R_date;

    @FXML
    private TextField add_hall_name;

    @FXML
    private AnchorPane add_hall_pane;

    @FXML
    private Button add_halls_btn;

    @FXML
    private AnchorPane add_movie;

    @FXML
    private Button add_movie_btn;

    @FXML
    private Button add_seats_btn;

    @FXML
    private AnchorPane add_seats_pane;

    @FXML
    private Button clear_btn;

    @FXML
    private Button dash_btn;

    @FXML
    private AnchorPane dashboard_pane;

    @FXML
    private ComboBox<String> duration;

    @FXML
    private ComboBox<String> hall_id_seat;

    @FXML
    private ImageView img;

    @FXML
    private TableColumn<Movies, String> img_col;

    @FXML
    private Label img_lab;

    @FXML
    private ImageView img_view;

    @FXML
    private Button logout_btn;

    @FXML
    private Button maximize;

    @FXML
    public Button minimize;

    @FXML
    private TextField mov_name;
    @FXML
    private TextField dur;
    @FXML
    private TextField price9;
    @FXML
    private TextField release_date;

    @FXML
    private ComboBox<String> movie_id_seat;

    @FXML
    private Button movie_list_btn;

    @FXML
    private TextField movie_name;

    @FXML
    private Button movies_btn;

    @FXML
    private AnchorPane movies_list;

    @FXML
    private AnchorPane movies_navbar;

    @FXML
    private ComboBox<String> price;

    @FXML
    private TableColumn<Movies, String> price_col;

    @FXML
    private TableColumn<Movies, LocalDate> r_date_col;
    @FXML
    private TableColumn<PendingTickets, String> username0_col;
    @FXML
    private TableColumn<PendingTickets, String> mov_name0_col;
    @FXML
    private TableColumn<PendingTickets, Integer> seatNum0_col;
    @FXML
    private TableColumn<PendingTickets, LocalDate> R_date0_col;
    @FXML
    private TableColumn<PendingTickets, Integer> Reser_id0_col;
    @FXML
    private TableColumn<PendingTickets, String> Price0_col;
    @FXML
    private TableColumn<PendingTickets, String> status0_col;
    @FXML
    private TableColumn<PendingTickets, Integer> seatId0_col;
    @FXML
    private TableColumn<PendingTickets, Date> Reserv_date0_col;
    @FXML
    private TextField search_bar;
    @FXML
    private Button reserv_btn;
    @FXML
    private Button confirm_btn_acce;
    @FXML
    private Button delete_btn_reser;

    @FXML
    private AnchorPane reserv_pane;

    @FXML
    private Button search_btn;

    @FXML
    private TextField reser_id_8;
    @FXML
    private TextField seat_id_8;

    @FXML
    private ComboBox<Integer> seat_number;

    @FXML
    private Button update_data;

    @FXML
    private Button upload_img;

    @FXML
    private Label welcome_title;

    private ObservableList<Movies> movieList0;
    private ObservableList<PendingTickets> pendingTicketList0;
    private final String SAVE_FOLDER_PATH = "D:/New folder (3)/Level 4/Fall 24/Parallel Processing/Practical/Project Cinema Ticket Reservation System/Cinema/src/cinema/Ticket_attendant/img";
    private static double x = 0;
    private static double y = 0;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    //    close button
    public void close() {
        System.exit(0);
    }

    public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
        Parent root = loader.load();
        SigninController1.username = "";
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 900, 500);
        stage_movement(stage, root);
        cinema.Cinema.minimize(stage, loader);
        stage.setScene(scene);
        stage.show();
    }

    public static void stage_movement(Stage stage, Parent root) {
        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8);
        });
        root.setOnMouseReleased((MouseEvent event) -> {
            stage.setOpacity(1);
        });
    }
//     print movies in combobox

    public void printMovies() {
        db_attendant db = new db_attendant();
        ObservableList<Movies> movies_list = db.MoviesName();

        List<String> moviesName = new ArrayList<>();
        for (Movies mv : movies_list) {
            moviesName.add(mv.getMovie_name());
        }

        ObservableList<String> observableMovieList = FXCollections.observableArrayList(moviesName);
        movie_id_seat.setItems(observableMovieList);
    }
//     print Hall in combobox

    public void printHalls() {
        db_attendant db = new db_attendant();
        ObservableList<Halls> halls_list = db.Hall_name();

        List<String> hallsName = new ArrayList<>();
        for (Halls hall : halls_list) {
            hallsName.add(hall.getHall());
        }

        ObservableList<String> observableMovieList = FXCollections.observableArrayList(hallsName);
        hall_id_seat.setItems(observableMovieList);
    }
//     print Seat Number in combobox

    public void printSeatNumber() {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        for (int i = 1; i <= 100; i++) {
            list.add(i);
        }
        seat_number.setItems(list);
    }

    // duration
    private String[] duration0;

    public void printDuration(ComboBox<String> duration) {
        double start = 1.0;
        double end = 15.0;
        double step = 0.1;

        int size = (int) ((end - start) / step) + 1;
        duration0 = new String[size];

        double value = start;
        for (int i = 0; i < duration0.length; i++) {
            duration0[i] = String.format("%.1f hrs", value);
            value += step;
        }

        ObservableList<String> list = FXCollections.observableArrayList(duration0);
        duration.setItems(list);
    }
    // price
    private String[] price0;

    public void printPrice() {

        price0 = new String[40];
        int j = 5;
        for (int i = 0; i < price0.length; i++) {
            price0[i] = (100 + j) + " EGP";
            j += 5;
        }

        ObservableList<String> list = FXCollections.observableArrayList(price0);
        price.setItems(list);
    }

// image
    public void InsertImg_Movie() {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) img.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            Image img = new Image(file.toURI().toString(), 200, 150, false, true);
            this.img.setImage(img);

            // Save the image and get the new path
            String newFilePath = saveImageToFolder(file);
            if (newFilePath != null) {
                img_lab.setText(newFilePath); // Update the path display
            } else {
                img_lab.setText("Failed to save image.");
                System.out.println("Failed to save image.");
            }
        }
    }

    public String saveImageToFolder(File sourceFile) {
        Date date = new Date();
        String fileName = date.getTime() + "_" + sourceFile.getName();
        File destFile = new File(SAVE_FOLDER_PATH + File.separator + fileName);
        try {
            // Create the folder if it doesn't exist
            File saveFolder = new File(SAVE_FOLDER_PATH);
            if (!saveFolder.exists()) {
                saveFolder.mkdirs();
            }

            // Copy the file to the destination folder
            Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Image saved to: " + destFile.getAbsolutePath());
            return destFile.getAbsolutePath(); // Return the absolute path of the saved image
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save image.");
            return null; // Return null if saving fails
        }
    }

//  insert Movie
    public void AddNewMovie(ActionEvent event) {
        db_attendant db = new db_attendant();
        if (movie_name.getText().isEmpty() || duration.getSelectionModel().isEmpty()
                || R_date.getValue() == null || price.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank input");
            alert.setHeaderText("");
            alert.setContentText("All fields are required. Please fill in all fields.");
            alert.showAndWait();
            clearMovies_Field();
        } else {
            String movieName = movie_name.getText();
            String dur = duration.getSelectionModel().getSelectedItem();
            LocalDate d = R_date.getValue();
            String p = price.getSelectionModel().getSelectedItem();
            if (img_lab.getText() == null || img_lab.getText().trim().isEmpty()) {
                img_lab.setText(SAVE_FOLDER_PATH + "movie.png"); // default image
            }
            System.out.println("Image path: " + img_lab.getText());
            String imge = img_lab.getText();
            Movies mv = new Movies(movieName, dur, d, imge, p);
            db.insertMovie(mv);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Movie Added Successfully");
            alert.setHeaderText("");
            alert.setContentText("Movie Added Successfully.");
            alert.showAndWait();
            clearMovies_Field();
            printMovies();
            showMoviesData();
             ObservableList<Movies> updatedList = db.selectAll_Movies(); // Fetch the updated list
            EventBus2.getInstance().notifyObservers(updatedList); // Notify observers

        }
    }
// add new Hall

    public void addHall(ActionEvent event) {
        db_attendant db = new db_attendant();
        if (add_hall_name.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank input");
            alert.setHeaderText("");
            alert.setContentText("All fields are required. Please fill in all fields.");
            alert.showAndWait();
            add_hall_name.setText("");
        } else {
            Halls h = new Halls(add_hall_name.getText());
            db.inserHall(h);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Movie Added Successfully");
            alert.setHeaderText("");
            alert.setContentText("Movie Added Successfully.");
            alert.showAndWait();
            add_hall_name.setText("");
        }
        printHalls();
    }
// add new Seats

    public void addSeat(ActionEvent event) {
        db_attendant db = new db_attendant();
        if (seat_number.getSelectionModel().isEmpty() || movie_id_seat.getSelectionModel().isEmpty()
                || hall_id_seat.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank input");
            alert.setHeaderText("");
            alert.setContentText("All fields are required. Please fill in all fields.");
            alert.showAndWait();
            clearSeats();
        } else {
            int seatNum = seat_number.getSelectionModel().getSelectedItem();
            int movieId = db.getMovieId(movie_id_seat.getSelectionModel().getSelectedItem());
            int hallId = db.getHallId(hall_id_seat.getSelectionModel().getSelectedItem());
            Seats s = new Seats(seatNum, hallId, movieId);
            db.insertSeat(s);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Seat Added Successfully");
            alert.setHeaderText("");
            alert.setContentText("Seat Added Successfully.");
            alert.showAndWait();
            clearSeats();
        }
    }

    public void clearMovies_Field() {
        movie_name.setText("");
        duration.getSelectionModel().clearSelection();
        R_date.setValue(null);
        img_lab.setText("");
        img.setImage(null);
        price.getSelectionModel().clearSelection();
    }

    public void clearSeats() {
        seat_number.getSelectionModel().clearSelection();
        movie_id_seat.getSelectionModel().clearSelection();
        hall_id_seat.getSelectionModel().clearSelection();
    }

    public void setPaneVisibility(AnchorPane Activepane) {
        dashboard_pane.setVisible(false);
        Movies_pane.setVisible(false);
        reserv_pane.setVisible(false);
        Activepane.setVisible(true);
    }

    public void setPaneVisibility2(AnchorPane Activepane) {
        movies_list.setVisible(false);
        add_movie.setVisible(false);
        add_hall_pane.setVisible(false);
        add_seats_pane.setVisible(false);
        Activepane.setVisible(true);
    }

    public void toggle_btn_style(Button btn, String add, String remove) {
        btn.getStyleClass().remove(remove);
        btn.getStyleClass().add(add);
    }

    public void updateBtnStyles(String inactiveClass, String activeClass, Button activeButton) {
        List<Button> allButtons = Arrays.asList(
                dash_btn, movies_btn, reserv_btn
        );

        for (Button button : allButtons) {
            button.getStyleClass().removeAll(
                    "nav-btn", "nav-btn-active"
            );

            if (button == activeButton) {
                button.getStyleClass().add(activeClass);
            } else {
                button.getStyleClass().add(inactiveClass);
            }
            button.applyCss();
        }
    }

    public void updateBtnStyles2(String inactiveClass, String activeClass, Button activeButton) {
        List<Button> allButtons = Arrays.asList(
                movie_list_btn, add_movie_btn, add_halls_btn, add_seats_btn
        );

        for (Button button : allButtons) {
            button.getStyleClass().removeAll(
                    "nav-btn", "nav-btn-active"
            );

            if (button == activeButton) {
                button.getStyleClass().add(activeClass);
            } else {
                button.getStyleClass().add(inactiveClass);
            }
            button.applyCss();
        }
    }

    public void switchMenu(ActionEvent event) {
        if (event.getSource() == dash_btn) {
            setPaneVisibility(dashboard_pane);
            updateBtnStyles("nav-btn", "nav-btn-active", dash_btn);
        } else if (event.getSource() == movies_btn) {
            setPaneVisibility(Movies_pane);
            updateBtnStyles("nav-btn", "nav-btn-active", movies_btn);
        } else if (event.getSource() == reserv_btn) {
            setPaneVisibility(reserv_pane);
            updateBtnStyles("nav-btn", "nav-btn-active", reserv_btn);
        }
    }

    public void switchMenu_Movies(ActionEvent event) {
        if (event.getSource() == movie_list_btn) {
            setPaneVisibility2(movies_list);
            updateBtnStyles2("nav-btn", "nav-btn-active", movie_list_btn);
        } else if (event.getSource() == add_movie_btn) {
            setPaneVisibility2(add_movie);
            updateBtnStyles2("nav-btn", "nav-btn-active", add_movie_btn);
        } else if (event.getSource() == add_halls_btn) {
            setPaneVisibility2(add_hall_pane);
            updateBtnStyles2("nav-btn", "nav-btn-active", add_halls_btn);
        } else if (event.getSource() == add_seats_btn) {
            setPaneVisibility2(add_seats_pane);
            updateBtnStyles2("nav-btn", "nav-btn-active", add_seats_btn);
        }
    }

    public void showMoviesData() {
        db_attendant db = new db_attendant();
        movieList0 = db.selectAll_Movies();

        // Map table columns to the Movies properties
        Movie_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        Movie_name_col.setCellValueFactory(new PropertyValueFactory<>("movie_name"));
        Duration_col.setCellValueFactory(new PropertyValueFactory<>("duration"));
        r_date_col.setCellValueFactory(new PropertyValueFactory<>("releaseData"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        img_col.setCellValueFactory(new PropertyValueFactory<>("img"));

        // Set the items in the table
        Movies_Table.setItems(movieList0);
    }

    public void showPendingTickets() {
        db_attendant db = new db_attendant();
        pendingTicketList0 = db.selectAll_PendingTickets();

        // Map table columns to the Movies properties
        username0_col.setCellValueFactory(new PropertyValueFactory<>("userName"));
        mov_name0_col.setCellValueFactory(new PropertyValueFactory<>("movieName"));
        seatNum0_col.setCellValueFactory(new PropertyValueFactory<>("seatNumber"));
        R_date0_col.setCellValueFactory(new PropertyValueFactory<>("reDate"));
        Reser_id0_col.setCellValueFactory(new PropertyValueFactory<>("reserId"));
        Price0_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        status0_col.setCellValueFactory(new PropertyValueFactory<>("status"));
        seatId0_col.setCellValueFactory(new PropertyValueFactory<>("seatId"));
        Reserv_date0_col.setCellValueFactory(new PropertyValueFactory<>("Reser_date"));

        // Set the items in the table
        PendingTable.setItems(pendingTicketList0);
    }

    public void selectData() {
        Movies selectedMovie = Movies_Table.getSelectionModel().getSelectedItem();
        int selectedIndex = Movies_Table.getSelectionModel().getSelectedIndex();

        if (selectedIndex < 0) { // Check if no valid selection
            return;
        }

        // Set Movie Name
        mov_name.setText(selectedMovie.getMovie_name());
        dur.setText(selectedMovie.getDuration());
        release_date.setText(String.valueOf(selectedMovie.getReleaseData()));
        price9.setText(selectedMovie.getPrice());
        // Load Movie Poster into ImageView
        try {
            String imagePath = selectedMovie.getImg();
//        System.out.println("Poster path: " + imagePath);
            if (imagePath == null || imagePath.isEmpty()) {
                this.img.setImage(null); // Clear ImageView
            } else {
                String pic = "file:" + new File(imagePath).getAbsolutePath(); // Convert to file path
                Image img = new Image(pic, 169, 156, false, true); // Load image
                img.exceptionProperty().addListener((obs, oldException, newException) -> {
                    if (newException != null) {
                        System.out.println("Failed to load image: " + newException.getMessage());
                    }
                });
                this.img_view.setImage(img);
            }
        } catch (Exception e) {
            System.out.println("Error loading poster: " + e.getMessage());
        }
    }

    public void clear(ActionEvent event) {
        mov_name.setText("");
        dur.setText("");
        release_date.setText("");
        price9.setText("");
        this.img_view.setImage(null);
    }

    public void selectReserData() {
        // Log table selection
        PendingTickets selectedTic = PendingTable.getSelectionModel().getSelectedItem();
        System.out.println("Selected PendingTickets: " + selectedTic);

        if (selectedTic == null) { // Check for null selection
            System.out.println("No item selected.");
            return;
        }

        // Log selected indices
        int selectedIndex = PendingTable.getSelectionModel().getSelectedIndex();
        System.out.println("Selected Index: " + selectedIndex);

        if (selectedIndex < 0) { // Check for invalid index
            System.out.println("Invalid selection index.");
            return;
        }

        // Update UI elements
        seat_id_8.setText(String.valueOf(selectedTic.getSeatId()));
        reser_id_8.setText(String.valueOf(selectedTic.getReserId()));

        confirm_btn_acce.setVisible(true);
        delete_btn_reser.setVisible(true);

        System.out.println("UI updated successfully.");
    }

//    confirm btn
    public void confirm(ActionEvent event) {
        db_attendant db = new db_attendant();
        db_cust db1 = new db_cust();
        db.updateStatusReserv(Integer.parseInt(reser_id_8.getText()));
        db1.updateSeatAvailability(Integer.parseInt(seat_id_8.getText()), 1);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reservation Ticket has confirmed Successfully");
        alert.setHeaderText("");
        alert.setContentText("Reservation Ticket has confirmed Successfully.");
        alert.showAndWait();
        confirm_btn_acce.setVisible(false);
        delete_btn_reser.setVisible(false);
        showPendingTickets();
    }
//    delete btn
    public void delete(ActionEvent event) {
        db_attendant db = new db_attendant();
        db_cust db1 = new db_cust();
        db.deleteReservation(Integer.parseInt(reser_id_8.getText()));
        db1.updateSeatAvailability(Integer.parseInt(seat_id_8.getText()), 1);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reservation Ticket has deleted Successfully");
        alert.setHeaderText("");
        alert.setContentText("Reservation Ticket has deleted Successfully.");
        alert.showAndWait();
        confirm_btn_acce.setVisible(false);
        delete_btn_reser.setVisible(false);
        showPendingTickets();
    }

    // product search
    public void MovieSearch() {
        db_cust db = new db_cust();
        String s = search_bar.getText();
        movieList0 = db.MovieSearch(s);

        // Map table columns to the Movies properties
        Movie_id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        Movie_name_col.setCellValueFactory(new PropertyValueFactory<>("movie_name"));
        Duration_col.setCellValueFactory(new PropertyValueFactory<>("duration"));
        r_date_col.setCellValueFactory(new PropertyValueFactory<>("releaseData"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        img_col.setCellValueFactory(new PropertyValueFactory<>("img"));

        // Set the items in the table
        Movies_Table.setItems(movieList0);
    }

    @Override
    public void update(ObservableList<PendingTickets> updatedList) {
        // Update the UI or perform necessary actions with the updated list
        showPendingTickets(updatedList);
    }

    public void showPendingTickets(ObservableList<PendingTickets> updatedList) {
        // Update the TableView or other UI elements with the new data
        PendingTable.setItems(updatedList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showMoviesData();
        showPendingTickets();
        EventBus.getInstance().registerObserver(this);
        printDuration(duration);
        printPrice();
        printHalls();
        printMovies();
        printSeatNumber();
        welcome_title.setText("Welcome , " + SigninController1.username);
        search_bar.setText("");
    }

}
