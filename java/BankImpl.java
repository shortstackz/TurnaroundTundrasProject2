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

        // TODO:
        // Implement the Banker's Safety Algorithm.

        return false;
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

        // TODO:
        // 1. Validate the request.
        // 2. Check whether the request exceeds Need.
        // 3. Check whether the resources are available.
        // 4. Check whether the resulting state is safe.
        // 5. Update the bank if the request is approved.

        return false;
    }

    /**
     * Releases resources held by a customer.
     */
    @Override
    public synchronized void releaseResources(
            int customerNum, int[] release) {

        // TODO:
        // Validate and release the resources.
    }
}
