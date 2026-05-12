import java.util.*;

public class Ring {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // STEP 1: Enter number of processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        int id[] = new int[n];
        boolean alive[] = new boolean[n];

        // STEP 2: Enter process IDs
        for (int i = 0; i < n; i++) {

            System.out.print("Enter ID for Process " + i + ": ");
            id[i] = sc.nextInt();

            alive[i] = true;
        }

        // STEP 3: Find initial coordinator
        int coordinator = id[0];

        for (int i = 1; i < n; i++) {

            if (id[i] > coordinator) {
                coordinator = id[i];
            }
        }

        System.out.println("Initial Coordinator: Process "
                + coordinator);

        while (true) {

            // STEP 4: Display Menu
            System.out.println("\n1. Start Election");
            System.out.println("2. Crash Process");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            // STEP 5: Start Election
            if (ch == 1) {

                System.out.print("Enter initiator index: ");
                int init = sc.nextInt();

                if (alive[init] == false) {

                    System.out.println("Process is dead.");

                } else {

                    System.out.println("Election started by Process "
                            + id[init]);

                    int max = id[init];

                    int current = (init + 1) % n;

                    // Pass message around the ring
                    while (current != init) {

                        if (alive[current]) {

                            System.out.println("Message passed to Process "
                                    + id[current]);

                            if (id[current] > max) {
                                max = id[current];
                            }

                        } else {

                            System.out.println("Process "
                                    + id[current] + " is dead");
                        }

                        current = (current + 1) % n;
                    }

                    coordinator = max;

                    System.out.println("New Coordinator: Process "
                            + coordinator);
                }
            }

            // STEP 6: Crash a process
            else if (ch == 2) {

                System.out.print("Enter process index to crash: ");
                int p = sc.nextInt();

                alive[p] = false;

                System.out.println("Process " + id[p] + " crashed.");

                if (id[p] == coordinator) {

                    System.out.println("Coordinator crashed.");
                    System.out.println("Start election again.");
                }
            }

            // STEP 7: Exit
            else if (ch == 3) {
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