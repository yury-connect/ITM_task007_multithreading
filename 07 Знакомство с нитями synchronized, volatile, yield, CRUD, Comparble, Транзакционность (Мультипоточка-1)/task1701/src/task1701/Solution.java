package task1701;

import java.util.ArrayList;
import java.util.List;

/*
Заметки
Асинхронность выполнения нитей.
1. Класс Note будет использоваться нитями.
2. Создай public static нить NoteThread (Runnable не является нитью), которая в методе run 1000 раз (index = 0-999) сделает следующие действия:
2.1. Используя метод addNote добавит заметку с именем [getName() + "-Note" + index], например, при index=4
"Thread-0-Note4"
2.2. Заснет на 1 миллисекунду
2.3. Используя метод removeNote удалит заметку
2.4. В качестве параметра в removeNote передай имя нити - метод getName()


Requirements:
1. Класс Solution должен содержать public static класс NoteThread.
2. Класс NoteThread должен быть нитью.
3. В методе run класса NoteThread должен быть цикл.
4. Метод run класса NoteThread должен 1000 раз вызывать метод addNote c параметром (getName() + "-Note" + index).
5. Метод run класса NoteThread должен 1000 раз вызывать Thread.sleep() c параметром (1).
6. Метод run класса NoteThread должен 1000 раз вызывать метод removeNote c параметром (getName()).
*/

public class Solution {
    public static void main(String[] args) {
        new NoteThread().start();
        new NoteThread().start();
    }

    public static class Note {

        public static final List<String> notes = new ArrayList<>();

        public static void addNote(String note) {
            notes.add(0, note);
        }

        public static void removeNote(String threadName) {
            String note = notes.remove(0);
            if (note == null) {
                System.out.println("Другая нить удалила нашу заметку");
            } else if (!note.startsWith(threadName)) {
                System.out.println("Нить [" + threadName + "] удалила чужую заметку [" + note + "]");
            } else {
                System.out.println("Нить [" + threadName + "] удалила свою заметку [" + note + "]");
            }
        }
    }

    public static class NoteThread extends Thread {
        @Override
        public void run() {
            for (int index = 0; index < 1000; index++) {
                // Добавляем заметку
                Note.addNote(getName() + "-Note" + index);

                try {
                    // Пауза на 1 миллисекунду
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Удаляем заметку
                Note.removeNote(getName());
            }
        }
    }
}





/*
Для выполнения задачи нужно реализовать класс NoteThread, который будет нитью
и будет добавлять/удалять заметки в списке notes 1000 раз.
Каждый цикл будет сопровождаться задержкой в 1 миллисекунду.
Мы также будем использовать методы addNote и removeNote для работы с заметками.

Решение
Создадим класс NoteThread, который наследуется от класса Thread.
В методе run создаем цикл на 1000 итераций. В каждой итерации:
Добавляем заметку с помощью метода addNote.
Делаем паузу в 1 миллисекунду с помощью Thread.sleep(1).
Удаляем заметку с помощью метода removeNote.

Описание:
Класс NoteThread наследуется от Thread. В его методе run происходит основной цикл с 1000 итерациями.
На каждой итерации:
Создается заметка, в которой содержится имя нити и текущий индекс (например, Thread-0-Note4).
Нить засыпает на 1 миллисекунду.
Заметка удаляется, и в консоли выводится информация о том,
удалена ли заметка той же нитью, что ее добавила, или другой нитью.
В методе main запускаются две нити.
Таким образом, нити будут работать параллельно, добавляя и удаляя свои заметки,
что продемонстрирует асинхронное выполнение.
 */
