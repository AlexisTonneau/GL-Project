package com.cleancodeproject.bill;


import com.cleancodeproject.billable.Billable;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;

@Getter
@AllArgsConstructor
public class Bill {
    private final HashMap<Billable, Integer> billElements = new HashMap<>();

    public double getTotalTaxes() {
        return this.billElements.entrySet().stream().mapToDouble(e -> e.getKey().getTotalTaxes() * e.getValue()).sum();
    }

    public double getTotalPrice() {
        return this.billElements.entrySet().stream().mapToDouble(e -> e.getKey().getInclTaxPrice() * e.getValue()).sum();
    }

    public void addElement(Billable billable, int number) {
        this.billElements.put(billable, number);
    }


}
