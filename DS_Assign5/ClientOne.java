import java.io.*;
import java.net.*;

public class ClientOne {
    public static void main(String[] args) throws IOException {
        Socket serverSocket = new Socket("localhost", 7000);
        PrintWriter server = new PrintWriter(serverSocket.getOutputStream(), true);

        ServerSocket ss = new ServerSocket(7001);
        Socket ring = ss.accept();

        BufferedReader fromTwo = new BufferedReader(
                new InputStreamReader(ring.getInputStream()));
        PrintWriter toTwo = new PrintWriter(ring.getOutputStream(), true);

        BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in));

        String token = "Token";

        while (true) {
            if (token != null && token.equalsIgnoreCase("Token")) {
                System.out.print("Send data? (yes/no): ");
                String choice = console.readLine();

                if (choice.equalsIgnoreCase("yes")) {
                    System.out.print("Enter data: ");
                    String data = console.readLine();
                    server.println("ClientOne: " + data);
                }

                toTwo.println("Token");
                System.out.println("Token passed to ClientTwo");
            }

            System.out.println("Waiting for token...");
            token = fromTwo.readLine();
        }
    }
}