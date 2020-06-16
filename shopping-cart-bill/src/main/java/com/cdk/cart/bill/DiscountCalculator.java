package com.cdk.cart.bill;

public class DiscountCalculator {

    private static final DiscountCalculator discountCalculator = new DiscountCalculator();
    private final DiscountSlabsConfig discountSlabsConfig = DiscountSlabsConfig.getInstance();

    public static DiscountCalculator getInstance() {
        return discountCalculator;
    }

    public double getDiscountedPrice(String customerType, double price) {
        DiscountSlabs discountSlabs = discountSlabsConfig.getDiscountSlabs(customerType);
        double originalPrice = price;
        double finalDiscount = 0.0;
        for (DiscountSlab discountSlab : discountSlabs) {
            double slabAmount = discountSlab.getAmount();
            double slabDiscount = discountSlab.getDiscount();
            if (price > slabAmount) {
                double excess = price - slabAmount;
                finalDiscount += excess * slabDiscount * 0.01;
                price = slabAmount;
            }
        }
        return originalPrice - finalDiscount;
    }

}
