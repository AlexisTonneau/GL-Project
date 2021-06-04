package com.cleancodeproject;

import com.cleancodeproject.bill.Bill;
import com.cleancodeproject.bill.BillDisplay;
import com.cleancodeproject.billable.product.Product;
import com.cleancodeproject.billable.product.ProductType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BillDisplayTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private Bill bill;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @BeforeEach
    public void setUptBill() {
        bill = new Bill();
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testDisplayBill() {
        String expectedOutputBill =
                """
                        2 Jordan One Low: 190
                        1 Pfizer Vaccine: 200
                        Montant des taxes : 30
                        Total : 610
                        """;
        bill.addElement(Product.builder().name("Jordan One Low").exclTaxPrice(190).type(ProductType.OTHER).isImported(false).build(), 2);
        bill.addElement(Product.builder().name("Pfizer Vaccine").exclTaxPrice(200).type(ProductType.DRUGS).isImported(true).build(), 1);
        BillDisplay.displayBill(bill);
        Assertions.assertEquals(expectedOutputBill, outContent.toString());
    }


}
