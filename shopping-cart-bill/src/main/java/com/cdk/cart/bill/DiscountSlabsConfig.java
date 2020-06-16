package com.cdk.cart.bill;

import com.cdk.cart.exceptions.NoSuchCustomerTypeException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class DiscountSlabsConfig {

    private static final DiscountSlabsConfig INSTANCE = new DiscountSlabsConfig();

    private static final Map<String, DiscountSlabs> map = new HashMap<>();

    public static DiscountSlabsConfig getInstance() {
        try {
            final Properties properties = new Properties();
            InputStream taxSlabsResource = DiscountSlabsConfig.class.getClassLoader().getResourceAsStream("discountslabs.properties");
            properties.load(taxSlabsResource);
            properties.forEach(DiscountSlabsConfig::addToMap);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return INSTANCE;
    }

    private static void addToMap(Object key, Object value) {
        map.put(key.toString().toLowerCase(), new DiscountSlabs(value.toString()));
    }

    public DiscountSlabs getDiscountSlabs(String customerType) {
        DiscountSlabs discountSlabs = map.get(customerType.toLowerCase());
        if (Objects.nonNull(discountSlabs)) {
            return discountSlabs;
        }
        throw new NoSuchCustomerTypeException("Tax Slabs for Customer Type " + customerType + " have not been registerd with the system");
    }

}
