package task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD
CRUD - Create, Read, Update, Delete.

Программа запускается с одним из следующих наборов параметров:
-c name sex bd
-r id
-u id name sex bd
-d id

Значения параметров:
name - имя, String
sex - пол, "м" или "ж", одна буква
bd - дата рождения в следующем формате 15/04/1990
-c - добавляет человека с заданными параметрами в конец allPeople, выводит id (index) на экран
-r - выводит на экран информацию о человеке с id: name sex (м/ж) bd (формат 15-Apr-1990)
-u - обновляет данные человека с данным id
-d - производит логическое удаление человека с id, заменяет все его данные на null

id соответствует индексу в списке.
Все люди должны храниться в allPeople.
Используй Locale.ENGLISH в качестве второго параметра для SimpleDateFormat.

Пример параметров:
-c Миронов м 15/04/1990

Пример вывода для параметра -r:
Миронов м 15-Apr-1990

Если программа запущена с параметрами, то они попадают в массив args (аргумент метода main - String[] args).
Например, при запуске программы c параметрами:
-c name sex bd
получим в методе main
args[0] = "-c"
args[1] = "name"
args[2] = "sex"
args[3] = "bd"
Для запуска кода с параметрами в IDE IntellijIDEA нужно их прописать в поле Program arguments в меню Run -> Edit Configurations.


Requirements:
1. Класс Solution должен содержать public static поле allPeople типа List<Person>.
2. Класс Solution должен содержать статический блок, в котором добавляются два человека в список allPeople.
3. При запуске программы с параметром -с программа должна добавлять человека с заданными параметрами в конец списка allPeople, и выводить id (index) на экран.
4. При запуске программы с параметром -r программа должна выводить на экран данные о человеке с заданным id по формату указанному в задании.
5. При запуске программы с параметром -u программа должна обновлять данные человека с заданным id в списке allPeople.
6. При запуске программы с параметром -d программа должна логически удалять человека с заданным id в списке allPeople.
*/

/*
-c Пугачева_Алла ж 4/10/56

 */
public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
        allPeople.add(Person.createMale("Сидоров Сидр", new Date(45, 04, 9)));  //родился 09/05/1945    id=2
        allPeople.add(Person.createMale("Ленин Владимир", new Date(33, 10, 27)));  // родился 27/11/1933    id=3
        allPeople.add(Person.createMale("Сталин Иосиф", new Date(11, 11, 31)));  //родился 31/12/1911   id=4
    }

    public static void main(String[] args) throws ParseException {
        //напишите тут ваш код
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        System.out.println("\tВывожу базовый (исходный) список людей:");
        print();

        switch (args[0]) {
            case "-c": // добавление нового человека
                System.out.println("\t Create.");
                String name = args[1];
                Sex sex = "м".equals(args[2].toLowerCase().trim()) ? Sex.MALE : Sex.FEMALE;
                Date birthDate = dateFormat.parse(args[3]);
                Person person = sex == Sex.MALE ? Person.createMale(name, birthDate) : Person.createFemale(name, birthDate);
                allPeople.add(person);
                System.out.println("\tДобавлена персона:\n\t" + person);
                System.out.println(allPeople.size() - 1); // выводим индекс нового человека
                break;

            case "-r": // чтение данных о человеке
                System.out.println("\t Read.");
                int id = Integer.parseInt(args[1]);
                Person readPerson = allPeople.get(id);
                String sexString = readPerson.getSex() == Sex.MALE ? "м" : "ж";
                System.out.println(readPerson.getName() + " " + sexString + " " + outputDateFormat.format(readPerson.getBirthDate()));
                break;

            case "-u": // обновление данных человека
                System.out.println("\t Update.");
                id = Integer.parseInt(args[1]);
                name = args[2];
                sex = "м".equals(args[3]) ? Sex.MALE : Sex.FEMALE;
                birthDate = dateFormat.parse(args[4]);
                Person updatePerson = allPeople.get(id);
                updatePerson.setName(name);
                updatePerson.setSex(sex);
                updatePerson.setBirthDate(birthDate);
                break;

            case "-d": // логическое удаление человека
                System.out.println("\t Delete.");
                id = Integer.parseInt(args[1]);
                Person deletePerson = allPeople.get(id);
                deletePerson.setName(null);
                deletePerson.setSex(null);
                deletePerson.setBirthDate(null);
                break;

            default:
                System.out.println("Неверный параметр");
                break;
        }


        System.out.println("\n\tВывожу список людей после обработки:");
        print();
    }

    public static void print() {
        allPeople.stream().forEach(person -> System.out.println(person));
    }
}
