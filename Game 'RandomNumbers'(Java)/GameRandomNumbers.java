package RandomNumbers;

/*
@Author: Willi27
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/*
*  Класс реалтзует логику игры.
* В игре есть 2 режима: "Игрок - Игрок" и "Игрок - Компьютер".
* - Режим "Игрок - Игрок" расчитан на 2х пользователей,
* которые поочередно выполняют ход.
* - Режим "Игрок - Компьютер" расчитан на 1 игрока, играющего
* против компьютера(ходы выполняются поочередно).
* */
class GameRandomNumbers {
    private String gameMode;                        // режим игры
    private AbstractPlayeer myRival;                // объект игрока-сопрника
    private AbstractPlayeer myPlayer;               // объект игрока-пользователя
    private volatile boolean isFinish = false;      // флаг указывающий на наличие поедителя
    private BufferedReader reader;


    public GameRandomNumbers() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("\"Игрок - Игрок\": введите \"A\".\n" +
                        "\"Игрок - Компьютер\": введите \"B\".\n" +
                        "Выберите режим игры: ");
        try {
            gameMode = reader.readLine().toUpperCase();
            if (!gameMode.equals("A") && !gameMode.equals("B")) gameMode = "B";
        } catch (IOException e) {
            System.out.println("Вы ввели некорректное значение.");
            System.out.println("Выбран режим по умолчанию \"Игрок - Компьютер\"");
            gameMode = "B";
        }
        System.out.println("Игра начинается!\n");
        createGame();
    }

    private void createGame() {
        if (gameMode.equals("A")) {
            myPlayer = new Playeer();
            myRival = new Playeer();

            System.out.println("Создаем первого игрока.");
            creteMyPlayeer(myPlayer, "-1");

            System.out.println("Создаем второго игрока.");
            creteMyPlayeer(myRival, "-2");
        }
        else if (gameMode.equals("B")) {
            myPlayer = new Playeer();
            myRival = new RivalComputer();

            creteMyPlayeer(myPlayer, "");
        }
    }

    private void creteMyPlayeer(AbstractPlayeer currentPlayeer, String index) {
        // получаем имя игрока(пользователя)
        System.out.print("Введите ваше имя: ");
        String playeerName = null;
        try {
            playeerName = reader.readLine();
        } catch (IOException ioe) {
//            ioe.printStackTrace();
        }

        // проверяем полученное имя на корректность
        if (playeerName.length() < 2 || playeerName == null || playeerName.equals("Computer")) {
            System.out.println("Вы ввели некорректное значение.");
            System.out.printf("Ваше имя \"New Playeer%s\".\n", index);    // присваиваем игроку(пользователю) имя по умолчанию
            playeerName = "New Playeer" + index;
            currentPlayeer.setName(playeerName);
        } else currentPlayeer.setName(playeerName);

        // получаем секретное число игрока(пользователя)
        System.out.print("Введите ваше секретное число от 1 до 10: ");
        int random = new Random().nextInt(10) +1;
        int playeerNumber = random;

        try {
            playeerNumber = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException nfe) {
//            nfe.printStackTrace();
            System.out.println("Вы ввели некорректное значение.");
            System.out.printf("Ваше секретное число сгенерировано автоматически(%d).\n", playeerNumber);    // не меняем число, установленное по умолчанию
            currentPlayeer.setNumber(playeerNumber);
        } catch (IOException ioe) {
//            ioe.printStackTrace();
        }

        if (playeerNumber < 1 || playeerNumber > 10) {
            System.out.println("Вы ввели некорректное значение.");
            System.out.printf("Ваше секретное число сгенерировано автоматически(%d).\n", random);    // не меняем число, установленное по умолчанию
            currentPlayeer.setNumber(random);
        } else currentPlayeer.setNumber(playeerNumber);
        System.out.println("");
    }

    // метод отвечающий за логику ходов игроков.
    private void playeerMakesTheMove(AbstractPlayeer myPlayer, AbstractPlayeer myRival) {
        System.out.printf("%s, какое число загадал соперник?\n", myPlayer.getName());
        System.out.print("Введите число от 1 до 10: ");
        int number = 1;

        try {
            number = Integer.parseInt(reader.readLine());   // игрок(пользователь вводит число)
        } catch (NumberFormatException nfe) {
            System.out.println("Ошибка, вы ввели некорректное значентие.");
            System.out.println("Следующий ход выполняет соперник.\n");
//            nfe.printStackTrace();
            return;
        } catch (IOException ioe) {
//            ioe.printStackTrace();
        }

        if (myRival.getNumber() == number) {      // если число пользователя == секретному числу соперника - победа!
            System.out.printf("Победа! %s, Вы угадали число соперника!\n", myPlayer.getName());
            System.out.println(String.format("Это число %d.", number));
            isFinish = true;                    // флаг указывающий на наличие поедителя изменяется на true
        } else {                                // если число пользователя != секретному числу соперника(компьютера) - игра продолжается
            System.out.println("Вы не угадали.");
            System.out.println("Следующий ход выполняет соперник.\n");
        }
    }

    // метод отвечающий за логику хода игрока-соперника(компьютер).
    private void rivalMakeTheMove() {
        int number = new Random().nextInt(10) + 1;
        System.out.println(String.format("Соперник ввел число %d.", number));

        if (myPlayer.getNumber() == number) {        // если число соперника(компьютера) == секретному числу игрока(пользователя) - победа!
            System.out.println("Соперник угадал ваше число!");
            System.out.print(String.format("Это число %d.", number));
            isFinish = true;                        // флаг указывающий на наличие поедителя изменяется на true
        } else {                                    // если число соперника(компьютера) != секретному числу игрока(пользователя) - игра продолжается
            System.out.println("Соперник не угадал ваше число.\n");
            System.out.println("Ваш ход:");
        }
    }

    // метод отвечающий за последовательность выполения ходов и закрытие потока чтения(BufferReader).
    public void startGame() {
        if (gameMode.equals("A")) {
            while (!isFinish) {     // игроки поочередно угадывают число пока не будет выявлен победитель
                playeerMakesTheMove(myPlayer, myRival);
                if (!isFinish) playeerMakesTheMove(myRival, myPlayer);
            }
        }
        else if (gameMode.equals("B")) {
            while (!isFinish) {     // игроки поочередно угадывают число пока не будет выявлен победитель
                playeerMakesTheMove(myPlayer, myRival);
                if (!isFinish) rivalMakeTheMove();
            }
        }

        try {                   // закрытие потока чтения(BufferReader)
            reader.close();
        } catch (IOException ignore) {
            /*NOP*/
        }
    }
}
