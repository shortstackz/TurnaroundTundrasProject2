public class BankImpl implements BankInterface {

    private int numberOfCustomers;
    private int numberOfResources;

    // Banker's Algorithm data structures
    private int[] available;
    private int[][] maximum;
    private int[][] allocation;
    private int[][] need;

    /**
     * Creates a new bank with the specified resources.
     */
    public BankImpl(int numberOfCustomers, int[] resources) {
 
        // TODO: COMPLETED
        // Initialize numberOfCustomers and numberOfResources.
        this.numberOfCustomers = numberOfCustomers;
        this.numberOfResources = resources.length;

        // TODO: COMPLETED
        // Initialize available, maximum,
        // allocation, and need.
        available = new int[numberOfResources];
        for (int j = 0; j < numberOfResources; j++) {
            available[j] = resources[j];
        }

        // initialize int arrays to 0, so allocation starts empty
        maximum = new int[numberOfCustomers][numberOfResources];
        allocation = new int[numberOfCustomers][numberOfResources];
        need = new int[numberOfCustomers][numberOfResources];
    }

    /**
     * Adds a customer and records the customer's
     * maximum resource demand.
     */
    @Override
    public void addCustomer(int customerNum, int[] maxDemand) {
        //TODO: COMPLETED

        if (customerNum < 0 || customerNum >= numberOfCustomers) {
            System.out.println("Error: invalid customer number " + customerNum);
            return;
        }
        if (maxDemand == null || maxDemand.length != numberOfResources) {
            System.out.println("Error: customer " + customerNum
                    + " must have " + numberOfResources + " resource values");
            return;
        }

        for (int j = 0; j < numberOfResources; j++) {
            if (maxDemand[j] < 0) {
                System.out.println("Error: negative maximum for customer " + customerNum);
                return;
            }
        }

        for (int j = 0; j < numberOfResources; j++) {
            maximum[customerNum][j] = maxDemand[j];
            allocation[customerNum][j] = 0;
            need[customerNum][j] = maxDemand[j];   // Need = Max - Allocation (0)
        }
    }

    /**
     * Displays the current state of the bank:
     *
     * Available
     * Maximum
     * Allocation
     * Need
     */

    @Override
    public void getState() {

        // TODO: COMPLETED

        System.out.println("Available:");
        System.out.println(formatRow(available));
        System.out.println();

        printMatrix("Maximum", maximum);
        printMatrix("Allocation", allocation);
        printMatrix("Need", need);
    }

    /** Prints labeled matrix (one customer per line) */
    private void printMatrix(String label, int[][] matrix) {
        System.out.println(label + ":");
        for (int i = 0; i < numberOfCustomers; i++) {
            System.out.println("Customer " + i + ": " + formatRow(matrix[i]));
        }
        System.out.println();
    }

    /** Formats a row [10 5 7 8]. */
    private String formatRow(int[] row) {
        StringBuilder sb = new StringBuilder("[");
        for (int j = 0; j < row.length; j++) {
            if (j > 0) sb.append(' ');
            sb.append(row[j]);
        }
        return sb.append(']').toString();
    }

    /**
     * Determines whether the system would remain
     * in a safe state if the request were granted.
     */
    private boolean isSafeState(int customerNum, int[] request) {

        // TODO: COMPLETED
        // Implement the Banker's Safety Algorithm.

        // Copy the state so the real bank is NEVER changed
        int[] work = new int[numberOfResources];
        int[][] tempAllocation = new int[numberOfCustomers][numberOfResources];
        int[][] tempNeed = new int[numberOfCustomers][numberOfResources];

        for (int j = 0; j < numberOfResources; j++) {
            work[j] = available[j];
        }
        for (int i = 0; i < numberOfCustomers; i++) {
            for (int j = 0; j < numberOfResources; j++) {
                tempAllocation[i][j] = allocation[i][j];
                tempNeed[i][j] = need[i][j];
            }
        }    

        // Pretend request was granted
        for (int j = 0; j < numberOfResources; j++) {
            work[j] -= request[j];
            tempAllocation[customerNum][j] += request[j];
            tempNeed[customerNum][j] -= request[j];
        }

        // No customer has finished (yet)
        boolean[] finish = new boolean[numberOfCustomers];
        int finishedCount = 0;

        // Keep finding a customer whose Need <= Work,
        // let it finish, before taking back resources
        boolean foundOne = true;
        while (foundOne) {
            foundOne = false;

            for (int i = 0; i < numberOfCustomers; i++) {
                if (finish[i]) {
                    continue;
                }

                boolean canFinish = true;
                for (int j = 0; j < numberOfResources; j++) {
                    if (tempNeed[i][j] > work[j]) {
                        canFinish = false;
                        break;
                    }
                }

                if (canFinish) {
                    for (int j = 0; j < numberOfResources; j++) {
                        work[j] += tempAllocation[i][j];
                    }
                    finish[i] = true;
                    finishedCount++;
                    foundOne = true;
                }
            }    
        }

        // Safe only if every customer finishes
        return finishedCount == numberOfCustomers;
    }

    /**
     * Attempts to allocate resources to a customer.
     *
     * @return true if approved
     *         false if denied
     */
    @Override
    public synchronized boolean requestResources(
            int customerNum, int[] request) {

        // TODO: COMPLETED
        // 1. Validate the request.
        if (customerNum < 0 || customerNum >= numberOfCustomers) {
            return false;
        }
        if (request == null || request.length != numberOfResources) {
            return false;
        }

        for (int i = 0; i < numberOfResources; i++) {
            if (request[i] < 0) {
                return false;
            }
        }
        
        // 2. Check whether the request exceeds Need.
        for (int i = 0; i < numberOfResources; i++) {
            if (request[i] > need[customerNum][i]) {
                return false;
            }
        }
        
        // 3. Check whether the resources are available.
        for (int i = 0; i < numberOfResources; i++) {
            if (request[i] > available[i]) {
                return false;
            }
        }
        
        // 4. Check whether the resulting state is safe.
        if (!isSafeState(customerNum, request)) {
            return false;
        }
        
        // 5. Update the bank if the request is approved.
        for (int i = 0; i < numberOfResources; i++) {
            available[i] -= request[i];
            allocation[customerNum][i] += request[i];
            need[customerNum][i] -= request[i];
        }
        
        return true;
    }

    /**
     * Releases resources held by a customer.
     */
    @Override
    public synchronized void releaseResources(
            int customerNum, int[] release) {

        // TODO: COMPLETED
        // Validate and release the resources.
        if (customerNum < 0 || customerNum >= numberOfCustomers) {
            System.out.println("Error: Invalid customer number");
            return;
        }

        if (release == null || release.length != numberOfResources) {
            System.out.println("Error: Incorrect number of resources");
            return;
        }

        for (int i = 0; i < numberOfResources; i++) {
            if (release[i] < 0) {
                System.out.println("Error: Cannot release a negative value");
                return;
            }
        }

        for (int i = 0; i < numberOfResources; i++) {
            if (release[i] > allocation[customerNum][i]) {
                System.out.println("Error: Cannot release more resources than allocated");
                return;
            }
        }
      
        // Releasing the resources if successful
        for (int i = 0; i < numberOfResources; i++) {
            available[i] += release[i];
            allocation[customerNum][i] -= release[i];
            need[customerNum][i] += release[i];
        }
    }
}
