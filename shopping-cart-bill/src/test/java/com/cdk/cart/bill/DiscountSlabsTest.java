package com.cdk.cart.bill;

import org.junit.Assert;
import org.junit.Test;

public class DiscountSlabsTest {

    private final DiscountCalculator discountCalculator = DiscountCalculator.getInstance();

    @Test
    public void testPremiumDiscountSlabs() {
        String customerType = "premium";
        testDiscountedPrice(customerType, 4000, 3600);
        testDiscountedPrice(customerType, 8000, 7000);
        testDiscountedPrice(customerType, 12000, 10200);
        testDiscountedPrice(customerType, 20000, 15800);
    }

    @Test
    public void testRegularDiscountSlabs() {
        String customerType = "regular";
        testDiscountedPrice(customerType, 5000, 5000);
        testDiscountedPrice(customerType, 10000, 9500);
        testDiscountedPrice(customerType, 15000, 13500);
    }

    private void testDiscountedPrice(String customerType, double price, double expectedPrice) {
        double actualPrice = discountCalculator.getDiscountedPrice(customerType, price);
        System.out.println(String.format("Customer Type: %s, Original Price: %.2f, Discounted Price: %.2f", customerType, price, actualPrice));
        Assert.assertEquals(Double.doubleToLongBits(actualPrice), Double.doubleToLongBits(expectedPrice));
    }

}
