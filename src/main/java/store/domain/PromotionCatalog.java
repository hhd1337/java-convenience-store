package store.domain;

import java.util.List;

public class PromotionCatalog {
    private List<Promotion> promotionList;

    public PromotionCatalog(List<Promotion> promotionList) {
        this.promotionList = promotionList;
    }

    public Promotion findPromotionByNameOrNull(String name) {
        try {
            return promotionList.stream()
                    .filter(promotion -> promotion.getName().equals(name))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("해당 이름의 프로모션이 없습니다."));

        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
