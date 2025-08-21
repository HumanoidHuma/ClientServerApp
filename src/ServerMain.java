import java.io.*;
import java.net.*;

class ServerMain {
    public static void main(String[] args) {
        int port = 8090;
        InetAddress host = null;
        try {
            host = InetAddress.getByName("netology.homework");
        } catch (UnknownHostException e) {
            System.out.println(e.getMessage());
        }

        try (ServerSocket serverSocket = new ServerSocket(port, 50, host)) {
            System.out.println("Сервер запущен на порту: " + port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                processingClient(clientSocket);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    // Обработка одного клиента
    public static void processingClient(Socket clientSocket) {
        try (clientSocket;
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            // stap 1
            out.println("Write your name");
            String name = in.readLine();

            // stap 2
            out.println("Are you child? (yes/no)");
            String answer = in.readLine();

            // stap 3
            if ("yes".equals(answer)) {
                out.println("Welcome to the kids area, " + name + "(PORT: " + clientSocket.getPort() + ")! Let's play!");
            } else {
                out.println("Welcome to the adult zone, " + name  + "(PORT: " + clientSocket.getPort() + ") Have a good rest, or a good working day!");
            }
            System.out.println("User port: " + clientSocket.getPort());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}