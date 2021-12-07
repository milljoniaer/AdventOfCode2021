import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Day5 extends DAY {
    private static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "(" + x + "|" + y + ")";
        }
    }


    public static void main(String[] args) {
        List<String> input = readFromResFile("Day5.txt");

        List<Point> markedPointsMulti = new ArrayList<>();
        for (String s : input) {
            int x1 = Integer.parseInt(s.replaceAll("(\\d+),(\\d+) -> (\\d+),(\\d+)", "$1"));
            int y1 = Integer.parseInt(s.replaceAll("(\\d+),(\\d+) -> (\\d+),(\\d+)", "$2"));
            int x2 = Integer.parseInt(s.replaceAll("(\\d+),(\\d+) -> (\\d+),(\\d+)", "$3"));
            int y2 = Integer.parseInt(s.replaceAll("(\\d+),(\\d+) -> (\\d+),(\\d+)", "$4"));

            markedPointsMulti.addAll(makeLine(new Point(x1,y1), new Point(x2,y2)));
        }

        List<Integer> multiplicities = new ArrayList<>();

        for (Point a : markedPointsMulti.stream().distinct().collect(Collectors.toList())) {
            int count = 0;
            for (Point b : markedPointsMulti) {
                if (a.equals(b)) count++;
            }
            multiplicities.add(count);
        }

        // System.out.println(markedPointsMulti);
        // System.out.println(multiplicities);
        System.out.println(multiplicities.stream().filter((Integer i) -> i >= 2).collect(Collectors.toList()).size());
    }

    private static List<Point> makeLine(Point a, Point b) {
        List<Point> points = new ArrayList<>();
        if (a.x == b.x) {
            if (a.y < b.y) {
                for (int i=0; i<=(b.y-a.y); i++)
                    points.add(new Point(a.x, a.y+i));
            } else {
                for (int i=0; i<=(a.y-b.y); i++)
                    points.add(new Point(a.x, b.y+i));
            }
        } else if (a.y == b.y) {
            if (a.x < b.x) {
                for (int i=0; i<=(b.x-a.x); i++)
                    points.add(new Point(a.x+i, a.y));
            } else {
                for (int i=0; i<=(a.x-b.x); i++)
                    points.add(new Point(b.x+i, a.y));
            }
        } else {
            if (a.x < b.x) {
                if (a.y < b.y)
                    for (int i=0; i<=(b.x-a.x); i++) {
                        points.add(new Point(a.x + i, a.y + i));
                    }
                else
                    for (int i=0; i<=(b.x-a.x); i++) {
                        points.add(new Point(a.x + i, a.y - i));
                    }
            } else {
                if (a.y < b.y)
                    for (int i=0; i<=(a.x-b.x); i++) {
                        points.add(new Point(a.x - i, a.y + i));
                    }
                else
                    for (int i=0; i<=(a.x-b.x); i++) {
                        points.add(new Point(a.x - i, a.y - i));
                    }
            }
        }
        return points;
    }
}
