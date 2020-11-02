/**
 * @Willi27
 */

package javaRash.Lev5;

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
    private static boolean negativeOrPositive;

    // публичный метод для пользовтеля
    public static int nod() {
        inputNumbers();     // ввод чисел
        negativeOrPositive(x, y);     // проверка положительная пара или отрицательная
        nodSeach(x, nodListOne);     // поиск делителей для первого числа
        nodSeach(y, nodListTwo);     // поиск делителей для второго числа
        return nodResult(nodListOne, nodListTwo);     // поиск наибольшего общего делителя для двух чисел
    }

    // ввод с клавиатуры 2х чисел
    private static void inputNumbers() {
        try (BufferedReader readNumber = new BufferedReader(new InputStreamReader(System.in))) {
            try {
                x = Integer.parseInt(readNumber.readLine());
                y = Integer.parseInt(readNumber.readLine());
            } catch (NumberFormatException e) {     // проверка на соответствие типу "int"
                System.out.println("Неверное значение, повторите ввод числа.");
                e.printStackTrace();
                System.exit(0);
            }

            if ((x < 0 && y >= 0) || (y < 0 && x >= 0)) {       // проверка чисел: 2 числа положительные или 2 чмсла отрицательные
                System.out.println("Нахождение НОД невозможно - одно из чисел отрицательное.");
                System.out.println("Ведите два отрицательных числа или два положительных.");
                System.exit(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // проверка пары чисел(положительные или отрицательные)
    private static void negativeOrPositive(int x, int y) {
        if (x < 0 && y < 0) {
            Divisor.x = Math.abs(x);
            Divisor.y = Math.abs(y);
            negativeOrPositive = true;  // изменение значения флага
        }
        else negativeOrPositive = false;
    }

    // поиск все делителей числа "number"
    private static void nodSeach(int nunber, List<Integer> list) {
        for (int i = 1; i <= nunber; i++) {
            int result;
            if (nunber % i == 0) list.add(nunber / i);     // добавление в список только тех делителей, которые при деление не дают остаток
            else continue;
        }
    }

    // сравнение двух списков делителей и нахождение наибольшего
    private static int nodResult(List<Integer> listOne, List<Integer> listTwo) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < listOne.size() - 1; i++) {
            if (listTwo.contains(listOne.get(i))) {  // поиск одинаковых делителей
                result.add(listOne.get(i));
            }
            else continue;
        }

        if (result.size() == 0) {  // если список пустой(т.е. одинаковых делителей нет) - НОД = 1
//            System.out.println("NOD: 1");
            return 1;
        }

        Collections.sort(result);  // сортировка списка
        if (negativeOrPositive) {  // если флаг = true(оба числа отрицательные), - НОД отрицательный
//            System.out.printf("NOD: %d", (result.get(result.size()-1))*-1);
            return result.get(result.size()-1)*-1;
        }
//        System.out.printf("NOD: %d", result.get(result.size()-1));
        return result.get(result.size()-1);
    }
}
