package cinema.Server;

import cinema.Customer.db_cust;
import cinema.Ticket_attendant.db_attendant;
import java.io.*;
import java.net.*;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReservationServer {

    private int port = 5000;
    private static TextArea output0;
    private ExecutorService threadPool = Executors.newCachedThreadPool();
    private db_cust db = new db_cust();
    private db_attendant db1 = new db_attendant();

public ReservationServer(TextArea output) {
    if (output != null) {
        output0 = output; // Initialize output0 with the passed TextArea
        output.appendText(output0.getText()); // Append the existing text (if any)
    } else {
        System.out.println("Error: TextArea is null.");
    }
    create_server_connection(port); // Proceed to create the server connection
}


    public void create_server_connection(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for clients...");
            Platform.runLater(() -> output0.appendText("Server started. Waiting for clients...\n"));

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                Platform.runLater(() -> output0.appendText("Client connected: " + clientSocket.getInetAddress() + "\n"));
                threadPool.submit(() -> handleClient(clientSocket));  // Handle client connections in a separate thread
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
            Platform.runLater(() -> output0.appendText("Server error: " + e.getMessage() + "\n"));
        }
    }

 public void handleClient(Socket clientSocket) {
    try (DataInputStream in = new DataInputStream(clientSocket.getInputStream());
         DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

        String message;
        while ((message = in.readUTF()) != null) {
            // Print message to the console (for debugging purposes)
            System.out.println("Message from client: " + message);
            
            // Simply append message to output (without using Platform.runLater)
            if (output0 != null) {
                output0.appendText("Message from client: " + message + "\n");
            }

            // Split the message into parts
            String[] mes = message.split(",");
            if (mes.length > 0) {
                // Handle different types of messages based on the first part (command)
                switch (mes[0]) {
                    case "login":
                    case "login2":
                        handleLogin(mes, out, mes[0].equals("login") ? db : db1);
                        break;

                    case "select":
                    case "insert":
                    case "cancel":
                    case "update":
                        out.writeUTF("done");
                        if (output0 != null) {
                            output0.appendText("done\n");
                        }
                        break;

                    default:
                        System.out.println("Unknown message type: " + mes[0]);
                        break;
                }
            }
        }
    } catch (IOException e) {
        // Log client disconnection
        System.out.println("Client disconnected: " + clientSocket.getInetAddress());
        if (output0 != null) {
            output0.appendText("Client disconnected: " + clientSocket.getInetAddress() + "\n");
        }
    }
}


    // Handle login requests for both customer and attendant
    public void handleLogin(String[] mes, DataOutputStream out, Object dbInstance) {
        boolean loginSuccess = false;
        if (dbInstance instanceof db_cust) {
            loginSuccess = ((db_cust) dbInstance).login(mes[1], mes[2]);
        } else if (dbInstance instanceof db_attendant) {
            loginSuccess = ((db_attendant) dbInstance).login(mes[1], mes[2]);
        }

        try {
            if (loginSuccess) {
                out.writeUTF("done");
                output0.appendText("done\n");
            } else {
                out.writeUTF("Not done");
                output0.appendText("Not done\n");
            }
        } catch (IOException e) {
            System.out.println("Error sending response: " + e.getMessage());
            output0.appendText("Error sending response: " + e.getMessage() + "\n");
        }
    }
}
