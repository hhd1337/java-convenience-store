package store.domain;

import java.util.List;

public class Order {
    private List<OrderProduct> orderProductList;

    public Order(List<OrderProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }
}
