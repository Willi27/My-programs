package RandomNumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

// класс релизующий логику игры
class GameRandomNumbers {
    private static Playeer rival;       // объект сопрника(игрок-компьюер)
    private static Playeer playeer;     // объект игрока(пользователь)
    private static volatile boolean isFinish = false;   // флаг указывающий на наличие поедителя
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    static {
        rival = new Rival();
        playeer = new MyPlayeer();

        // получаем имя игрока(пользователя)
        System.out.println("Введите ваше имя: ");
        String playeerName = null;
        try {
            playeerName = reader.readLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        // проверяем полученное имя на корректность
        if (playeerName.length() < 2 || playeerName == null || playeerName.equals("Computer")) {
            System.out.println("Вы ввели некорректное значение.");
            System.out.println("Ваше имя \"New Playeer\".");    // присваиваем игроку(пользователю) имя по умолчанию
            playeerName = "New Playeer";
            playeer.setName(playeerName);
        } else playeer.setName(playeerName);

        // получаем секретное число игрока(пользователя)
        System.out.println("Введите ваше секретное число от 1 до 10: ");
        int playeerNumber = 0;
        try {
            playeerNumber = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        if (playeerNumber < 1 || playeerNumber > 10) {
            System.out.println("Вы ввели некорректное значение.");
            System.out.println("Ваше секретное число будет сгенерировано автоматически.\n");    // не меняем число, установленное по умолчанию
        } else playeer.setNumber(playeerNumber);
    }

    // метод для печати имен и чисел игроков
    public void printNames() {      // испольуется только для тестов
        System.out.println(rival.getName() + " " + rival.getNumber());
        System.out.println(playeer.getName() + " " + playeer.getNumber());
    }

    private static void playeerMakesTheMove() {     // метод отвечающий за логику хода игрока(пользователя)
        System.out.println("Какое число загадал соперник?");
        System.out.println("Введите число от 1 до 10: ");
        int number = 1;

        try {
            number = Integer.parseInt(reader.readLine());   // игрок(пользователь вводит число)
        } catch (NumberFormatException nfe) {
            System.out.println("Ошибка, вы ввели некорректное значентие.");
            System.out.println("Следующий ход выполняет соперник.\n");
            nfe.printStackTrace();
            return;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        if (rival.getNumber() == number) {      // если число пользователя == секретному числу соперника(компьютера) - победа!
            System.out.println("Победа! Вы угадали число соперника!");
            System.out.println(String.format("Это число %d.", number));
            isFinish = true;                    // флаг указывающий на наличие поедителя изменяется на true
        } else {                                // если число пользователя != секретному числу соперника(компьютера) - игра продолжается
            System.out.println("Вы не угадали.");
            System.out.println("Следующий ход выполняет соперник.\n");
        }

    }

    private static void rivalMakeTheMove() {       // метод отвечающий за логику хода сперника(компьютера)
        int number = new Random().nextInt(10) + 1;
        System.out.println(String.format("Соперник ввел число %d.", number));

        if (playeer.getNumber() == number) {        // если число соперника(компьютера) == секретному числу игрока(пользователя) - победа!
            System.out.println("Соперник угадал ваше число!");
            System.out.print(String.format("Это число %d.", number));
            isFinish = true;                        // флаг указывающий на наличие поедителя изменяется на true
        } else {                                    // если число соперника(компьютера) != секретному числу игрока(пользователя) - игра продолжается
            System.out.println("Соперник не угадал ваше число.\n");
            System.out.println("Ваш ход:");
        }
    }

    // метод отвечающий за последовательность выполения ходов и закрытие потока чтения(BufferReader)
    public void startGame() {
        while (!isFinish) {     // игроки поочередно угадывают число пока не будет выявлен победитель
            playeerMakesTheMove();
            if (!isFinish) rivalMakeTheMove();
            else break;
        }

        try {                   // закрытие потока чтения(BufferReader)
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
