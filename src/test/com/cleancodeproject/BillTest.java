package com.cleancodeproject;

import com.cleancodeproject.bill.Bill;
import com.cleancodeproject.billable.product.Product;
import com.cleancodeproject.billable.product.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BillTest {

    private Bill bill;

    private Double randomDouble;


    @BeforeEach
    public void setUp() {
        this.bill = new Bill();
        this.randomDouble = Math.random() * 100;
    }

    @Test
    public void whenAddEntry_thenLengthChanged() {
        Assertions.assertEquals(bill.getBillElements().size(), 0);
        bill.addElement(Product.builder().exclTaxPrice(20).type(ProductType.OTHER).isImported(true).build(), 1);
        Assertions.assertEquals(bill.getBillElements().size(), 1);
    }


    @Test
    public void whenAddEntry_thenTotalPriceChanged() {
        Assertions.assertEquals(bill.getTotalPrice(), 0);
        bill.addElement(Product.builder().exclTaxPrice(20).type(ProductType.OTHER).isImported(false).build(), 1);
        Assertions.assertEquals(bill.getTotalPrice(), 20);
    }

    @Test
    public void whenAddImportedOtherProduct_thenTotalTaxesIs10PercentsRounded() {
        Assertions.assertEquals(bill.getTotalTaxes(), 0);
        bill.addElement(Product.builder().name("Shampoo").exclTaxPrice(randomDouble).type(ProductType.OTHER).isImported(true).build(), 1);
        Assertions.assertEquals(bill.getTotalTaxes(), Math.round(20 * randomDouble * 0.05) / 20.0);
    }

    @Test
    public void whenAddNotImportedNotOtherProduct_thenTotalTaxesIs5PercentsRounded() {
        Assertions.assertEquals(bill.getTotalTaxes(), 0);
        bill.addElement(Product.builder().name("Tacos XL Triple Meat Blue Cord Nuggets Chicken Double Curry Sauce").exclTaxPrice(randomDouble).type(ProductType.FOOD).isImported(false).build(), 1);
        Assertions.assertEquals(bill.getTotalTaxes(), Math.round(20 * randomDouble * 0.1) / 20.0);
    }

    @Test
    public void whenAddImportedNotOtherProduct_thenTotalTaxesIs15PercentsRounded() {
        Assertions.assertEquals(bill.getTotalTaxes(), 0);
        bill.addElement(Product.builder().name("Tacos XL Triple Meat Blue Cord Nuggets Chicken Curry Sauce").exclTaxPrice(randomDouble).type(ProductType.FOOD).isImported(true).build(), 1);
        Assertions.assertEquals(bill.getTotalTaxes(), Math.round(20 * randomDouble * 0.15) / 20.0);
    }


}
