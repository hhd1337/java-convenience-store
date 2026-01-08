package store.domain;

public class OrderProduct {
    private String productName;
    private int count;

    public OrderProduct(String productName, int count) {
        this.productName = productName;
        this.count = count;
    }

    public String getProductName() {
        return productName;
    }

    public int getCount() {
        return count;
    }
}
