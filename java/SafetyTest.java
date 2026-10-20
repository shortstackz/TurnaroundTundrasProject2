import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Quick test for isSafeState() in BankImpl.java.
 *
 * Compile:  javac BankInterface.java BankImpl.java SafetyTest.java
 * Run:      java SafetyTest
 *
 * THIS IS ANOTHER THROWAWAY TEST FILE, UNTIL SOMEONE FINISHED THE TODOs IN TestBankers.java
 */
public class SafetyTest {

    // Same values as inPutFile.txt
    static final int[][] MAX_DEMAND = {
        {6, 4, 7, 3},
        {4, 2, 3, 2},
        {2, 5, 3, 3},
        {6, 3, 3, 2},
        {5, 6, 7, 5}
    };

    static int passed = 0;
    static int failed = 0;

    public static void main(String[] args) throws Exception {

        System.out.println("=== Test 1: safe request ===");
        BankImpl bank1 = makeBank(new int[] {10, 6, 7, 8});
        check("RQ 0 3 1 2 1 should be SAFE",
              isSafe(bank1, 0, new int[] {3, 1, 2, 1}), true);

        System.out.println();
        System.out.println("=== Test 2: unsafe request ===");
        BankImpl bank2 = makeBank(new int[] {10, 6, 7, 8});
        giveResources(bank2, 0, new int[] {4, 3, 3, 0});   // customer 0 now holds 4 3 3 0
        check("State before the request should be SAFE",
              isSafe(bank2, 0, new int[] {0, 0, 0, 0}), true);
        check("RQ 4 5 2 3 5 should be UNSAFE (enough available, but nobody could finish)",
              isSafe(bank2, 4, new int[] {5, 2, 3, 5}), false);

        System.out.println();
        System.out.println("=== Test 3: checking a request must not change the bank ===");
        BankImpl bank3 = makeBank(new int[] {10, 6, 7, 8});
        isSafe(bank3, 0, new int[] {3, 1, 2, 1});
        int[] available = (int[]) getField(bank3, "available");
        check("Available should still be [10, 6, 7, 8], is " + Arrays.toString(available),
              Arrays.equals(available, new int[] {10, 6, 7, 8}), true);

        System.out.println();
        System.out.println("=== Test 4: README data (10 5 7 8) ===");
        BankImpl bank4 = makeBank(new int[] {10, 5, 7, 8});
        check("RQ 0 3 1 2 1 should be UNSAFE (customer 4 needs 6 of R1, only 5 exist)",
              isSafe(bank4, 0, new int[] {3, 1, 2, 1}), false);

        System.out.println();
        System.out.println(passed + " passed, " + failed + " failed");
    }

    /** Creates a bank and adds all 5 customers. */
    static BankImpl makeBank(int[] resources) {
        BankImpl bank = new BankImpl(5, resources);
        for (int i = 0; i < 5; i++) {
            bank.addCustomer(i, MAX_DEMAND[i]);
        }
        return bank;
    }

    /** Calls the private isSafeState method. */
    static boolean isSafe(BankImpl bank, int customer, int[] request) throws Exception {
        Method m = BankImpl.class.getDeclaredMethod("isSafeState", int.class, int[].class);
        m.setAccessible(true);
        return (Boolean) m.invoke(bank, customer, request);
    }

    /** Hands resources to a customer directly (stand-in until requestResources exists). */
    static void giveResources(BankImpl bank, int customer, int[] amount) throws Exception {
        int[] available = (int[]) getField(bank, "available");
        int[][] allocation = (int[][]) getField(bank, "allocation");
        int[][] need = (int[][]) getField(bank, "need");
        for (int j = 0; j < amount.length; j++) {
            available[j] -= amount[j];
            allocation[customer][j] += amount[j];
            need[customer][j] -= amount[j];
        }
    }

    static Object getField(BankImpl bank, String name) throws Exception {
        Field f = BankImpl.class.getDeclaredField(name);
        f.setAccessible(true);
        return f.get(bank);
    }

    static void check(String description, boolean actual, boolean expected) {
        if (actual == expected) {
            System.out.println("PASS: " + description);
            passed++;
        } else {
            System.out.println("FAIL: " + description + " (got " + actual + ")");
            failed++;
        }
    }
}