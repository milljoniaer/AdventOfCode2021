import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
    private static final boolean TEST_MODE = false;

    public static void main (String[] args) {
        List<Integer> input = null;
        try {
            input = Files.readAllLines(Path.of("res/Day1" + (TEST_MODE ? "-test" : "") + ".txt"))
                    .stream()
                    .map((String s) -> Integer.valueOf(s))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Cannot read Day1.txt: " + System.lineSeparator() + e.toString());
            return;
        }

        partTwo(input);
    }

    public static void partOne(List<Integer> input) {
        int numberBefore = -1;
        int count = 0;

        try {
            for (int x : input) {
                if (x > numberBefore)
                    count++;
                numberBefore = x;
            }
        } catch (Exception e) {
            System.out.println("Integer Array numbers is null!");
            return;
        }

        count--; //The first number is always greater than -1, but you cannot verify if there was an increase

        System.out.println("Number of increases: " + count);
    }

    public static void partTwo(List<Integer> input) {
        List<Integer> sums = new ArrayList<Integer>();
        for (int i = 2; i < input.size(); i++) {
            sums.add(input.get(i)+input.get(i-1)+input.get(i-2));
        }

        partOne(sums);
    }
}
