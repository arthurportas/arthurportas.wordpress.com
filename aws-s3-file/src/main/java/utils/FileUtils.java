package utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

public class FileUtils {

	private static String UNDERSCORE = "_";

    public static String generateRandomFileId() {
        return new StringBuilder()
                .append(UUID.randomUUID().toString())
                .append(UNDERSCORE)
                .append(ZonedDateTime.now(ZoneId.of("UTC")))
                .toString();
    }

    public static String generateRandomFileId(String fileName) {
        return new StringBuilder()
                .append(fileName)
                .append(UNDERSCORE)
                .append(UUID.randomUUID().toString())
                .append(UNDERSCORE)
                .append(ZonedDateTime.now(ZoneId.of("UTC")))
                .toString();
    }
}
