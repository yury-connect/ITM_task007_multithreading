package task1603;

import java.util.ArrayList;
import java.util.List;

/* 
Список и нити
В методе main добавить в статический объект list 5 нитей.
Каждая нить должна быть новым объектом класса Thread, работающим со своим объектом класса SpecialThread.


Requirements:
1. В методе main создай 5 объектов типа SpecialThread.
2. В методе main создай 5 объектов типа Thread.
3. Добавь 5 разных нитей в список list.
4. Каждая нить из списка list должна работать со своим объектом класса SpecialThread.
5. Класс SpecialThread изменять нельзя.*/

public class Solution {
    public static volatile List<Thread> list = new ArrayList<Thread>(5);

    public static void main(String[] args) {
        final int count = 5;

        // Создаем 5 объектов SpecialThread и 5 объектов Thread
        for (int i = 0; i < count; i++) {
            SpecialThread specialThread = new SpecialThread();  // Создаем объект SpecialThread
            Thread thread = new Thread(specialThread);  // Создаем объект Thread с SpecialThread
            list.add(thread);  // Добавляем объект Thread в список list
        }

        // Запуск всех потоков
        for (Thread thread : list) {
            thread.start();
        }
    }

    public static class SpecialThread implements Runnable {
        public void run() {
            System.out.println("it's a run method inside SpecialThread");
        }
    }
}
