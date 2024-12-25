package cinema.Customer;

import cinema.Server.EventBus;
import cinema.Server.EventBus2;
import cinema.Ticket_attendant.Movies;
import cinema.Ticket_attendant.PendingTickets;
import cinema.Ticket_attendant.Seats;
import cinema.Ticket_attendant.TicketAttendant;
import cinema.Ticket_attendant.Ticket_AttendantFXMLController;
import cinema.Ticket_attendant.db_attendant;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.*;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class CustomerFXMLController implements Initializable , EventBus2.Observer{
    
    @FXML
    private TableColumn<Movies, String> Duration_col;
    
    @FXML
    private TableColumn<Movies, Integer> Movie_id_col;
    
    @FXML
    private TableColumn<Movies, String> Movie_name_col;
    
    @FXML
    private TableView<Movies> Movies_Table;
    
    @FXML
    private Button clear_btn;
    
    @FXML
    private TableColumn<Movies, String> img_col;
    
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
    private TextField duration_mov;
    
    @FXML
    private TextField movie_id_field;
    
    @FXML
    private TextField r_date_mov_f;
    
    @FXML
    private TextField price_f;
    
    @FXML
    private Button movies_btn;
    
    @FXML
    private TableColumn<Movies, String> price_col;
    
    @FXML
    private TableColumn<Movies, LocalDate> r_date_col;
    
    @FXML
    private Button reservation_btn;
    
    @FXML
    private Button search_btn;
    
    @FXML
    private Button update_data;
    
    @FXML
    private Label welcome_title;
    
    @FXML
    private AnchorPane Movies_pane;
    
    @FXML
    private AnchorPane Reserve_pane;
    @FXML
    private AnchorPane Reservation_pane;
    
    @FXML
    private TextField userName5;
    @FXML
    private TextField userid5;
    @FXML
    private TextField movie_name5;
    @FXML
    private TextField movie_id5;
    @FXML
    private TextField r_date5;
    @FXML
    private TextField dur5;
    @FXML
    private TextField price5;
    @FXML
    private TextField search_bar;
    @FXML
    private Button reserve5;
    @FXML
    private Button reserve_info;
    @FXML
    private Button reserve_btn;
    @FXML
    private ComboBox<String> hall5;
    @FXML
    private ComboBox<String> seat5;
    @FXML
    private Label imgLab;
    @FXML
    private ImageView img5;
    
       private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    // Method to establish connection to the server
    private void connectToServer() throws IOException {
        socket = new Socket("localhost", 12345);  // Connect to server at localhost, port 12345
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    // Method to close the connection to the server
    private void closeConnection() throws IOException {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
        //    close button
    public void close() {
        System.exit(0);
    }  
    private ObservableList<Movies> movieList0;
        private static double x = 0;
    private static double y = 0;
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
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
        public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Signin.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
        Parent root = loader.load();
        SigninController.username = "";
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 900, 500);
        stage_movement(stage, root);
        cinema.Cinema.minimize(stage, loader);
        stage.setScene(scene);
        stage.show();
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
    
    public void selectData() {
        Movies selectedMovie = Movies_Table.getSelectionModel().getSelectedItem();
        int selectedIndex = Movies_Table.getSelectionModel().getSelectedIndex();
        
        if (selectedIndex < 0) { // Check if no valid selection
            return;
        }

        // Set Movie ID
        movie_id_field.setText(String.valueOf(selectedMovie.getId()));

        // Set Movie Name
        mov_name.setText(selectedMovie.getMovie_name());

        // Set Movie Duration into TextField
        try {
            duration_mov.setText(selectedMovie.getDuration()); // Assuming duration is a String
        } catch (Exception e) {
            System.out.println("Error setting duration: " + e.getMessage());
        }

        // Set Release Date into TextField
        try {
            r_date_mov_f.setText(selectedMovie.getReleaseData().toString()); // Assuming releaseDate is a LocalDate
        } catch (Exception e) {
            System.out.println("Error setting release date: " + e.getMessage());
        }

        // Set Ticket Price into TextField
        try {
            price_f.setText(String.valueOf(selectedMovie.getPrice())); // Assuming price is a double or integer
        } catch (Exception e) {
            System.out.println("Error setting price: " + e.getMessage());
        }

        // Load Movie Poster into ImageView
        try {
            String imagePath = selectedMovie.getImg();
//        System.out.println("Poster path: " + imagePath);
            if (imagePath == null || imagePath.isEmpty()) {
                this.img_view.setImage(null); // Clear ImageView
            } else {
                String pic = "file:" + new File(imagePath).getAbsolutePath(); // Convert to file path
                imgLab.setText(pic);
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
        reserve_btn.setVisible(true);
    }
    
    public void clear() {
        movie_id_field.setText("");
        mov_name.setText("");
        duration_mov.setText("");
        r_date_mov_f.setText("");
        price_f.setText("");
        imgLab.setText("");
        img_view.setImage(null);
        reserve_btn.setVisible(false);
        seat5.getSelectionModel().clearSelection();
        hall5.getSelectionModel().clearSelection();
    }
    
    public void clear_btn(ActionEvent event) {
        clear();
    }
    
    public void setPaneVisibility(AnchorPane Activepane) {
        Movies_pane.setVisible(false);
        Reserve_pane.setVisible(false);
        Reservation_pane.setVisible(false);
        Activepane.setVisible(true);
    }
    
    public void updateBtnStyles(String inactiveClass, String activeClass, Button activeButton) {
        List<Button> allButtons = Arrays.asList(
                movies_btn, reservation_btn, reserve_info
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
    
    public void go_To_ReserveInfo(ActionEvent event) {
        setPaneVisibility(Reserve_pane);
        updateBtnStyles("nav-btn", "nav-btn-active", reserve_info);
        userName5.setText(SigninController.username);
        db_cust db = new db_cust();
        userid5.setText(String.valueOf(db.getuserid(SigninController.username)));
        movie_name5.setText(mov_name.getText());
        movie_id5.setText(movie_id_field.getText());
        dur5.setText(duration_mov.getText());
        r_date5.setText(r_date_mov_f.getText());
        price5.setText(price_f.getText());
        reserve_info.setVisible(true);
        Image i = new Image(imgLab.getText(), 284, 174, false, true);
        img5.setImage(i);
        int mvid = Integer.parseInt(movie_id_field.getText());
        ObservableList<Seats> seatsList = db.selectSeats(mvid);
        ObservableList<String> seatNumbers = FXCollections.observableArrayList();
        for (Seats seat : seatsList) {
            seatNumbers.add(String.valueOf(seat.getSeatNumber()));
        }        
        seat5.setItems(seatNumbers);
        
        ObservableList<Seats> hallList = db.selectHalls(mvid);
        ObservableList<String> hallNames = FXCollections.observableArrayList();
        for (Seats seat : hallList) {
            hallNames.add(seat.getHall_name());
        }        
        hall5.setItems(hallNames);
    }

    public void switch_btn_menu(ActionEvent event) {
        if (event.getSource() == movies_btn) {
            setPaneVisibility(Movies_pane);
            updateBtnStyles("nav-btn", "nav-btn-active", movies_btn);
        } else if (event.getSource() == reservation_btn) {
            setPaneVisibility(Reservation_pane);
            updateBtnStyles("nav-btn", "nav-btn-active", reservation_btn);
        }
        reserve_info.setVisible(false);
        
    }

public void Reserve_Ticket(ActionEvent event) {
    if (seat5.getSelectionModel().isEmpty() || hall5.getSelectionModel().isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Blank input");
        alert.setHeaderText("");
        alert.setContentText("All fields are required. Please fill in all fields.");
        alert.showAndWait();
    } else {
        try {
            db_cust db = new db_cust();
            int userid = Integer.parseInt(userid5.getText());
            int movid = Integer.parseInt(movie_id5.getText());
            int seatid = db.getseatId(Integer.parseInt(seat5.getSelectionModel().getSelectedItem()));
            int hallid = db.getHallId(hall5.getSelectionModel().getSelectedItem());

            // Create a reservation object
            Reservations r = new Reservations(userid, seatid, hallid, movid);
            db.Reserve(r); // Reserve the ticket in the database
            db.updateSeatAvailability(seatid,0); // Update seat availability
           db_attendant db2 = new db_attendant();
            // Notify observers about the updated list of pending tickets
            ObservableList<PendingTickets> updatedList = db2.selectAll_PendingTickets(); // Fetch the updated list
            EventBus.getInstance().notifyObservers(updatedList); // Notify observers
            // Notify observers about the updated list of pending tickets
            ObservableList<PendingTickets> updatedList2 = db2.selectAllTickets(); // Fetch the updated list
            EventBus.getInstance().notifyObservers(updatedList2); // Notify observers

            // Display success message
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Reserved Ticket Successfully");
            alert.setHeaderText("");
            alert.setContentText("Reserved Ticket Successfully.");
            alert.showAndWait();

            // Reset UI and clear input fields
            setPaneVisibility(Movies_pane);
            updateBtnStyles("nav-btn", "nav-btn-active", movies_btn);
            reserve_info.setVisible(false);
            clear();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Reservation Failed");
            alert.setContentText("An error occurred during the reservation process. Please try again.");
            alert.showAndWait();
        }
    }
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
    public void update(ObservableList<Movies> updatedList) {
        // Update the UI or perform necessary actions with the updated list
        showMovies(updatedList);
    }

    public void showMovies(ObservableList<Movies> updatedList) {
        // Update the TableView or other UI elements with the new data
        Movies_Table.setItems(updatedList);
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showMoviesData();
                EventBus2.getInstance().registerObserver(this);
        welcome_title.setText("Welcome , " + SigninController.username);
        search_bar.setText("");
    }
    
}
