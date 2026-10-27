import java.io.*;
import java.util.*;

public class TestBankers {

    public static final int NUMBER_OF_CUSTOMERS = 5;

    public static void main(String[] args) throws IOException {

        /*
         * Usage:
         *
         * java TestBankers <input-file> <R0> <R1> ... <Rm-1>
         *
         * Example:
         *
         * java TestBankers inPutFile.txt 10 5 7 8
         */

        if (args.length < 2) {
            System.err.println(
                "Usage: java TestBankers "
                + "<input-file> <R0> <R1> ... <Rm-1>"
            );
            System.exit(1);
        }
        //TODO: COMPLETED
        // 1. Read the input filename.
        String inputFile = args[0];
        
        // 2. Determine the number of resource types.
        int numberOfResources = args.length - 1;
        
        // 3. Read the initial resources from the command-line arguments.
        int [] resources = new int[numberOfResources];
        for (int j = 0; j < numberOfResources; j++){
            resources[j] = readNumber(args[j + 1]);
            if (resources[j] < 0){
                System.err.println("Error: Invalid entry resource values must be 0 or more, \"" + args[j + 1] + "\" is not valid.");
                System.exit(1);
            } 
        }
         
         // 4. Create the BankImpl object.
         BankInterface bank = new BankImpl(NUMBER_OF_CUSTOMERS, resources);
        
         // 5. Open the input file.
         BufferedReader reader = openInputFile(inputFile);

        // 6. Read the maximum demand for each customer.
        int[][] maxDemand = readMaxDemand(reader, inputFile, numberOfResources);

        // 7. Call addCustomer() for each customer.
        for (int i = 0; i < NUMBER_OF_CUSTOMERS; i++) {
            bank.addCustomer(i, maxDemand[i]);
        }
        

        /*
         * TODO:
         *
         * Repeatedly read commands from the user.
         *
         * Required commands:
         *
         * RQ <customer> <R0> <R1> ... <Rm-1>
         *
         * RL <customer> <R0> <R1> ... <Rm-1>
         *
         * *
         *
         *
         * RQ:
         * Call requestResources()
         *
         * RL:
         * Call releaseResources()
         *
         * *:
         * Call getState()
         */
    }
}
