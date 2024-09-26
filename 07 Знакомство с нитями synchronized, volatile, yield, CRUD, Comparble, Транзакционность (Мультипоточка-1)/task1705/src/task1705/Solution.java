package task1705;

import java.util.ArrayList;
import java.util.List;

/* 
Сад-огород
1. Создай метод public void addFruit(int index, String fruit) - который добавляет параметр fruit в лист fruits на позицию index.
2. Создай метод public void removeFruit(int index) - который удаляет из fruits элемент с индексом index.
3. Создай метод public void addVegetable(int index, String vegetable) - который добавляет параметр vegetable в лист vegetables на позицию index.
4. Создай метод public void removeVegetable(int index) - который удаляет из vegetables элемент с индексом index.
5. Класс Garden будет использоваться нитями. Поэтому сделай так, чтобы все методы блокировали мьютекс this.
6. Реализуй это минимальным количеством кода.


Requirements:
1. Класс Garden должен содержать метод public void addFruit(int index, String fruit).
2. Класс Garden должен содержать метод public void removeFruit(int index).
3. Класс Garden должен содержать метод public void addVegetable(int index, String vegetable).
4. Класс Garden должен содержать метод public void removeVegetable(int index).
5. Метод addFruit(int index, String fruit) должен добавлять параметр fruit в лист fruits на позицию index.
6. Метод removeFruit(int index) должен удалять из fruits элемент с индексом index.
7. Метод addVegetable(int index, String vegetable) должен добавлять параметр public void addVegetable(int index, String vegetable в лист vegetables на позицию index.
8. Метод removeVegetable(int index) должен удалять из vegetables элемент с индексом index.
9. Все методы класса Garden должны блокировать мьютекс this (быть синхронизированы).*/


public class Solution {


    public static void main(String[] args) {
        Garden garden = new Garden();
        garden.addFruit(0, "Apple");
        garden.addFruit(1, "Pear");
        garden.addVegetable(0, "Carrot");
        garden.addVegetable(1, "Potato");
        garden.print();

        garden.removeFruit(1);
        garden.removeVegetable(0);
        garden.print();
    }



    public static class Garden {

        public final List<String> fruits = new ArrayList<String>();
        public final List<String> vegetables = new ArrayList<String>();


        public synchronized void addFruit(int index, String fruit) { // Добавление фрукта с синхронизацией
            fruits.add(index, fruit);
        }

        public synchronized void removeFruit(int index) { // Удаление фрукта с синхронизацией
            fruits.remove(index);
        }

        public synchronized void addVegetable(int index, String vegetable) { // Добавление овоща с синхронизацией
            vegetables.add(index, vegetable);
        }

        public synchronized void removeVegetable(int index) { // Удаление овоща с синхронизацией
            vegetables.remove(index);
        }

        public synchronized void print() {
            System.out.println("\n\tfruits:");
            fruits.stream().forEach(System.out::println);
            System.out.println("\n\tvegetables:");
            vegetables.stream().forEach(System.out::println);
        }
    }
}
