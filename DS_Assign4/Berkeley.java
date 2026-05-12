import java.util.*;

public class Berkeley {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // STEP 1: Set Master Clock Time
        int masterH = 10, masterM = 0, masterS = 0;

        int masterTime = masterH * 3600 + masterM * 60 + masterS;

        System.out.println("Master Time : "
                + masterH + ":" + masterM + ":" + masterS);

        // STEP 2: Enter Number of Nodes
        System.out.print("\nEnter number of nodes: ");
        int n = sc.nextInt();

        int[] nodeTime = new int[n];
        int[] diff = new int[n];

        int sum = 0;

        // STEP 3: Enter Time for Each Node
        for (int i = 0; i < n; i++) {

            System.out.print("Enter Node " + (i + 1) + " time (h m s): ");

            int h = sc.nextInt();
            int m = sc.nextInt();
            int s = sc.nextInt();

            // Convert into seconds
            nodeTime[i] = h * 3600 + m * 60 + s;

            // Find difference with master clock
            diff[i] = nodeTime[i] - masterTime;

            sum = sum + diff[i];
        }

        // STEP 4: Calculate Average Time Difference
        int avg = sum / (n + 1);

        System.out.println("\nAverage correction: " + avg + " seconds");

        // STEP 5: Correct Master Clock
        masterTime = masterTime + avg;

        System.out.println("\nSynchronized Times:");

        System.out.println("Master -> "
                + (masterTime / 3600) + ":"
                + ((masterTime % 3600) / 60) + ":"
                + (masterTime % 60));

        // STEP 6: Correct Node Clocks
        for (int i = 0; i < n; i++) {

            int correctedTime = nodeTime[i] + (avg - diff[i]);

            System.out.println("Node " + (i + 1) + " -> "
                    + (correctedTime / 3600) + ":"
                    + ((correctedTime % 3600) / 60) + ":"
                    + (correctedTime % 60));
        }

        // STEP 7: Close Scanner
        sc.close();
    }
}

// javac Berkeley.java
// java Berkeley
// Master Time : 10:0:0

// Enter number of nodes: 3
// Enter Node 1 time (h m s): 10 1 0
// Enter Node 2 time (h m s): 9 59 0
// Enter Node 3 time (h m s): 10 0 30

// Average correction: 7 seconds

// Synchronized Times:
// Master -> 10:0:7
// Node 1 -> 10:0:7
// Node 2 -> 10:0:7
// Node 3 -> 10:0:7