package com.cleancodeproject.billable.product;

import com.cleancodeproject.billable.Billable;
import lombok.*;

@Builder
@Setter
@AllArgsConstructor
public class Product implements Billable {
    private double exclTaxPrice;
    private ProductType type;
    private boolean isImported;
    private String name;

    @Override
    public double getExclTaxPrice() {
        return exclTaxPrice;
    }

    @Override
    public double getInclTaxPrice() {
        return this.getExclTaxPrice() + this.getTotalTaxes();
    }

    @Override
    public double getTotalTaxes() {
        double taxPercentage = 0;
        switch (this.type) {
            case DRUGS, BOOK, FOOD -> taxPercentage += 10;
        }
        if (isImported) {
            taxPercentage += 5;
        }
        return Math.round(20.0 * (exclTaxPrice * taxPercentage/100)) / 20.0;
    }

    @Override
    public String getName() {
        return name;
    }
}
