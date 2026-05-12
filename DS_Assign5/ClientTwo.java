import java.io.*;
import java.net.*;

public class ClientTwo {
    public static void main(String[] args) throws IOException {
        Socket serverSocket = new Socket("localhost", 7000);
        PrintWriter server = new PrintWriter(serverSocket.getOutputStream(), true);

        Socket ring = new Socket("localhost", 7001);

        BufferedReader fromOne = new BufferedReader(
                new InputStreamReader(ring.getInputStream()));
        PrintWriter toOne = new PrintWriter(ring.getOutputStream(), true);

        BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in));

        while (true) {
            System.out.println("Waiting for token...");
            String token = fromOne.readLine();

            if (token != null && token.equalsIgnoreCase("Token")) {
                System.out.print("Send data? (yes/no): ");
                String choice = console.readLine();

                if (choice.equalsIgnoreCase("yes")) {
                    System.out.print("Enter data: ");
                    String data = console.readLine();
                    server.println("ClientTwo: " + data);
                }

                toOne.println("Token");
                System.out.println("Token passed to ClientOne");
            }
        }
    }
}