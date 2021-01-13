package anuncio.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class Utils {

    public String formatterDateTime (LocalDateTime dateTime, String pattern) {
        Locale local = new Locale("pt", "BR");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, local);
        return dateTime.format(formatter);
    }
}
