import java.util.*;

public class Bully {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // STEP 1: Enter number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        boolean alive[] = new boolean[n + 1];

        // STEP 2: Initially all processes are alive
        for (int i = 1; i <= n; i++) {
            alive[i] = true;
        }

        // Highest process becomes coordinator
        int coordinator = n;

        while (true) {

            // STEP 3: Display Menu
            System.out.println("\n1. Crash");
            System.out.println("2. Recover");
            System.out.println("3. Display");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            // STEP 4: Crash a process
            if (ch == 1) {

                System.out.print("Enter process to crash: ");
                int p = sc.nextInt();

                if (alive[p] == false) {
                    System.out.println("Process already crashed.");
                } else {

                    alive[p] = false;
                    System.out.println("Process " + p + " crashed.");

                    // If coordinator crashes
                    if (p == coordinator) {

                        System.out.println("Election started...");

                        // Find highest alive process
                        for (int i = n; i >= 1; i--) {

                            if (alive[i]) {
                                coordinator = i;
                                break;
                            }
                        }

                        System.out.println("New Coordinator is Process "
                                + coordinator);
                    }
                }
            }

            // STEP 5: Recover a process
            else if (ch == 2) {

                System.out.print("Enter process to recover: ");
                int p = sc.nextInt();

                if (alive[p] == true) {
                    System.out.println("Process already alive.");
                } else {

                    alive[p] = true;

                    System.out.println("Process " + p + " recovered.");

                    // Recovered higher process becomes coordinator
                    if (p > coordinator) {
                        coordinator = p;

                        System.out.println("Process " + p
                                + " becomes new Coordinator.");
                    }
                }
            }

            // STEP 6: Display alive processes
            else if (ch == 3) {

                System.out.print("Alive Processes: ");

                for (int i = 1; i <= n; i++) {

                    if (alive[i]) {
                        System.out.print(i + " ");
                    }
                }

                System.out.println("\nCoordinator: Process "
                        + coordinator);
            }

            // STEP 7: Exit
            else if (ch == 4) {
                break;
            }

            else {
                System.out.println("Invalid choice.");
            }
        }

        // STEP 8: Close scanner
        sc.close();
    }
}

// javac Bully.java
// java Bully
// Enter number of processes: 5

// 1. Crash
// 2. Recover
// 3. Display
// 4. Exit
// Enter choice: 1
// Enter process to crash: 5
// Process 5 crashed.
// Election started...
// New Coordinator is Process 4

// 1. Crash
// 2. Recover
// 3. Display
// 4. Exit
// Enter choice: 2
// Enter process to recover: 5
// Process 5 recovered.
// Process 5 becomes new Coordinator.
