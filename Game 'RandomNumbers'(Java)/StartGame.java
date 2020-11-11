package RandomNumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

class StartGame {
    private static Playeer rival;
    private static Playeer playeer;
    private static volatile boolean isFinish = false;

    static {
        rival = new Rival();
        playeer = new MyPlayeer();

        System.out.println("Введите ваше имя: ");
        BufferedReader nameReader= new BufferedReader(new InputStreamReader(System.in));
        String playeerName = "New Playeer";
        try {
            playeerName = nameReader.readLine();
            if (playeerName.length() < 2 || playeerName == null) {
                    System.out.println("Вы ввели некорректное значентие");
                    System.out.println("Повторите попытку");
                    playeerName = nameReader.readLine();
            } else playeer.setName(playeerName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void printNames() {
        System.out.println(rival.getName() + " " + rival.getNumber());
        System.out.println(playeer.getName() + " " + playeer.getNumber());
    }

    private static void playeerMakesTheMove() {
        BufferedReader numberReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Какое число загадал соперник?");
        System.out.println("Введите число от 1 до 10: ");
        int number = -1;

        try {
            number = Integer.parseInt(numberReader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка, вы ввели некорректное значентие.");
            System.out.println("Следующий ход выполняет соперник.\n");
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (rival.getNumber() == number) {
            System.out.println("Победа! Вы угадали число соперника!");
            System.out.println(String.format("Это число %d.", number));
            isFinish = true;
        } else {
            System.out.println("Вы не угадали.");
            System.out.println("Следующий ход выполняет соперник.\n");
        }

    }

    private static void rivalMakeTheMove() {
        int number = new Random().nextInt(10) + 1;
        System.out.println(String.format("Соперник ввел число %d.", number));

        if (playeer.getNumber() == number) {
            System.out.println("Соперник угадал ваше число!");
            System.out.print(String.format("Это число %d.", number));
            isFinish = true;
        } else {
            System.out.println("Соперник не угадал ваше число.\n");
            System.out.println("Ваш ход:");
        }
    }

    public void startGame() {
        while (!isFinish) {
            playeerMakesTheMove();
            if (!isFinish) rivalMakeTheMove();
            else break;
        }
    }
}
