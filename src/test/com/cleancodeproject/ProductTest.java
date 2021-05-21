package com.cleancodeproject;

import com.cleancodeproject.billable.product.Product;
import com.cleancodeproject.billable.product.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    private Product product;

    @Test
    public void whenTypeOtherAndNotImported_thenNoTaxTotal() {
        product = Product.builder().type(ProductType.OTHER).isImported(false).exclTaxPrice(21.3).name("Tacos XL").build();
        Assertions.assertEquals(product.getTotalTaxes(), 0);
    }

    @Test
    public void whenTaxesAndHTPrice_thenTTCPriceIsComputed() {
        product = Product.builder().exclTaxPrice(20).type(ProductType.BOOK).build();
        Assertions.assertTrue(product.getInclTaxPrice() > 20);
    }

    @Test
    public void whenProductTypeIsNotOther_thenTaxesAre10Percents() {

        product = Product.builder().exclTaxPrice(20).type(ProductType.BOOK).build();
        Assertions.assertEquals(product.getTotalTaxes(), 2);

    }


}
