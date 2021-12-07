import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day7 extends DAY {
    public static void main(String[] args) {
        List<Integer> input = Arrays.stream(readFromResFile("Day7.txt").get(0).split(","))
                .map((String s) -> Integer.parseInt(s.replaceAll(" ", "")))
                .collect(Collectors.toList());

        long avg = Math.round(input.stream().mapToInt((Integer n) -> (int) n).average().orElse(0));

        long fuel = 0;
        for (int n : input) {
            long newFuel = 0;
            long dif = Math.abs(n-avg);
            for (long i=1; i<=dif; i++) newFuel = newFuel + i;

            fuel = fuel + newFuel;
            System.out.println("Move from " + n + " to " + avg + ": " + newFuel + " fuel");
        }

        System.out.println("Total fuel: " + fuel);
    }

    private static void partOne() {
        List<Integer> input = Arrays.stream(readFromResFile("Day7.txt").get(0).split(","))
                .map((String s) -> Integer.parseInt(s.replaceAll(" ", "")))
                .sorted()
                .collect(Collectors.toList());

        int median = input.get(input.size()/2);

        int fuel = 0;
        for (int n : input) {
            int newFuel = Math.abs(n-median);
            fuel = fuel + newFuel;
            System.out.println("Move from " + n + " to " + median + ": " + newFuel + " fuel");
        }

        System.out.println("Total fuel: " + fuel);
    }
}
