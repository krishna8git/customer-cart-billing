package com.cdk.cart.bill;

import com.cdk.cart.exceptions.InvalidDiscountSlabConfigurationException;

import java.util.Iterator;

public class DiscountSlabs implements Iterable<DiscountSlab> {

    private final DiscountSlab head;

    public DiscountSlabs(String slabsConfigStr) {
        String[] slabsConfig = slabsConfigStr.split("::");
        head = initDiscountSlabs(slabsConfig);
    }

    public DiscountSlab initDiscountSlabs(String[] slabsConfig) {
        String[] slabs = slabsConfig[0].split("-");
        String[] discounts = slabsConfig[1].split("-");
        if (slabs.length != discounts.length) {
            throw new InvalidDiscountSlabConfigurationException("Slabs and Discounts are not of equal length");
        }
        DiscountSlab head = new DiscountSlab(Integer.parseInt(slabs[0]), Integer.parseInt(discounts[0]));
        for (int i = 1; i < slabs.length; i++) {
            int price = Integer.parseInt(slabs[i]);
            int discount = Integer.parseInt(discounts[i]);
            DiscountSlab temp = new DiscountSlab(price, discount);
            temp.setNext(head);
            head = temp;
        }
        return head;
    }

    @Override
    public Iterator<DiscountSlab> iterator() {
        return new DiscountSlabIterator();
    }

    private class DiscountSlabIterator implements Iterator<DiscountSlab> {

        DiscountSlab temp = head;

        @Override
        public boolean hasNext() {
            return temp != null;
        }

        @Override
        public DiscountSlab next() {
            DiscountSlab next = temp;
            temp = temp.getNext();
            return next;
        }
    }

}
