package RandomNumbers;

/*
@Author: Willi27
 */

import java.util.Random;

abstract class AbstractPlayeer {
    private String name;    // имя игрока
    private int number;     // число загаданное игроком

    public AbstractPlayeer() {      // автоматическая генерациячисла от 1до 10
        number = new Random().nextInt(10) +1;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
