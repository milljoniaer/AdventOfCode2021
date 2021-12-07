import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 extends DAY {
    public static void main(String[] args) {
        List<Integer> input = Arrays.stream(readFromResFile("Day6.txt").get(0).split(",")).
                map((String s) -> Integer.parseInt(s.replaceAll(" ", ""))).
                collect(Collectors.toList());

        System.out.println(input);

        long[] fish = new long[9];

        for (int n : input) {
            fish[n] = fish[n] + 1;
        }
        System.out.println(Arrays.toString(fish));

        for (int i = 0; i < 256; i++) {
            long[] newfish = new long[9];
            newfish[0] = fish[1];
            newfish[1] = fish[2];
            newfish[2] = fish[3];
            newfish[3] = fish[4];
            newfish[4] = fish[5];
            newfish[5] = fish[6];
            newfish[6] = fish[7] + fish[0];
            newfish[7] = fish[8];
            newfish[8] = fish[0];

            fish = newfish;
            System.out.println(Arrays.toString(fish));
        }

        long size = 0;
        for (long f : fish) {
            size = size + f;
        }
        System.out.println(size);
    }

    private static void partOne() {
        List<Integer> fish = Arrays.stream(readFromResFile("Day6.txt").get(0).replaceAll(" ","").split(","))
                .map((String s) -> Integer.parseInt(s))
                .collect(Collectors.toList());

        for (int i = 0; i < 256; i++) {
            int curSize = fish.size();
            for (int h=0; h < curSize; h++) {
                int f = fish.get(h);
                f--;
                if (f == -1) {
                    fish.add(8);
                    f = 6;
                }
                fish.set(h, f);
            }
        }

        System.out.println(fish);
        System.out.println(fish.size());

        write("day-6-out100.txt", fish.toString().replaceAll("\\[", "").replaceAll("]", ""));
    }
}
