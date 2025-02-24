package com.pragma.traceability.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }


    public static final String EMAIL = "email";

    public static final String STATE_BASE_PATH = "/state";
    public static final String STATE_TAG = "State Management";
    public static final String STATE_DESCRIPTION = "API for managing order states and traceability";

    public static final String STATE_SAVE_PATH = "/save";
    public static final String STATE_SAVE_SUMMARY = "Save order state";
    public static final String STATE_SAVE_DESCRIPTION = "Saves the state of an order";

    public static final String STATE_TRACEABILITY_PATH = "/consult-traceability";
    public static final String STATE_TRACEABILITY_SUMMARY = "Get traceability";
    public static final String STATE_TRACEABILITY_DESCRIPTION = "Retrieves traceability records";

    public static final String STATE_EFFICIENCY_ALL_ORDERS_PATH = "/consult-eficiency-all-order";
    public static final String STATE_EFFICIENCY_ALL_ORDERS_SUMMARY = "Get efficiency of all orders";
    public static final String STATE_EFFICIENCY_ALL_ORDERS_DESCRIPTION = "Retrieves efficiency metrics for all orders";

    public static final String STATE_EFFICIENCY_ALL_EMPLOYEES_PATH = "/getEfficiencyAllEmployee";
    public static final String STATE_EFFICIENCY_ALL_EMPLOYEES_SUMMARY = "Get efficiency of all employees";
    public static final String STATE_EFFICIENCY_ALL_EMPLOYEES_DESCRIPTION = "Retrieves efficiency metrics for all employees";



}
