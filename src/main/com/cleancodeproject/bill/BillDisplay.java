package com.cleancodeproject.bill;

import lombok.Setter;

import java.text.DecimalFormat;

@Setter
public class BillDisplay {

    private static DecimalFormat to2Decimals = new DecimalFormat("#.##");

    public static void displayBill(Bill bill) {
        bill.getBillElements().forEach((key, value) -> {
            System.out.printf("%d %s: %s%n", value, key.getName(), to2Decimals.format(key.getExclTaxPrice()));
        });
        System.out.printf("Montant des taxes : %s%n", to2Decimals.format(bill.getTotalTaxes()));
        System.out.printf("Total : %s%n", to2Decimals.format(bill.getTotalPrice()));
    }
}
