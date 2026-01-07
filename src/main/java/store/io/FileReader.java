package store.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import store.converter.StringToIntConverter;
import store.converter.StringToLocalDateConverter;
import store.domain.Promotion;
import store.domain.PromotionCatalog;

public class FileReader {
    private static final String FILE_NAME = "products.md";
    private static final String FILE_NAME_2 = "promotions.md";
    private static final String DELIMITER = ",";

    // (일급 컬렉션 클래스 반환)
    public PromotionCatalog makePromotionCatalog() {
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

    // List<String> 반환
//    public List<String> readLines() {
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
//
//        if (inputStream == null) {
//            throw new IllegalArgumentException(FILE_NAME + "을 classpath에서 찾을 수 없습니다.");
//        }
//
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
//            return reader.lines()
//                    .filter(line -> !line.isBlank())
//                    .collect(Collectors.toList());
//        } catch (IOException e) {
//            throw new IllegalArgumentException(FILE_NAME + " 파일을 읽는 과정에서 오류가 발생했습니다.", e);
//        }
//    }

    // 메인 메소드
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        fileReader.makePromotionCatalog();
        //fileReader.makeAttendanceCatalog();
    }
}
