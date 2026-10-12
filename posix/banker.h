#ifndef BANKER_H
#define BANKER_H

#define NUMBER_OF_CUSTOMERS 5
#define MAX_RESOURCES 20

/*
 * Bank data structure
 */
typedef struct {

    int number_of_customers;
    int number_of_resources;

    /* Available amount of each resource */
    int available[MAX_RESOURCES];

    /* Maximum demand of each customer */
    int maximum[NUMBER_OF_CUSTOMERS][MAX_RESOURCES];

    /* Resources currently allocated to each customer */
    int allocation[NUMBER_OF_CUSTOMERS][MAX_RESOURCES];

    /* Remaining resource need of each customer */
    int need[NUMBER_OF_CUSTOMERS][MAX_RESOURCES];

} Bank;


/*
 * Function Prototypes
 */

void initialize_bank(
    Bank *bank,
    int number_of_resources,
    int resources[]
);

void add_customer(
    Bank *bank,
    int customer_num,
    int max_demand[]
);

int request_resources(
    Bank *bank,
    int customer_num,
    int request[]
);

void release_resources(
    Bank *bank,
    int customer_num,
    int release[]
);

void get_state(
    Bank *bank
);

#endif
