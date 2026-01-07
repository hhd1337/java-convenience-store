package store.controller;

import store.view.InputView;

public class InputHandler {

    private final InputView inputView;
    private final IteratorInputTemplate inputTemplate;

    public InputHandler(InputView inputView, IteratorInputTemplate iteratorInputTemplate) {
        this.inputView = inputView;
        this.inputTemplate = iteratorInputTemplate;
    }

    // Menu 메서드
//    public Menu inputMenu() {
//        StringToMenuConverter converter = new StringToMenuConverter();
//        return inputTemplate.execute(
//                inputView::inputMenu,
//                value -> {
//                    value = value.trim();
//
//                    // 만약 메뉴 하나만 검증해야 할 로직이 있다면 아래 사용
//                    if (value.equals(Menu.MENU1.getSymbol())) {
//                        validate();
//                    }
//                    return converter.convert(value);
//                }
//        );
//    }

    // LocalDateTime 메서드 - DateTimeException catch함
//    private LocalDate inputAttendanceUpdateDayInMonth(LocalDate currentDate) {
//        return inputTemplate.execute(
//                inputView::inputAttendanceUpdateDayInMonth,
//                value -> {
//                    try {
//                        int year;
//                        int monthInt;
//                        Month month = Month.of(monthInt);
//                        int dayOfMonth;
//
//                        return LocalDate.of(year, month, dayOfMonth);
//                    } catch (NumberFormatException | DateTimeException exception) {
//                        throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
//                    }
//                }
//        );
//    }

    // 인풋을 parse하는 경우 사용 (delimeter 1개!) -> "이오,델타,깨박,윽박"
//    public List<String> inputOrderMenuAndCounts() {
//        DelimiterParser parser = new DelimiterParser();
//        StringToIntConverter converter = new StringToIntConverter();
//        return inputTemplate.execute(
//                inputView::inputOrderMenuAndCounts,
//                value -> {
//                    value = value.trim();
//                    // 만약 양 끝에 [] 있으면 처음에 한번 벗김.
//                    // value = value.trim().replace("[", "").replace("]", "");
//
//                    // value가 이렇게 들어옴 : " 티본스테이크 - 1, 바비큐립- 1 , 초코케이크 -2, 제로콜라-1 "
//                    // 공백 어디에 있어도 아무상관 없음.
//                    // 먼저 ","로 구분
//                    List<String> parsedFirst = parser.parse(value, ",");
//
//                    // 검증로직
//                    // validate();
//                    return parsedFirst;
//                }
//        );
//    }

    // 인풋을 parse하는 경우 사용 (delimeter 2개!) -> "티본스테이크-1,바비큐립-1,초코케이크-2"
//    public Map<Food, Integer> inputOrderMenuAndCounts() {
//        DelimiterParser parser = new DelimiterParser();
//        StringToIntConverter converter = new StringToIntConverter();
//        return inputTemplate.execute(
//                inputView::inputOrderMenuAndCounts,
//                value -> {
//                    value = value.trim();
//                    // 만약 양 끝에 [] 있으면 처음에 한번 벗김.
//                    // value = value.trim().replace("[", "").replace("]", "");
//
//                    // value가 이렇게 들어옴 : " 티본스테이크 - 1, 바비큐립- 1 , 초코케이크 -2, 제로콜라-1 "
//                    // 공백 어디에 있어도 아무상관 없음.
//                    // 먼저 ","로 구분
//                    List<String> parsedFirst = parser.parse(value, ",");
//
//                    // 이제 "-"로 구분 (1. 객체 만듦, 일급컬렉션(Catalog) 객체 반환)
//                    for (String part : parsedFirst) {
//                        List<String> foodAndCount = parser.parse(part, "-");
//                        foodAndCount.get(0);
//                        foodAndCount.get(1);// 여기서 이거 두개 사용하기 (객체 생성자에 넣어서 객체 하나 만들어도 됨)
//
//                        OrderCatalog.add(new Order(foodAndCount.get(0), foodAndCount.get(1))); // 카탈로그가 있다면 카탈로그에 추가
//                    }
//
//                    // 이제 "-"로 구분 (2. 요소가 2개라면 Map 반환)
//                    Map<Food, Integer> foodCountMap = new LinkedHashMap<>();
//                    for (String part : parsedFirst) {
//                        List<String> foodAndCount = parser.parse(part, "-");
//                        Food food = Food.findByName(foodAndCount.get(0));
//                        int count = converter.convertPositiveInt(foodAndCount.get(1));
//
//                        foodCountMap.put(food, count);
//                    }
//
//                    // validate()
//                    validate();
//                    return foodCountMap;
//                }
//        );
//    }
}