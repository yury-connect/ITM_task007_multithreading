package task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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


public class Solution {
    public static List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        switch (args[0]) {
            case "-c": // добавление нового человека
                String name = args[1];
                Sex sex = "м".equals(args[2]) ? Sex.MALE : Sex.FEMALE;
                Date birthDate = dateFormat.parse(args[3]);
                Person person = sex == Sex.MALE ? Person.createMale(name, birthDate) : Person.createFemale(name, birthDate);
                allPeople.add(person);
                System.out.println(allPeople.size() - 1); // выводим индекс нового человека
                break;

            case "-r": // чтение данных о человеке
                int id = Integer.parseInt(args[1]);
                Person readPerson = allPeople.get(id);
                String sexString = readPerson.getSex() == Sex.MALE ? "м" : "ж";
                System.out.println(readPerson.getName() + " " + sexString + " " + outputDateFormat.format(readPerson.getBirthDate()));
                break;

            case "-u": // обновление данных человека
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
    }
}





/*
Для реализации функционала CRUD в программе, в классе Solution нужно дополнить метод main
логикой для обработки команд -c, -r, -u, -d. Давайте пошагово разберём, как добавить нужный функционал.


 */
