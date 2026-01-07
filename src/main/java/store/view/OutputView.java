package store.view;

import store.util.ErrorMessage;

public class OutputView {

    public void printErrorMessage(Exception exception) {
        System.out.println(ErrorMessage.PREFIX + exception.getMessage());
    }

    public void printHelloAndStock() {
        System.out.println("안녕하세요. w 편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다.");
        // 상품명, 가격, 프로모션, 이름, 재고 쭉 출력
    }
}
