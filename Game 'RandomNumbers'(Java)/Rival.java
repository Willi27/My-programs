package RandomNumbers;

// класс-наследник абстрактного класса "Player"
// реализует соперника(игрок-компьютер)
class Rival extends Playeer {
    private String name = "Computer";   // имеет заданое по умолчанию имя

    // убрать этот метод, т.к. он реализован родительском классе
    @Override
    public String getName() {
        return name;
    }
}
