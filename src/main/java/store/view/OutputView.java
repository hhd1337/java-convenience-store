package store.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import store.domain.Product;
import store.domain.Promotion;
import store.util.ErrorMessage;
import store.view.formatter.MoneyFormatter;

public class OutputView {

    public void printErrorMessage(Exception exception) {
        System.out.println(ErrorMessage.PREFIX + exception.getMessage());
    }

    public void printHelloAndStock(Map<Product, Integer> productCountMap) {
        System.out.println("안녕하세요. w 편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        // 상품명, 가격, 프로모션, 이름, 재고 쭉 출력
        List<String> ProductNameList = new ArrayList<>();

        for (Product product : productCountMap.keySet()) {
            String name = product.getName();
            ProductNameList.add(name);
            String price = MoneyFormatter.format(product.getPrice());
            Integer count = productCountMap.get(product);
            Promotion promotion = product.getPromotion();

            if (promotion == null) {
                if (count == 0) {
                    System.out.printf("- %s %s원 재고 없음%n", name, price);
                    continue;
                }
                System.out.printf("- %s %s원 %d개%n", name, price, count);
                continue;
            }
            System.out.printf("- %s %s원 %d개 %s%n", name, price, count, promotion.getName());
        }
    }

    public void printOrderInputPrompt() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
    }


}
