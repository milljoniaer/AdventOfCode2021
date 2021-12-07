import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 extends DAY {
    public static void main(String[] args) {
        partOne();
        partTwo();
    }

    private static void partTwo() {
        System.out.println("------PartTwo------");

        List<String> input = readFromResFile("Day3.txt");

        List<String> oxygen = new ArrayList<>(input);
        List<String> co2 = new ArrayList<>(input);

        for (int i = 0; i < input.size() && oxygen.size() > 1; i++) {
            int numberOfOnes = 0;
            for (int j = 0; j< oxygen.size(); j++) {
                if (oxygen.get(j).charAt(i) == '1') numberOfOnes++;
            }

            char c;

            if (numberOfOnes >= (oxygen.size()-numberOfOnes))
                c = '1';
            else
                c = '0';

            int finalI = i;
            oxygen=oxygen.stream().filter((String s) -> s.charAt(finalI) == c).collect(Collectors.toList());

            // System.out.println(oxygen);
        }

        for (int i = 0; i < input.size() && co2.size() > 1; i++) {
            int numberOfOnes = 0;
            for (int j = 0; j< co2.size(); j++) {
                if (co2.get(j).charAt(i) == '1') numberOfOnes++;
            }

            char c;

            if (numberOfOnes < (co2.size()-numberOfOnes))
                c = '1';
            else
                c = '0';

            int finalI = i;
            co2=co2.stream().filter((String s) -> s.charAt(finalI) == c).collect(Collectors.toList());
        }
        int intOxy = Integer.parseInt(oxygen.get(0), 2);
        int intCo2 = Integer.parseInt(co2.get(0), 2);

        System.out.println("Binary: oxygen=" + oxygen.get(0) + ", co2="+co2.get(0));
        System.out.println("Decimal: oxygen=" + intOxy + ", co2="+intCo2);
        System.out.println("oxygen*co2="+(intOxy*intCo2));
    }

    private static void partOne() {
        System.out.println("------PartOne------");
        List<String> input = readFromResFile("Day3.txt");

        int[] binary = new int[input.get(0).length()];

        for (String s : input) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i)=='1') binary[i] = binary[i] + 1;
            }
        }

        StringBuilder gammaRateS = new StringBuilder();
        StringBuilder epsilonRateS = new StringBuilder();

        for (int i=0; i < binary.length; i++) {
            if (binary[i] > (input.size()/2)) {
                gammaRateS.append("1");
                epsilonRateS.append("0");
            } else {
                gammaRateS.append("0");
                epsilonRateS.append("1");
            }
        }

        int gammaRate = Integer.parseInt(gammaRateS.toString(), 2);
        int epsilonRate = Integer.parseInt(epsilonRateS.toString(), 2);

        System.out.println("Binary: gammaRate="+gammaRateS + ", epsilonRate="+epsilonRateS);
        System.out.println("Decimal: gammaRate="+gammaRate + ", epsilonRate=" + epsilonRate);
        System.out.println("gammaRate*epsilonRate=" + (gammaRate*epsilonRate));


    }
}
