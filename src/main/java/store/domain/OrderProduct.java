package store.domain;

public class OrderProduct {
    private Product product;
    private int count;

    public OrderProduct(Product product, int count) {
        this.product = product;
        this.count = count;
    }
}
