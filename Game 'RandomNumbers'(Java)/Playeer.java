package RandomNumbers;

import java.util.Random;

abstract class Playeer {
    private String name;
    private int number;

    public Playeer() {
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
}
