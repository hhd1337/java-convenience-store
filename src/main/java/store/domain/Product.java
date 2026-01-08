package store.domain;

import java.util.Objects;

public class Product {
    private String name;
    private int price;
    private Promotion promotion;

    public Product(String name, int price, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    private String promotionName() {
        return (promotion == null) ? null : promotion.getName(); // Promotion에 getName() 필요
    }

    @Override
    public boolean equals(Object o) {
        // 1) 같은 객체(같은 참조)이면 무조건 true
        if (this == o) {
            return true;
        }

        // 2) null 이면 비교 불가 → false
        if (o == null) {
            return false;
        }

        // 3) 타입이 Product가 아니면 비교 불가 → false
        if (!(o instanceof Product)) {
            return false;
        }

        // 4) 이제 안전하게 캐스팅 가능
        Product other = (Product) o;

        // 5) 필드 값 비교 (equals 기준)
        // Product의 name, price, promotionName 값들이 모두 같으면 같은 Product로 봐라.
        return price == other.price
                && java.util.Objects.equals(name, other.name)
                && java.util.Objects.equals(promotionName(), other.promotionName());
    }


    @Override // Object의 hashCode()를 내가 정의한 equals 규칙과 "일관되게" 재정의한다는 표시
    public int hashCode() { // 해시 기반 컬렉션(HashMap/LinkedHashMap/HashSet)이 key를 빠르게 찾기 위해 쓰는 값

        return Objects.hash(name, price, promotionName());
        // (1) equals에서 비교에 사용한 필드(name, price, promotionName)를 똑같이 사용해 해시값 생성
        // (2) "equals가 true면 hashCode도 같아야 한다" 규칙을 만족시키기 위한 구현
        // (3) Objects.hash는 내부적으로 주어진 값들을 조합해서 안정적인 int 해시값을 만들어준다 (null도 안전)
    }
}
