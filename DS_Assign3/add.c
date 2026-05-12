#include <stdio.h>
#include <mpi.h>

int main(int argc, char *argv[]) {
    int rank, size;
    int N = 20;
    int num[20], local[20], sums[20];
    
    //Step 1: Start MPI environment and get rank and size
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);

    //Step 2: Safety check for divisibility of N by size
    if (N % size != 0) {
        if (rank == 0) {
            printf("N must be divisible by number of processors\n");
        }
        MPI_Finalize();
        return 0;
    }

    int chunk = N / size;

    //Step 3: Only Root 0 fills the array
    if (rank == 0) {
        for (int i = 0; i < N; i++) {
            num[i] = i + 1;
        }
    }

    //Step 4 : Distribute the chunks to all processors
    MPI_Scatter ( num, chunk, MPI_INT,
                 local, chunk, MPI_INT,
                 0, MPI_COMM_WORLD);

    //Step 5: Each processor sums its local chunk (in parallel)
    int local_sum = 0;
    for (int i = 0; i < chunk; i++) {
        local_sum += local[i];
    }

    //Step 6: Send local sums back to root / Rank 0
    MPI_Gather(&local_sum, 1, MPI_INT,
               sums, 1, MPI_INT,
               0, MPI_COMM_WORLD);

    //Step 7: Rank 0 prints the results and computes the final sum
    if (rank == 0) {
        int total = 0;

        printf("Intermediate sums: \n");
        for (int i = 0; i < size; i++) {
            printf("local sum at rank %d is %d\n", i, sums[i]);
            total += sums[i];
        }
        printf("Final sum = %d\n", total);
    }
    
    //Step 8: Finalize MPI environment
    MPI_Finalize();
    return 0;
}

// Install OpenMPI on Ubuntu:

// sudo apt update
// sudo apt install gcc
// sudo apt install openmpi-bin libopenmpi-dev

// Verify installation:

// mpicc --version
// mpirun --version

// mpicc add.c -o add
// mpirun -np 4 ./add
// mpirun -np 4 --oversubscribe ./add