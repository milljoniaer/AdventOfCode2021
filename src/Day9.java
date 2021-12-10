import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 extends DAY {
    public static void main(String[] args) {
        List<String> input = readFromResFile("Day9.txt");

        int [] [] heightMap = new int [input.get(0).length()][input.size()];

        for (int i = 0; i < input.size(); i++) {
            String curr = input.get(i);
            for (int h = 0; h < curr.length(); h++) {
                heightMap [h] [i] = Integer.parseInt(Character.toString(curr.charAt(h)));
            }
        }

        int risk = 0;
        for (int x = 0; x < heightMap.length; x++) {
            for (int y = 0; y < heightMap[0].length; y++) {
                int curr = heightMap[x][y];
                int defaultHeight = Integer.MAX_VALUE;
                int up=defaultHeight; int down=defaultHeight; int left=defaultHeight; int right=defaultHeight;

                if (y-1>=0) up = heightMap [x] [y-1];
                if (y+1<heightMap[0].length) down = heightMap [x] [y+1];
                if (x-1>=0) left = heightMap [x-1] [y];
                if (x+1<heightMap.length) right = heightMap [x+1] [y];

                // System.out.println("Curr: " + curr + ", up: " + up + ", down: " + down + ", left: " + left + ", right: " + right);

                if (curr < up && curr < down && curr < left && curr < right)
                    risk = risk + curr + 1;
            }
        }

        System.out.println(risk);

        // System.out.println(Arrays.stream(heightMap).map(((int[] a) -> Arrays.toString(a).replaceAll(",", "").replaceAll("\\[", "").replaceAll("]", ""))).collect(Collectors.joining(System.lineSeparator())));
    }
}
