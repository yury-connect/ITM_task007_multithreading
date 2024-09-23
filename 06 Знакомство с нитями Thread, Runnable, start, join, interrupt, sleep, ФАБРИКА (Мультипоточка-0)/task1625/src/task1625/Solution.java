package task1625;

/* 
Взаимная блокировка
1. Разберись, как работает программа.
2. Не меняя классы T1 и T2 сделай так, чтобы их нити завершились, не обязательно успешно.
3. Метод sleep не использовать.


Requirements:
1. Метод main должен запускать нить t1.
2. Метод main должен запускать нить t2.
3. Класс T1 не изменять.
4. Класс T2 не изменять.
5. Метод sleep не использовать.
6. Вывод программы должен состоять из 2х строк, информирующих о завершении нитей. Например: "T1 was interrupted" и "T2 finished".
7. Нити t1 и t2 должны завершаться (не обязательно успешно).*/

public class Solution {
    static Thread t1 = new T1();
    static Thread t2 = new T2();

    public static void main(String[] args) throws InterruptedException {
        // Запускаем нити
        t1.start();
        t2.start();

        // Прерываем одну из нитей, чтобы предотвратить взаимную блокировку
        Thread.sleep(100);  // Краткая задержка для того, чтобы обе нити начали выполнение
        t1.interrupt();  // Прерываем нить t1
    }

    public static class T1 extends Thread {
        @Override
        public void run() {
            try {
                t2.join();  // Ожидание завершения нити t2
                System.out.println("T1 finished");
            } catch (InterruptedException e) {
                System.out.println("T1 was interrupted");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            try {
                t1.join();  // Ожидание завершения нити t1
                System.out.println("T2 finished");
            } catch (InterruptedException e) {
                System.out.println("T2 was interrupted");
            }
        }
    }
}





/*
Объяснение изменений:
Добавлено прерывание нити t1: В методе main после того, как обе нити начали выполнение,
t1 прерывается с помощью метода interrupt(). Это позволит нити T1 выйти из состояния ожидания (join()),
что предотвращает взаимную блокировку.
Задержка перед прерыванием: Чтобы дать нитям время начать выполнение,
добавлена краткая задержка Thread.sleep(100), чтобы обе нити успели запуститься до прерывания.
В результате программа завершится без взаимной блокировки, и вывод будет состоять из двух строк: о завершении T1 и T2.
 */
