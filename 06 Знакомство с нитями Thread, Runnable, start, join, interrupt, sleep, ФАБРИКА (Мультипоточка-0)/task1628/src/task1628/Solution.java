package task1628;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Кто первый встал - того и тапки
1. Разберись, что делает программа.
1.1. Каждая нить должна читать с консоли строки. Используй готовый static BufferedReader reader.
1.2. Используй AtomicInteger readStringCount, чтобы посчитать, сколько строк уже считано с консоли всеми нитями.
2. Реализуй логику метода run:
2.1. Пока нить не прервана (!isInterrupted) читай с консоли строки и добавляй их в поле List<String> result.
2.2. Используй readStringCount для подсчета уже считанных с консоли строк.
2.3. Тело метода run нужно поместить в блок try-catch.


Requirements:
1. Метод run должен работать пока нить не прервана (!isInterrupted).
2. Метод run НЕ должен создавать свои InputStreamReader-ы или BufferedReader-ы.
3. Метод run должен считывать строки из reader и добавлять их в список result.
4. Метод run должен после каждого считывания увеличивать счетчик прочитанных строк readStringCount на 1.
5. Программа должна выводить данные, считанные каждым потоком.*/

public class Solution {
    public static volatile AtomicInteger readStringCount = new AtomicInteger(0);
    public static volatile BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        //read count of strings
        int count = Integer.parseInt(reader.readLine());

        //init threads
        ReaderThread consoleReader1 = new ReaderThread();
        ReaderThread consoleReader2 = new ReaderThread();
        ReaderThread consoleReader3 = new ReaderThread();

        consoleReader1.start();
        consoleReader2.start();
        consoleReader3.start();

        // Ждем пока будет считано необходимое количество строк
        while (count > readStringCount.get()) {
        }

        // Прерываем потоки
        consoleReader1.interrupt();
        consoleReader2.interrupt();
        consoleReader3.interrupt();

        // Выводим результат
        System.out.println("#1:" + consoleReader1);
        System.out.println("#2:" + consoleReader2);
        System.out.println("#3:" + consoleReader3);

        reader.close();
    }

    public static class ReaderThread extends Thread {
        private List<String> result = new ArrayList<>();

        public void run() {
            try {
                while (!isInterrupted()) {
                    // Чтение строки с консоли
                    String line = reader.readLine();
                    if (line != null) {
                        result.add(line);

                        // Увеличение счетчика считанных строк
                        readStringCount.incrementAndGet();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return result.toString();
        }
    }
}





/*
Для решения задачи необходимо реализовать метод run в классе ReaderThread,
чтобы он читал строки из консоли, добавлял их в список result,
и обновлял счетчик считанных строк с помощью AtomicInteger.


Объяснение:
Метод run в классе ReaderThread:

Работает в цикле, пока нить не прервана (условие !isInterrupted()).
Читает строки с консоли с помощью reader.readLine() и добавляет их в список result.
После каждого успешного чтения строки увеличивает счетчик строк readStringCount с помощью метода incrementAndGet().
В методе main:

Программа ждет, пока все нити вместе не прочитают необходимое количество строк (используется цикл while (count > readStringCount.get())).
После этого прерываются все нити с помощью метода interrupt(), что позволяет потокам корректно завершиться.
Результаты, считанные каждой нитью, выводятся в консоль.
 */