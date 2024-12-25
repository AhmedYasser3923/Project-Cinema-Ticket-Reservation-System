package cinema.Server;

import cinema.Ticket_attendant.PendingTickets;
import cinema.Ticket_attendant.db_attendant;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ServerFXMLController implements Initializable , EventBus.Observer {

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
    private TextArea outputTextarea;

    @FXML
    public Button maximize;

    @FXML
    public Button minimize;

    @FXML
    private TableView<PendingTickets> PendingTable;
    private ObservableList<PendingTickets> pendingTicketList0;
  //    close button
    public void close() {
        System.exit(0);
    }
    public void showPendingTickets() {
        db_attendant db = new db_attendant();
        pendingTicketList0 = db.selectAllTickets();

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
        Thread th = new Thread() {
            @Override
            public void run() {
                ReservationServer ser = new ReservationServer(outputTextarea);

            }

        };
        th.start();
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
        showPendingTickets();
        EventBus.getInstance().registerObserver(this);
    }

}
