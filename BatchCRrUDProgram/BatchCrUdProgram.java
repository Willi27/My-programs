package BatchCRrUDProgram;

/*
Create by Willi27
*/

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

/*
* Программа запускается с одним из следующих наборов параметров:
-c name1 sex1 bd1 name2 sex2 bd2 ... добавляет всех людей с заданными параметрами в
   конец allPeople, выводит id (index) на экран в соответствующем порядке.
-u id1 name1 sex1 bd1 id2 name2 sex2 bd2 ... обновляет соответствующие данные людей с заданными id.
-d id1 id2 id3 id4 ...  производит логическое удаление человека с id, заменяет все его данные на null.
-i id1 id2 id3 id4 ... ыводит на экран информацию о всех людях с заданными id: name sex bd.
* */

public class BatchCrUdProgram {
    private static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        // В статическом блоке добавлены 2 объекта для тестирования.
        allPeople.add(Person.createFemale("Elen Smith", LocalDate.now()));
        allPeople.add(Person.createMale("Bill Jobs", LocalDate.now()));
    }

    public static void executeQuery(String[] arguments) {
        if (arguments == null || arguments.length < 1) throw new IllegalArgumentException();
        Person person = null;

        switch (arguments[0]) {
            case "c":
                for (int i = 1; i < arguments.length; i+=3) {
                    int[] intDate = Arrays.stream(arguments[i + 2].split("/")).mapToInt(Integer::parseInt).toArray();
                    LocalDate localDate = LocalDate.of(intDate[2], intDate[1], intDate[0]);

                    if (arguments[i + 1].equals("M")) allPeople.add(Person.createMale(arguments[i], localDate));
                    else if(arguments[i + 1].equals("F")) allPeople.add(Person.createFemale(arguments[i], localDate));

                    System.out.printf("ID = %d: %s\n", (allPeople.size() - 1), (allPeople.get(allPeople.size()-1)));
                }
                break;

            case "u":
                for (int i = 1; i < arguments.length; i+=4) {
                    person = allPeople.get(Integer.parseInt(arguments[i]));
                    person.setName(arguments[i+1]);

                    if (arguments[i+2].equals("M")) person.setSex(Sex.MALE);
                    else if (arguments[i+2].equals("F")) person.setSex(Sex.FEMALE);

                    int[] intDate = Arrays.stream(arguments[i + 3].split("/")).mapToInt(Integer::parseInt).toArray();
                    person.setBirthDate(LocalDate.of(intDate[2], intDate[1], intDate[0]));

                    System.out.printf("ID = %s: %s\n", arguments[i], (allPeople.get(Integer.parseInt(arguments[i]))));
                }
                break;

            case "d":
                for (int i = 1; i < arguments.length; i++) {
                    try {
                        person = allPeople.get(Integer.parseInt(arguments[i]));
                        person.setName(null);
                        person.setSex(null);
                        person.setBirthDate(null);

                        person.toString();
                    } catch (NullPointerException ignore) {
                        System.out.println("Object is removed");
                    }
                }
                break;

            case "i":
                for (int i = 1; i < arguments.length; i++) {
                    person = allPeople.get(Integer.parseInt(arguments[i]));

                    String name = person.getName();
                    String sex = person.getSex().toString().equals("MALE") ? "M" : "F";
                    StringJoiner birthday = new StringJoiner("-");
                    birthday.add(String.valueOf(person.getBirthDate().getDayOfMonth())).
                            add(String.valueOf(person.getBirthDate().getMonth())).
                            add(String.valueOf(person.getBirthDate().getYear()));

                    System.out.println(name + " " +  sex + " " + birthday);
                }
                break;

            default: {
                System.out.println("Incorrect parameter");
            }
        }
    }
}
