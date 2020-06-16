package com.cdk.cart.bill;

public final class DiscountSlab {

    private final int amount;
    private final int discount;

    private DiscountSlab next;

    public DiscountSlab(int price, int discount) {
        this.amount = price;
        this.discount = discount;
    }

    public int getAmount() {
        return amount;
    }

    public int getDiscount() {
        return discount;
    }

    public DiscountSlab getNext() {
        return next;
    }

    public void setNext(DiscountSlab next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "DiscountSlab {" +
                "price = " + amount +
                ", discount = " + discount +
                '}';
    }

}
