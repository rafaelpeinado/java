package patientintake;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeConverterShould {

    @Test
    void convertTodayStringCorrectly() {
        LocalDateTime result = DateTimeConverter
                .convertStringToDateTime("today 1:00 pm",
                        LocalDate.of(2018, 9, 1));
        assertEquals(result, LocalDateTime.of(2018, 9, 1, 13, 0));
    }

    @Test
    void convertCorrectPatternToDateTime() {
        LocalDateTime result = DateTimeConverter
                .convertStringToDateTime("9/2/2018 1:00 pm",
                        LocalDate.of(2018, 9, 1));
        assertEquals(result, LocalDateTime.of(2018, 9, 2, 13, 0));
    }

    @Test
    void throwExceptionIfIncorrectPatternProvided() {
        Throwable error = assertThrows(RuntimeException.class, () -> DateTimeConverter
                .convertStringToDateTime("9/2/2018 100 pm",
                        LocalDate.of(2018, 9, 1)));
        assertEquals("Unable to create date time from: [9/2/2018 100 pm], " +
                "please enter with format [M/d/yyyy h:mm a], Text '9/2/2018 100 PM' " +
                "could not be parsed at index 12", error.getMessage());
    }
}