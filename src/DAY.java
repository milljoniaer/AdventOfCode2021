import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DAY {
    public static List<String> readFromResFile(String fileName) {
        try {
            return Files.readAllLines(Path.of("res/"+ fileName));
        } catch (IOException e) {
            System.out.println("Cannot read res/" + fileName + System.lineSeparator() + e.toString());
            return null;
        }
    }

    public static void write(String filename, String text) {
        try {
            Files.write(Path.of("out/" + filename), text.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
