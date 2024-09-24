package task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* 
Транзакционность
Сделать метод joinData транзакционным, т.е. если произошел сбой, то данные не должны быть изменены.
1. Считать с консоли 2 имени файла.
2. Считать построчно данные из файлов. Из первого файла - в allLines, из второго - в forRemoveLines.
В методе joinData:
3. Если список allLines содержит все строки из forRemoveLines, то удалить из списка allLines все строки, которые есть в forRemoveLines.
4. Если условие из п.3 не выполнено, то:
4.1. очистить allLines от данных
4.2. выбросить исключение CorruptedDataException
Метод joinData должен вызываться в main. Все исключения обработайте в методе main.
Не забудь закрыть потоки.


Requirements:
1. Класс Solution должен содержать public static поле allLines типа List<String>.
2. Класс Solution должен содержать public static поле forRemoveLines типа List<String>.
3. Класс Solution должен содержать public void метод joinData() который может бросать исключение CorruptedDataException.
4. Программа должна считывать c консоли имена двух файлов.
5. Программа должна считывать построчно данные из первого файла в список allLines.
6. Программа должна считывать построчно данные из второго файла в список forRemoveLines.
7. Метод joinData должен удалить в списке allLines все строки из списка forRemoveLines, если в allLines содержаться все строки из списка forRemoveLines.
8. Метод joinData должен очистить список allLines и выбросить исключение CorruptedDataException, если в allLines не содержаться все строки из списка forRemoveLines.
9. Метод joinData должен вызываться в main.
*/


public class Solution {
    public static List<String> allLines = new ArrayList<>();
    public static List<String> forRemoveLines = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir")); // выведет текущий путь от корня диска до корня проекта
        final String path = System.getProperty("user.dir") + "/07 Знакомство с нитями synchronized, volatile, yield, CRUD, Comparble, Транзакционность (Мультипоточка-1)/task1721(Transaction)/";

        System.out.println("Введите название файла с данными (источника) и файла с данными для удаления.\n"
                + "ПОДСКАЗКА: Сейчас нужно ввести '1.txt' и '2.txt' соответственно.");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader1 = new BufferedReader(new FileReader(path + reader.readLine()));
             BufferedReader fileReader2 = new BufferedReader(new FileReader(path + reader.readLine()))) {
            String line;

            while ((line = fileReader1.readLine()) != null) {
                allLines.add(line); // Читаем строки из первого файла в allLines
            }

            while ((line = fileReader2.readLine()) != null) {
                forRemoveLines.add(line); // Читаем строки из второго файла в forRemoveLines
            }

            new Solution().joinData(); // Вызываем метод joinData
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n\tallLines:");
        allLines.stream().forEach(System.out::println);

        System.out.println("\n\tforRemoveLines:");
        forRemoveLines.stream().forEach(System.out::println);
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            String allLinesString = allLines.stream().collect(Collectors.joining("\n")); // Преобразование с разделением пробелом
            String forRemoveLinesString = forRemoveLines.stream().collect(Collectors.joining("\n")); // Преобразование с разделением пробелом

            throw new CorruptedDataException("allLines = " + allLinesString + ",\n forRemoveLines = " + forRemoveLinesString); // запишу в исключение всю информацию
        }
    }
}
