package store.util;

import java.util.ArrayList;
import java.util.List;

public class DelimiterParser {
    private static final String DELIMITER = ",";

    public List<String> parse(String input) {
        validateBlankAndNull(input);

        String[] parts = input.split(DELIMITER);

        List<String> result = new ArrayList<>();
        for (String part : parts) {
            result.add(part.trim());
        }
        return result;
    }

    private static void validateBlankAndNull(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }
    }
}
