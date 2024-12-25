package cinema;

import cinema.Customer.CustomerFXMLController;
import cinema.Customer.SigninController;
import cinema.Ticket_attendant.SigninController1 ;
import cinema.Ticket_attendant.Ticket_AttendantFXMLController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author DELL
 */
public class Cinema extends Application {

    private static double x = 0;
    private static double y = 0;
//
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/cinema/Server/ServerFXML.fxml"));
        Parent root = loader.load();
        Stage stage2 = new Stage();
        Stage stage3 = new Stage();
        Scene scene = new Scene(root);
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/cinema/Customer/Signin.fxml"));
        Parent root2 = loader2.load();
        Scene scene2 = new Scene(root2);
        FXMLLoader loader3 = new FXMLLoader(getClass().getResource("/cinema/Ticket_attendant/Signin.fxml"));
        Parent root3 = loader3.load();
        Scene scene3 = new Scene(root3);
        stage_movement(stage, root);
        stage_movement(stage2, root2);
        stage_movement(stage3, root3);
        Image logo = new Image(getClass().getResourceAsStream("—Pngtree—cinema vector illustration_3704537.png"));
        stage.getIcons().add(logo);
        stage.setTitle("Customer Cinema");
        stage.initStyle(StageStyle.TRANSPARENT);
        Image logo2 = new Image(getClass().getResourceAsStream("—Pngtree—movie ticket retro gold effect_6840801.png"));
        stage2.getIcons().add(logo2);
        stage2.setTitle("Ticket Attendant");
        stage2.initStyle(StageStyle.TRANSPARENT);
        stage3.initStyle(StageStyle.TRANSPARENT);
        minimize(stage, loader);
        minimize(stage2, loader2);
        stage.setScene(scene);
        stage.show();
        stage2.setScene(scene2);    
        stage2.show();
        stage3.setScene(scene3);    
        stage3.show();
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

public static void minimize(Stage stage, FXMLLoader loader) {
    // Get the controller associated with the loaded FXML
    Object controller = loader.getController();

    // Check which type of controller you are dealing with and cast it accordingly
    if (controller instanceof SigninController) {
        SigninController contr = (SigninController) controller;
        if (contr.minimize != null) {
            contr.minimize.setOnAction(event -> {
                Stage currentStage = (Stage) contr.minimize.getScene().getWindow();
                currentStage.setIconified(true);
            });
        }
    } else if (controller instanceof SigninController1) {
        SigninController1 contr1 = (SigninController1) controller;
        if (contr1.minimize != null) {
            contr1.minimize.setOnAction(event -> {
                Stage currentStage = (Stage) contr1.minimize.getScene().getWindow();
                currentStage.setIconified(true);
            });
        }
    }
    else if (controller instanceof CustomerFXMLController) {
        CustomerFXMLController contr3 = (CustomerFXMLController) controller;
        if (contr3.minimize != null) {
            contr3.minimize.setOnAction(event -> {
                Stage currentStage = (Stage) contr3.minimize.getScene().getWindow();
                currentStage.setIconified(true);
            });
        }
    } else if (controller instanceof Ticket_AttendantFXMLController) {
        Ticket_AttendantFXMLController contr4 = (Ticket_AttendantFXMLController) controller;
        if (contr4.minimize != null) {
            contr4.minimize.setOnAction(event -> {
                Stage currentStage = (Stage) contr4.minimize.getScene().getWindow();
                currentStage.setIconified(true);
            });
        }
    }
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
