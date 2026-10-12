#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "banker.h"


int main(int argc, char *argv[])
{
    /*
     * Usage:
     *
     * ./banker <input-file> <R0> <R1> ... <Rm-1>
     *
     * Example:
     *
     * ./banker inPutFile.txt 10 5 7 8
     */

    if (argc < 3)
    {
        printf(
            "Usage: %s <input-file> "
            "<R0> <R1> ... <Rm-1>\n",
            argv[0]
        );

        return 1;
    }


    /*
     * Create the Bank.
     */
    Bank bank;


    /*
     * TODO:
     *
     * 1. Get the input filename.
     *
     * 2. Determine the number of
     *    resource types.
     *
     * 3. Read the initial resource
     *    values from argv[].
     *
     * 4. Call initialize_bank().
     *
     * 5. Open the input file.
     *
     * 6. Read the maximum demand
     *    for each customer.
     *
     * 7. Call add_customer() for
     *    each customer.
     */


    /*
     * TODO:
     *
     * Repeatedly read commands
     * from the user.
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
     *
     * Call:
     * request_resources(...)
     *
     *
     * RL:
     *
     * Call:
     * release_resources(...)
     *
     *
     * *:
     *
     * Call:
     * get_state(...)
     */

    return 0;
}
