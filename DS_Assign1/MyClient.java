import java.util.Scanner;
import java.rmi.*;

public class MyClient {
    public static void main(String args[]) {
        try {
            Concat stub = (Concat) Naming.lookup ("localhost");
            Scanner input = new Scanner(System.in);
            String x, y;
            System.out.println("Enter String 1:");
            x = input.nextLine();
            System.out.println("Enter String 2:");
            y = input.nextLine();
            System.out.println("Concatenated Result: " + stub.concat(x, y));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}