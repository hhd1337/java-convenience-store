package store.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public LocalDate convert(String value) {
        String trimmed = validateAndTrim(value);
        try {
            return LocalDate.parse(trimmed, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("날짜 형식이 올바르지 않습니다. 예) 2025-01-10");
        }
    }

    private String validateAndTrim(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("날짜/시간 입력이 비어있습니다.");
        }
        return value.trim();
    }

}
