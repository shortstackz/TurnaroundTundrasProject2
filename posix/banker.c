#include <stdio.h>
#include "banker.h"


/*
 * Private helper function.
 *
 * Determines whether granting a request
 * would leave the system in a safe state.
 */
static int is_safe_state(
    Bank *bank,
    int customer_num,
    int request[]
)
{
    /*
     * TODO:
     *
     * Implement the Banker's
     * Safety Algorithm.
     */

    return 0;
}


/*
 * Initialize the bank.
 */
void initialize_bank(
    Bank *bank,
    int number_of_resources,
    int resources[]
)
{
    /*
     * TODO:
     *
     * Initialize:
     *
     * number_of_customers
     * number_of_resources
     * available
     * maximum
     * allocation
     * need
     */
}


/*
 * Add a customer and record
 * the customer's maximum demand.
 */
void add_customer(
    Bank *bank,
    int customer_num,
    int max_demand[]
)
{
    /*
     * TODO
     */
}


/*
 * Request resources.
 *
 * Return:
 *
 *  0  = request approved
 * -1  = request denied
 */
int request_resources(
    Bank *bank,
    int customer_num,
    int request[]
)
{
    /*
     * TODO:
     *
     * 1. Validate the request.
     *
     * 2. Check whether the request
     *    exceeds the customer's need.
     *
     * 3. Check whether the requested
     *    resources are available.
     *
     * 4. Determine whether granting
     *    the request leaves the
     *    system in a safe state.
     *
     * 5. If approved, update the
     *    appropriate data structures.
     */

    return -1;
}


/*
 * Release resources held
 * by a customer.
 */
void release_resources(
    Bank *bank,
    int customer_num,
    int release[]
)
{
    /*
     * TODO:
     *
     * Validate and release the resources.
     */
}


/*
 * Display the current bank state:
 *
 * Available
 * Maximum
 * Allocation
 * Need
 */
void get_state(
    Bank *bank
)
{
    /*
     * TODO
     */
}
