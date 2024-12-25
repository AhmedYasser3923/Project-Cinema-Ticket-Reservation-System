package cinema.Customer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class SigninController implements Initializable {

    @FXML
    public Button minimize;

    @FXML
    private Button create_acc_btn;

    @FXML
    private Button login_btn;

    @FXML
    private AnchorPane login_pane;

    @FXML
    private PasswordField password_login;

    @FXML
    private PasswordField password_signup;

    @FXML
    private PasswordField repassword_signup;

    @FXML
    private Button signin_btn;

    @FXML
    private Button signup_btn;

    @FXML
    private AnchorPane signup_pane;

    @FXML
    private Label title;

    @FXML
    private TextField username_login;

    @FXML
    private TextField username_signup;

    @FXML
    private AnchorPane welcome_login_pane;

    @FXML
    private AnchorPane welcome_signup_pane;
    public static String username;
        @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
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
    //    close button
    public void close() {
        System.exit(0);
    }

    public void Switch_Customer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CustomerFXML.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerFXML.fxml"));
        Parent root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1500, 777);
        stage_movement(stage, root);
        cinema.Cinema.minimize(stage, loader);
        stage.setScene(scene);
        stage.show();
    }
//    Login
    public void login(ActionEvent event) throws IOException {
        Customer c = new Customer(username_login.getText(), password_login.getText(), 1);
        db_cust db = new db_cust();
        if(Customer.send_mess_to_server_help("login,"+username_login.getText()+","+password_login.getText()).equals("done")){
        
        if (db.login(username_login.getText() , password_login.getText())) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Login successful");
            alert.setHeaderText("");
            alert.setContentText("You have logged in successfully");
            alert.showAndWait();
            username = username_login.getText();
            Switch_Customer(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("");
            alert.setContentText("Incorrect Username or password");
            alert.showAndWait();
        }
          }
        reset();
    }

//    SignUp
    public void signUp(ActionEvent event) {
        if (username_signup.getText().isEmpty() || password_signup.getText().isEmpty() || repassword_signup.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Blank input");
            alert.setHeaderText("");
            alert.setContentText("All fields are required. Please fill in all fields.");
            alert.showAndWait();
            return;
        } else if (password_signup.getText().equals(repassword_signup.getText())) {
            Customer c = new Customer(username_signup.getText(), password_signup.getText(), 1);
            db_cust db = new db_cust();
            db.register(c);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Register successful");
            alert.setHeaderText("");
            alert.setContentText("You have registered successfully");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Re-Type Password");
            alert.setHeaderText("");
            alert.setContentText("Password and Re-Type Pasword should be the same");
            alert.showAndWait();
        }
        reset();
    }

//    switch 
    public void Switch_login_signup(ActionEvent event) {
        if (event.getSource() == create_acc_btn) {
            login_pane.setVisible(false);
            welcome_login_pane.setVisible(false);
            signup_pane.setVisible(true);
            welcome_signup_pane.setVisible(true);
            title.setText("SignUp Customer");
        } else if (event.getSource() == signin_btn) {
            signup_pane.setVisible(false);
            welcome_signup_pane.setVisible(false);
            login_pane.setVisible(true);
            welcome_login_pane.setVisible(true);
            title.setText("Login Customer");
        }
        reset();
    }
//        Reset

    public void reset() {
        username_login.setText("");
        username_signup.setText("");
        password_login.setText("");
        password_signup.setText("");
        repassword_signup.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Thread th = new Thread(){
            @Override
            public void run() {
      
      Customer c = new Customer();
            }
        
        };
        th.start();
    }

}
