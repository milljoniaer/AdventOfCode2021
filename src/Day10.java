import java.util.*;
import java.util.stream.Collectors;

public class Day10 extends DAY {

    public static void main(String[] args) {

        List<String> input = readFromResFile("Day10.txt");

        Map<Character, Integer> corruptedScoreMap = new HashMap<>();
        corruptedScoreMap.put(')', 3); corruptedScoreMap.put(']', 57); corruptedScoreMap.put('}', 1197); corruptedScoreMap.put('>', 25137);

        Map<Character, Integer> uncompletedScoreMap = new HashMap<>();
        uncompletedScoreMap.put('(', 1); uncompletedScoreMap.put('[', 2); uncompletedScoreMap.put('{', 3); uncompletedScoreMap.put('<', 4);

        List<Long> uncompletedScores = new ArrayList<>();

        long corruptedLineScore = 0;
        long uncompletedLineScore = 0;
        for (String s : input) {
            char unexpectedChar = ' ';
            Stack<Character> states = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (isOpenTag(c)) {
                    states.push(c);
                } else if (matchingTags(states.peek(), c)) {
                    states.pop();
                } else {
                    unexpectedChar = c;
                    break;
                }
            }
            boolean isCorrect = states.isEmpty();

            long uncompletedScore = 0;
            StringBuilder toComplete = new StringBuilder();
            if (!isCorrect && unexpectedChar == ' ') {
                while (!states.isEmpty()) {
                    char c = states.pop();
                    toComplete.append(getMatchingClosingTag(c));
                    uncompletedScore = uncompletedScore*5 + uncompletedScoreMap.get(c);
                }
                uncompletedScores.add(uncompletedScore);
            }

            if (!isCorrect && unexpectedChar != ' ')
                corruptedLineScore += corruptedScoreMap.get(unexpectedChar);

            System.out.println(s + "\t -> " + isCorrect +
                    (unexpectedChar != ' ' ? " (not expected Char: '" + unexpectedChar + "' )"
                            : " (uncomplete, complete with '" + toComplete.toString() + "', uncompletedScore: " + uncompletedScore + ")"));
        }

        uncompletedScores = uncompletedScores.stream().sorted().collect(Collectors.toList());

        System.out.println("corruptedScore: " + corruptedLineScore);
        System.out.println("uncompletedScore: " + uncompletedScores);
        System.out.println("\t middle uncompleted Score: " + uncompletedScores.get(uncompletedScores.size() / 2));
    }

    private static boolean isOpenTag(char c) {
        return c == '(' || c == '[' || c == '{' || c == '<';
    }

    private static boolean matchingTags(char open, char close) {
        return (open == '(' && close == ')') || (open == '[' && close == ']') ||
                (open == '{' && close == '}') || (open == '<' && close == '>');
    }

    private static char getMatchingClosingTag(char open) {
        switch (open) {
            case '(' -> { return ')'; }
            case '[' -> { return ']'; }
            case '{' -> { return '}'; }
            case '<' -> { return '>'; }
        }
        return ' ';
    }
}
