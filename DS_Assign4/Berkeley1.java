import java.util.*;

public class Berkeley1 {

    // ── HELPER FUNCTION 1 ──
    static int toSeconds(int h, int m, int s) {
        return h * 3600 + m * 60 + s;
    }

    // ── HELPER FUNCTION 2 ──
    static String toHMS(int total) {
        total = ((total % 86400) + 86400) % 86400;
        int h = total / 3600;
        int m = (total % 3600) / 60;
        int s = total % 60;
        return h + ":" + m + ":" + s;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //Step 1: Get master clock time
        Calendar cal = Calendar.getInstance();
        int masterTime = toSeconds(
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                cal.get(Calendar.SECOND));

        System.out.println("Master time: " + toHMS(masterTime));

        //Step 2: Get number of nodes and their times
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();

        int[] nodeTime = new int[n];
        int[] diff = new int[n];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            System.out.println("Enter time for Node " + (i + 1) + " (h m s): ");
            nodeTime[i] = toSeconds(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        //Step 3: Calculate differences 
        System.out.println("\n--- Time Differences ---");

        for (int i = 0; i < n; i++) {
            diff[i] = nodeTime[i] - masterTime;
            sum += diff[i];
            System.out.println("Node " + (i + 1) + " difference: " + diff[i] + "s");
        }

        //Step 4: Calculate average correction
        int avg = sum / (n + 1);
        System.out.println("\nAverage correction: " + avg + "s");

        //Step 5: Send individual corrections 
        System.out.println("\n--- Corrections ---");
        System.out.println("Master correction: " + avg + "s");

        for (int i = 0; i < n; i++) {
            int correction = avg - diff[i];
            System.out.println("Node " + (i + 1) + " correction: " + correction + "s");
        }

        //Step 6: Display synchronized times
        System.out.println("\n--- Synchronized Clocks ---");
        System.out.println("Master --> " + toHMS(masterTime + avg));

        for (int i = 0; i < n; i++) {
            int correction = avg - diff[i];
            System.out.println("Node " + (i + 1) + " --> " + toHMS(nodeTime[i] + correction));
        }

        sc.close();
    }
}