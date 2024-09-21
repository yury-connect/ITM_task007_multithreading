package task1618;

/* 
Снова interrupt
Создай нить TestThread.
В методе main создай экземпляр нити, запусти, а потом прерви ее используя метод interrupt().


Requirements:
1. Класс TestThread должен быть унаследован от Thread.
2. Класс TestThread должен иметь public void метод run.
3. Метод main должен создавать объект типа TestThread.
4. Метод main должен вызывать метод start у объекта типа TestThread.
5. Метод main должен вызывать метод interrupt у объекта типа TestThread.*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {

        TestThread thread = new TestThread();
        System.out.printf("Start thread: '%s'\n", Thread.currentThread().getName());
        thread.start();

        Thread.sleep(2000); // Основной поток засыпает на *** мили_секунд
        thread.interrupt();  // Прерываем thread через *** мили_секунд
    }

    public static class TestThread extends Thread {

        // в методе ниже код решения
        @Override
        public void run() {
            System.out.println("I am TestThread. I am working here...");
            Thread current = Thread.currentThread();
            int counter = 0;

            while (!current.interrupted()) {
                try {
                    System.out.printf("I'm still working... \tStep '%d'\n", ++counter);
                    Thread.sleep(200);
                } catch (InterruptedException e) {   // Когда Thread.sleep() вызывает InterruptedException, это означает, что поток был прерван во время сна.
                    System.out.println("The stream was interrupted. Completing the work...");   // Сообщаем о завершении выполнение потока корректно при прерывании
                    break;   // Выходим из цикла, т.к. Когда выбрасывается InterruptedException, флаг прерывания автоматически сбрасывается, т.е. interrupted = false
                }
            }
        }
    }
}