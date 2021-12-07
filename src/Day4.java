import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 extends DAY {
    public static void main(String[] args) {
        // Output
        boolean gameIsWon = false;
        int winSum = 0;
        String winBoard = "";

        int looseSum;
        String looseBoard;

        List<String> input = readFromResFile("Day4.txt");
        List<Integer> numbers = Arrays.stream(input.get(0).split(",")).map((String st) -> Integer.valueOf(st)).toList();
        input.remove(0);


        List<List<List<Integer>>> boards = new ArrayList<>();

        int boardCount = -1;
        int rowCount = -1;
        for (String s : input) {
            if (s.equals("")) {
                boardCount++;
                boards.add(new ArrayList<>());
                for (int h = 0; h < 10; h++) {
                    boards.get(boardCount).add(new ArrayList<>());
                }
                rowCount = 0;
            } else {
                List<Integer>nums = Arrays.stream(s.split(" ")).filter((String st) -> !(st.equals("") | st.equals(" "))).map((String st) -> Integer.valueOf(st.replaceAll(" ", ""))).collect(Collectors.toList());
                int numberCount = 0;
                // System.out.println(nums);
                for (int n : nums) {
                    boards.get(boardCount).get(rowCount).add(n);
                    boards.get(boardCount).get(numberCount + 5).add(n);
                    numberCount++;

                }
                rowCount++;
            }
        }

        List<List<List<Integer>>> leftBoards = new ArrayList<>();
        leftBoards.addAll(boards);
        for (int n : numbers) {
            for (List<List<Integer>> b : boards) {
                for (List<Integer> rc : b) {
                    if (rc.contains(n)) rc.remove(Integer.valueOf(n));
                }
                for (List<Integer> rc : b) {
                    if (rc.isEmpty()) {
                        leftBoards.remove(b);
                        if (!gameIsWon) {
                            winSum = (b.stream().flatMap(List::stream).mapToInt((Integer inte) -> (int) inte).sum() / 2) * n;
                            winBoard = b.toString();
                            gameIsWon = true;
                        }
                        if (leftBoards.isEmpty()) {
                            looseSum = (b.stream().flatMap(List::stream).mapToInt((Integer inte) -> (int) inte).sum() / 2) * n;
                            looseBoard = b.toString();

                            System.out.println(n);
                            System.out.println("Winner: " + winBoard + " with " + winSum);
                            System.out.println("Looser: " + looseBoard + " with " + looseSum);
                            return;
                        }
                    };
                }
            }
        }

        System.out.println(boards);


    }
}
