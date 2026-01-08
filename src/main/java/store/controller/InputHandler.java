package store.controller;

import java.util.ArrayList;
import java.util.List;
import store.converter.StringToIntConverter;
import store.domain.Order;
import store.domain.OrderProduct;
import store.domain.Stock;
import store.util.DelimiterParser;
import store.view.InputView;

public class InputHandler {

    private final InputView inputView;
    private final IteratorInputTemplate inputTemplate;

    public InputHandler(InputView inputView, IteratorInputTemplate iteratorInputTemplate) {
        this.inputView = inputView;
        this.inputTemplate = iteratorInputTemplate;
    }

    public Order inputOrder(Stock stock) {
        DelimiterParser parser = new DelimiterParser();
        StringToIntConverter converter = new StringToIntConverter();
        List<OrderProduct> orderProductList = new ArrayList<>();

        return inputTemplate.execute(
                inputView::inputOrder,
                value -> {
                    value = value.trim();
                    List<String> parsedFirst = parser.parse(value, ",");

                    for (String part : parsedFirst) {
                        part = part.trim().replace("[", "").replace("]", "");
                        List<String> productAndCount = parser.parse(part, "-");
                        String productName = productAndCount.get(0); // 사이다
                        int count = converter.convert(productAndCount.get(1));// 2

                        orderProductList.add(new OrderProduct(productName, count)); // 카탈로그가 있다면 카탈로그에 추가

                    }

                    validateOrder(stock, orderProductList);
                    return new Order(orderProductList);
                }
        );
    }

    private void validateOrder(Stock stock, List<OrderProduct> orderProductList) {
        //- 예외) 재고에 존재하지 않는 상품을 주문한 경우
        boolean isInStock = orderProductList.stream()
                .anyMatch(orderProduct -> !stock.isInStock(orderProduct.getProductName()));
        if (isInStock) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다. 다시 입력해 주세요.");
        }

        //- 예외) 재고에 있는 상품(프로모션+일반)보다 많은 상품을 주문했을 경우
        orderProductList.forEach(orderProduct -> {
            stock.validateIsEnoughProducts(orderProduct.getProductName(), orderProduct.getCount());
        });
    }
}