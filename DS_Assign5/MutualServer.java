import java.io.*;
import java.net.*;

public class MutualServer {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(7000);
        System.out.println("Server started on port 7000");

        while (true) {
            Socket client = ss.accept();

            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream()));

                    String msg;

                    while ((msg = in.readLine()) != null) {
                        System.out.println("Message received: " + msg);
                    }
                } catch (Exception e) {
                    System.out.println("Client disconnected");
                }
            }).start();
        }
    }
}


// javac MutualServer.java ClientOne.java ClientTwo.java

// Terminal 1
// java MutualServer

// Terminal 2
// java ClientOne

// Terminal 3
// java ClientTwo