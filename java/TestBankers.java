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

        /*
         * TODO:
         *
         * 1. Read the input filename.
         *
         * 2. Determine the number of resource types.
         *
         * 3. Read the initial resources from
         *    the command-line arguments.
         *
         * 4. Create the BankImpl object.
         *
         * 5. Open the input file.
         *
         * 6. Read the maximum demand for each customer.
         *
         * 7. Call addCustomer() for each customer.
         */


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
