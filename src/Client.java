import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        int port = 8090;
        InetAddress host = null;
        try {
            host = InetAddress.getByName("netology.homework");
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }


        try (Socket socket = new Socket(host, port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {
            String serverMessage;
            while ((serverMessage = in.readLine()) != null) {
                System.out.println("Server: " + serverMessage);

                if (serverMessage.contains("Welcome")) {
                    break;
                }

                String userInput = console.readLine();
                out.println(userInput);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}