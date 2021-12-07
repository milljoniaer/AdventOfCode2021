import java.util.List;

public class Day2 extends DAY {

    private static final boolean TEST_MODE = false;

    public static void main(String[] args) {
        partTwo();
    }

    private static void partOne() {
        List<String> commands = readFromResFile("Day2" + (TEST_MODE ? "-test" : "") + ".txt");
        int horizontal = 0;
        int depth = 0;

        for (String s : commands) {
            if (s.matches("forward \\d+")) {
                horizontal = horizontal + Integer.valueOf(s.replaceAll("forward (\\d+)", "$1"));
            } else if (s.matches("down (\\d+)")) {
                depth = depth + Integer.valueOf(s.replaceAll("down (\\d+)", "$1"));
            } else if (s.matches(("up (\\d+)"))) {
                depth = depth - Integer.valueOf(s.replaceAll("up (\\d+)", "$1"));
            }
        }

        System.out.println("horizontal=" + horizontal + ", depth=" + depth + System.lineSeparator() + "horizontal*depth=" + (horizontal*depth));
    }

    private static void partTwo() {
        List<String> commands = readFromResFile("Day2" + (TEST_MODE ? "-test" : "") + ".txt");
        int horizontal = 0;
        int depth = 0;
        int aim = 0;

        for (String s : commands) {
            if (s.matches("forward \\d+")) {
                int d = Integer.valueOf(s.replaceAll("forward (\\d+)", "$1"));
                horizontal = horizontal + d;
                depth = depth + (aim * d);
            } else if (s.matches("down (\\d+)")) {
                aim = aim + Integer.valueOf(s.replaceAll("down (\\d+)", "$1"));
            } else if (s.matches(("up (\\d+)"))) {
                aim = aim - Integer.valueOf(s.replaceAll("up (\\d+)", "$1"));
            }
        }

        System.out.println("horizontal=" + horizontal + ", depth=" + depth + System.lineSeparator() + "horizontal*depth=" + (horizontal*depth));
    }
}
