package store.domain;

import java.util.Map;

public class Stock {
    private Map<Product, Integer> productCountMap;

    public Stock(Map<Product, Integer> productCountMap) {
        this.productCountMap = productCountMap;
    }

    public Map<Product, Integer> getProductCountMap() {
        return productCountMap;
    }

}
