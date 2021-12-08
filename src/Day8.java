import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day8 extends DAY {

    public static void main(String[] args) {
        List<String[]> input = readFromResFile("Day8-test")
                .stream()
                .map((String s) -> s.split("\\s\\|\\s"))
                .collect(Collectors.toList());
        for (String[] s : input) {

        }

    }

    private static void partOne() {
        List<String> input = Arrays.stream(readFromResFile("Day8.txt")
                        .stream()
                        .map((String s) -> s.split("\\s\\|\\s")[1]).collect(Collectors.joining(" "))
                        .split(" "))
                .collect(Collectors.toList());

        int easyDigitCounter = 0;
        for (String s : input) {
            int numberOfSegments = (int) s.chars().distinct().count();
            if (numberOfSegments == 2 || numberOfSegments == 3  || numberOfSegments == 4 || numberOfSegments == 7) {
                easyDigitCounter++;
            }
        }

        System.out.println(easyDigitCounter);
    }
}
