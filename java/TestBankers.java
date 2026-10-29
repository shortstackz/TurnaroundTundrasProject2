import java.io.*;
import java.util.*;

public class TestBankers {

    public static final int NUMBER_OF_CUSTOMERS = 5;

    private static void printCommands() {
        System.out.println("Commands:");
        System.out.println("To request resources enter: RQ");
        System.out.println("To release resources enter: RL");
        System.out.println("To display the current state enter: *");
        System.out.println("To terminate the program enter: T");
    }

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
        

        // TODO: COMPLETED
         // Repeatedly read commands from the user.
            Scanner input = new Scanner(System.in);
            printCommands();

            while(input.hasNextLine()){
                String[] tokens = input.nextLine().trim().split("\\s+");
                String command = tokens[0].toUpperCase();

                if(command.isEmpty()){
                    continue;
                }

               // Required commands:
               // RQ <customer> <R0> <R1> ... <Rm-1>
               // RL <customer> <R0> <R1> ... <Rm-1>
                if (command.equals("RQ") || command.equals("RL")){
                    if(tokens.length != numberOfResources + 2){
                        System.out.println("Error: " + command + " needs " + numberOfResources + " resource numbers after the customer number, but " + Math.max(0, tokens.length - 2) + " was entered.");
                        continue;
                    }
                    int customerNumber = readNumber(tokens[1]);
                    int[] values = readValues(tokens, numberOfResources);
                    if(customerNumber < 0 || values == null){
                        System.out.println("Error: Customer and resource values must be numbers 0 or greater.");
                        continue;
                    }
                    // RQ:
                    // Call requestResources()
                    if(command.equals("RQ")){
                        if(bank.requestResources(customerNumber, values)){
                            System.out.println("Approved!");
                        }
                        else {
                            System.out.println("Denied!");
                        }
                    }
                    // RL:
                    // Call releaseResources()
                    else{
                        bank.releaseResources(customerNumber, values);
                    }
                }

                // Call getState()
                else if(command.equals("*")){
                    bank.getState();
                }
                else if(command.equals("T")){
                    break;
                }
                else{
                    System.out.println("Error: \"" + tokens[0]+"\"" + " is an invalid input.");
                    printCommands();
                }
            }
             input.close();
    }

        private static BufferedReader openInputFile(String inputFile){
            try{
                return new BufferedReader(new FileReader(inputFile));
            }
            catch(FileNotFoundException e){
                System.err.println("Error could not locate the file: \"" + inputFile + "\"");
                System.exit(1);

                return null;
            }
        }

            private static int[][] readMaxDemand(BufferedReader reader, String inputFile, int numberOfResources)
                throws IOException{
                    int [][] maxDemand = new int[NUMBER_OF_CUSTOMERS][numberOfResources];

                    for(int i = 0; i < NUMBER_OF_CUSTOMERS; i++){
                        String line = reader.readLine();
                        if(line == null){
                            System.err.println("Error: " + inputFile + " only has " + i + " lines, but it needs " + NUMBER_OF_CUSTOMERS + " (one per customer).");
                            System.exit(1);
                        }

                        String[] values = line.trim().split(",");
                        if(values.length != numberOfResources){
                            System.err.println("Error: line " + (i + 1) + " of " + inputFile + " has " + values.length + " numbers, but " + numberOfResources + " resources were given when starting the program.");
                            System.exit(1);
                        }

                        for (int j = 0; j < numberOfResources; j++){
                            maxDemand[i][j] = readNumber(values[j].trim());
                            if(maxDemand[i][j] < 0){
                                System.err.println("Error: line " + (i + 1) + " of " + inputFile + " has an invalid number: " + values[j].trim());
                                System.exit(1);
                            }
                        }
                    }
                    reader.close();
                    return maxDemand;
                }

            private static int[] readValues(String[] tokens, int numberOfResources) {
                int[] values = new int[numberOfResources];
                    for (int j = 0; j < numberOfResources; j++) {
                         values[j] = readNumber(tokens[j + 2]);
                         if (values[j] < 0) {
                             return null;
                        }
                    }
                    return values;
            }


            private static int readNumber(String text) {
                try {
                    return Integer.parseInt(text);
                }
                catch (NumberFormatException e) {
                    return -1;
                }
    }
}
