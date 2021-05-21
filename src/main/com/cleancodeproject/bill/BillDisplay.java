package com.cleancodeproject.bill;

import lombok.Setter;

import java.text.DecimalFormat;

@Setter
public class BillDisplay {

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public static void displayBill(Bill bill) {
        bill.getBillElements().forEach((key, value) -> {
            System.out.printf("%d %s: %s%n", value, key.getName(), df2.format(key.getExclTaxPrice()));
        });
        System.out.printf("Montant des taxes : %s%n", df2.format(bill.getTotalTaxes()));
        System.out.printf("Total : %s%n", df2.format(bill.getTotalPrice()));
    }
}
