package task1604;

/* 
Вывод стек-трейса
1. Создать таск (public static класс SpecialThread, который реализует интерфейс Runnable).
2. SpecialThread должен выводить в консоль свой стек-трейс.

Подсказка: main thread уже выводит в консоль свой стек-трейс.


Requirements:
1. Добавь в класс Solution публичный статический класс SpecialThread.
2. Класс SpecialThread не должен быть унаследован от какого-либо дополнительного класса.
3. Класс SpecialThread должен реализовывать интерфейс Runnable.
4. Метод run класса SpecialThread должен выводить свой стек-трейс.*/

import java.util.stream.IntStream;


public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SpecialThread()); // Создаем новый поток (Thread) с задачей SpecialThread (Runnable). Это означает, что поток будет выполнять метод run() из SpecialThread при запуске.
        thread.start();

        System.out.println("*****************");

        for (StackTraceElement element : Thread.currentThread().getStackTrace()) { // Вывод стек-трейса для текущего потока (главного потока)
            System.out.println("поток_1 (Главный): " + element);
        }
    }


    public static class SpecialThread implements Runnable {

        @Override
        public void run() {
            StackTraceElement[] stackTraceElements = new RuntimeException().getStackTrace();
            IntStream.range(0, stackTraceElements.length)
                    .forEach(count -> System.out.printf(
                            "поток_2 // Элемент СтэкТрэйса под № '%d' , имеет значение: '%s'\n"
                            , count, stackTraceElements[count]));
        }
    }
}
