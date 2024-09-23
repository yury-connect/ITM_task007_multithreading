package task1622;

/* 
Последовательные выполнения нитей Ӏ
1. В методе run после всех действий поставь задержку в 10 миллисекунд. Выведи "Нить прервана", если нить будет прервана.
2. Сделай так, чтобы все нити выполнялись последовательно: сначала для нити №1 отсчет с COUNT до 1, потом для нити №2 с COUNT до 1 и т.д.

Пример:
#1: 4
#1: 3
...
#1: 1
#2: 4
...


Requirements:
1. Программа должна создавать 4 объекта типа SleepingThread.
2. Метод main должен вызвать join у каждой создаваемой SleepingThread нити.
3. Метод run должен использовать Thread.sleep(10).
4. Вывод программы должен соответствовать условию.
5. Если нить SleepingThread прерывается, она должна вывести сообщение "Нить прервана".*/

public class Solution {
    public volatile static int COUNT = 4;

    public static void main(String[] args) throws InterruptedException {
        // Создаем и запускаем нити
        for (int i = 0; i < COUNT; i++) {
            SleepingThread thread = new SleepingThread();
            thread.join();  // Ждем завершения каждой нити перед началом следующей
        }
    }

    public static class SleepingThread extends Thread {
        private static volatile int threadCount = 0;
        private volatile int countdownIndex = COUNT;

        public SleepingThread() {
            super(String.valueOf(++threadCount));  // Назначаем имя нити
            start();  // Запускаем нить
        }

        public void run() {
            try {
                // Выполняем цикл до тех пор, пока countdownIndex не станет 0
                while (countdownIndex > 0) {
                    System.out.println(this);  // Печатаем текущее состояние нити
                    countdownIndex--;
                    Thread.sleep(10);  // Пауза в 10 миллисекунд
                }
            } catch (InterruptedException e) {
                System.out.println("Нить прервана");  // Обработка прерывания нити
            }
        }

        public String toString() {
            return "#" + getName() + ": " + countdownIndex;
        }
    }
}





/*
Для выполнения задания нужно сделать несколько изменений в коде:
Необходимо вызвать метод join() для каждой создаваемой нити, чтобы обеспечить их последовательное выполнение.
В методе run() каждой нити нужно добавить задержку на 10 миллисекунд и обработать возможное прерывание нити.


Объяснение изменений:
В методе main добавлен вызов join() после создания каждой нити.
Это заставляет основную нить ждать, пока каждая из создаваемых нитей завершит свое выполнение, прежде чем начинать следующую.
В методе run() добавлена задержка с помощью Thread.sleep(10) для соответствия требованиям задачи.
Добавлена обработка прерывания нити: если нить прервана, выводится сообщение "Нить прервана".
 */
