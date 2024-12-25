package cinema.Customer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.System.out;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author DELL
 */
public class Customer {

    private String userName;
    private String Password;
    private int role_id;
    private Socket socket = null;
    private ServerSocket server = null;
    private static DataInputStream in = null;
    private int port = 5000;
    private String address = "localhost";
    private static DataOutputStream out = null;

    public Customer(String userName, String Password, int role_id) {
        this.userName = userName;
        this.Password = Password;
        this.role_id = role_id;

    }

    public Customer(String userName, String Password) {
        this.userName = userName;
        this.Password = Password;
      

    }

    public Customer() {
          create_client_con();
    }

    public void create_client_con() {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to the server!");
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }

    public static String send_mess_to_server_help(String message) {
        String response = "";
        try {
            out.writeUTF(message);
            System.out.println(message);
            response = in.readUTF();
            System.out.println("Server response: " + response);
        } catch (IOException e) {
            System.out.println("Communication error: " + e.getMessage());
        }
        return response;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

}
