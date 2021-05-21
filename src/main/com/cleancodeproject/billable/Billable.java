package com.cleancodeproject.billable;

public interface Billable {
    double getExclTaxPrice();
    double getInclTaxPrice();
    double getTotalTaxes();
    String getName();

}
