/**
 * @Willi27
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Divisor {
    private static int x;
    private static int y;
    private static List<Integer> nodListOne = new ArrayList<>();
    private static List<Integer> nodListTwo = new ArrayList<>();

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            try {
                x = Integer.parseInt(reader.readLine());
                y = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("Input number");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        nodSeach(x, nodListOne);
        nodSeach(y, nodListTwo);
        nodResult(nodListOne, nodListTwo);
    }

    private static void nodSeach(int nunber, List<Integer> list) {
        for (int i = 1; i <= nunber; i++) {
            int result;
            if (nunber % i == 0) list.add(nunber / i);
            else continue;
        }
    }

    private static void nodResult(List<Integer> listOne, List<Integer> listTwo) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < listOne.size() - 1; i++) {
            if (listTwo.contains(listOne.get(i))) {
                result.add(listOne.get(i));
            }
            else continue;
        }

        if (result.size() == 0) {
            System.out.println("NOD: 1");
            return;
        }
        Collections.sort(result);
        System.out.printf("NOD: %d", result.get(result.size()-1));
    }
}
