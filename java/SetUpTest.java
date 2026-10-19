/**
 * Quick test for Set Up created in BankImpl.java:
 * the constructor, addCustomer(), and getState().
 *
 * Compile (in bash):  javac BankInterface.java BankImpl.java SetUpTest.java
 * Run:      java SetUpTest
 *
 * THIS IS A THROWAWAY TEST FILE, UNTIL SOMEONE FINISHED THE TODOs IN TestBankers.java.
 */
public class SetUpTest {

    public static void main(String[] args) {

        int[] resources = {10, 5, 7, 8};
        int[][] maxDemand = {
            {6, 4, 7, 3},
            {4, 2, 3, 2},
            {2, 5, 3, 3},
            {6, 3, 3, 2},
            {5, 6, 7, 5}
        };

        // Same setup as: java TestBankers inPutFile.txt 10 5 7 8
        BankImpl bank = new BankImpl(5, resources);
        for (int i = 0; i < 5; i++) {
            bank.addCustomer(i, maxDemand[i]);
        }

        System.out.println("=== Test 1: initial state (compare with README section 13) ===");
        bank.getState();

        System.out.println("=== Test 2: changing the caller's array must not change the bank ===");
        resources[0] = 999;
        System.out.println("Available should still start with 10:");
        bank.getState();

        System.out.println("=== Test 3: bad input should print an error and change nothing ===");
        bank.addCustomer(7, maxDemand[0]);              // customer number out of range
        bank.addCustomer(-1, maxDemand[0]);             // negative customer number
        bank.addCustomer(0, new int[] {1, 2});          // too few values
        bank.addCustomer(0, new int[] {1, 2, 3, 4, 5}); // too many values
        bank.addCustomer(0, new int[] {1, -2, 3, 4});   // negative value
        System.out.println("Customer 0 should still be [6 4 7 3]:");
        bank.getState();
    }
}