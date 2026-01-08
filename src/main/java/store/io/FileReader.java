package store.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import store.converter.StringToIntConverter;
import store.converter.StringToLocalDateConverter;
import store.domain.Product;
import store.domain.Promotion;
import store.domain.PromotionCatalog;
import store.domain.Stock;

public class FileReader {
    private static final String FILE_NAME = "products.md";
    private static final String FILE_NAME_2 = "promotions.md";
    private static final String DELIMITER = ",";

    // (일급 컬렉션 클래스 반환)
    public PromotionCatalog readPromotionCatalogFromFile() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME_2);
        StringToIntConverter intConverter = new StringToIntConverter();
        StringToLocalDateConverter dateConverter = new StringToLocalDateConverter();
        if (inputStream == null) {
            throw new IllegalArgumentException(FILE_NAME_2 + "을 classpath에서 찾을 수 없습니다.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<Promotion> promotionList = reader.lines()
                    .skip(1) // 헤더 스킵
                    .filter(line -> !line.isBlank()) // 빈줄 건너뛰기
                    .map(line -> line.split(DELIMITER)) // 가로줄 한줄을 ,로 잘라서 각 줄을 [,로 분리된 문자열들 배열]로 만듦
                    .map(splitedLine -> {
                        // word : 한 줄에서 , 로 분리된 단어들 (한 가로줄에 단어 몇개 있는지 보고 2개면 word3,4 지움)
                        // String 말고 다른 타입으로 찾아서 구성 후 Promotion(여기서 클래스) 생성자에 넣어도 됨!!!

                        String name = splitedLine[0].trim();
                        int buy = intConverter.convert(splitedLine[1].trim());
                        int get = intConverter.convert(splitedLine[2].trim());
                        LocalDate start_date = dateConverter.convert(splitedLine[3].trim());
                        LocalDate end_date = dateConverter.convert(splitedLine[4].trim());

                        return new Promotion(name, buy, get, start_date, end_date);
                    })
                    .collect(Collectors.toList()); // 가변 리스트 반환

            return new PromotionCatalog(promotionList);
        } catch (IOException e) {
            throw new IllegalArgumentException(FILE_NAME_2 + "파일을 읽는 과정에서 문제가 발생했습니다.");
        }
    }


    public Stock readStockFromFile(PromotionCatalog promotionCatalog) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
        StringToIntConverter intConverter = new StringToIntConverter();
        if (inputStream == null) {
            throw new IllegalArgumentException(FILE_NAME + "을 classpath에서 찾을 수 없습니다.");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Map<Product, Integer> productCountMap = new LinkedHashMap<>();

            reader.lines()
                    .skip(1) // 헤더 스킵
                    .filter(line -> !line.isBlank()) // 빈줄 건너뛰기
                    .map(line -> line.split(DELIMITER)) // 가로줄 한줄을 ,로 잘라서 각 줄을 [,로 분리된 문자열들 배열]로 만듦
                    .forEach(splitedLine -> {
                        String name = splitedLine[0].trim();
                        int price = intConverter.convert(splitedLine[1].trim());
                        int quantity = intConverter.convert(splitedLine[2].trim());
                        Promotion promotion = promotionCatalog.findPromotionByNameOrNull(splitedLine[3].trim());

                        Product product = new Product(name, price, promotion);
                        productCountMap.put(product, quantity);
                        // 프로모션 SKU면, 일반 SKU를 "없으면" 0으로 보강
                        if (promotion != null) {
                            Product normal = new Product(name, price, null);
                            productCountMap.putIfAbsent(normal, 0);
                        }

                    });
            return new Stock(productCountMap);
        } catch (IOException e) {
            throw new IllegalArgumentException(FILE_NAME + "파일을 읽는 과정에서 문제가 발생했습니다.");
        }
    }

}
