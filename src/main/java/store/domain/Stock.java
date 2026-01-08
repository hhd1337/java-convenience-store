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

    public boolean isInStock(String productName) {
        return productCountMap.keySet()
                .stream()
                .anyMatch(product -> product.getName().equals(productName));
    }

    public int findPromotionProductCountByName(String name) {
        Product promotionProduct = productCountMap.keySet().stream()
                .filter(product -> product.getName().equals(name))
                .filter(product -> product.getPromotion() != null)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름을 가진 프로모션 상품이 재고에 없습니다."));

        return productCountMap.get(promotionProduct);
    }

    public int findNormalProductCountByName(String name) {
        Product normalProduct = productCountMap.keySet().stream()
                .filter(product -> product.getName().equals(name))
                .filter(product -> product.getPromotion() == null)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름을 가진 일반 상품이 재고에 없습니다."));

        return productCountMap.get(normalProduct);
    }

    public void validateIsEnoughProducts(String productName, int orderCount) {
        int normalProductCount = findNormalProductCountByName(productName);
        int promotionProductCount = findPromotionProductCountByName(productName);

        if (normalProductCount + promotionProductCount < orderCount) {
            throw new IllegalArgumentException("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
    }

}
