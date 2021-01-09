package BatchCRrUDProgram;

/*
Create by Willi27
*/

import java.time.LocalDate;

/*
* Объект @Person содержит поля:
* имя, пол, дата рождения.
* В классе используется API даты и времени java.time.LocalDate.
 * */
class Person {
    private String name;
    private Sex sex;
    private LocalDate birthDate;

    private Person(String name, Sex sex, LocalDate birthDate) {
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public static Person createMale(String name, LocalDate birthDate) {
        return new Person(name, Sex.MALE, birthDate);
    }

    public static Person createFemale(String name, LocalDate birthDate) {
        return new Person(name, Sex.FEMALE, birthDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s-%s-%s", name, sex,
                birthDate.getDayOfMonth(),
                birthDate.getMonth(),
                birthDate.getYear());
    }
}
