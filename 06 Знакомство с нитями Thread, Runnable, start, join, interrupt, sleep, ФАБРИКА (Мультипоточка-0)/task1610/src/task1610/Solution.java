package task1610;

/* 
Расставь вызовы методов join()
1. Разберись, что делает программа.
2. Расставь вызовы методов join() так, чтобы для каждой кошки выполнялось следующее:
2.1. Сначала кошка рожает котят.
2.2. Потом все котята вылезают из корзинки в произвольном порядке.
2.3. В конце кошка собирает их назад в корзинку.
2.4. Все события для одной кошки могут быть перемешаны с событиями для другой кошки.
2.5. Добавить сон котят (200 мс) в investigateWorld.


Requirements:
1. У каждого котенка (объекта типа Kitten) должен быть вызван метод join.
2. Метод investigateWorld должен обеспечивать сон котенка на 200 мс. Используй метод Thread.sleep(200).
3. Программа должна создавать две кошки и четырех котят.
4. Методы, которые отвечают за вывод в консоль, не изменять.
5. Вывод программы должен отображать выполнение требований условия.*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        Cat cat1 = new Cat("Мурка");
        Cat cat2 = new Cat("Пушинка");
    }

    private static void investigateWorld() {
        try { // Обработаю возможную ошибку
            Thread.sleep(200); // пауза на 200 мс спать,
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Cat extends Thread {
        protected Kitten kitten1;
        protected Kitten kitten2;

        public Cat(String name) throws InterruptedException { // Пробрасываю возможную ошибку на следующий уровень
            super(name);
            kitten1 = new Kitten("Котенок 1, мама - " + getName());
            kitten2 = new Kitten("Котенок 2, мама - " + getName());

            start();

            kitten1.join(200); // Отправляю котенка №1 спать на 200 мс
            kitten2.join(200); // Отправляю котенка №2 спать на 200 мс
        }

        public void run() {
            System.out.println(getName() + " родила 2 котят");
            try {
                initAllKittens();
            } catch (InterruptedException e) {
            }
            System.out.println(getName() + ": Все котята в корзинке. " + getName() + " собрала их назад");
        }

        private void initAllKittens() throws InterruptedException {
            kitten1.start();
            kitten2.start();
        }
    }

    public static class Kitten extends Thread {
        public Kitten(String name) {
            super(name);
        }

        public void run() {
            System.out.println(getName() + ", вылез из корзинки");
            investigateWorld();
        }
    }
}
