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
7. Метод addVegetable(int index, String vegetable) должен добавлять параметр vegetable в лист vegetables на позицию index.
8. Метод removeVegetable(int index) должен удалять из vegetables элемент с индексом index.
9. Все методы класса Garden должны блокировать мьютекс this (быть синхронизированы).*/

public class Solution {

    public static void main(String[] args) {
        // Пример использования
        Garden garden = new Garden();
        garden.addFruit(0, "Apple");
        garden.addVegetable(0, "Carrot");
    }

    public static class Garden {

        public final List<String> fruits = new ArrayList<>();
        public final List<String> vegetables = new ArrayList<>();

        // Добавление фрукта с синхронизацией
        public synchronized void addFruit(int index, String fruit) {
            fruits.add(index, fruit);
        }

        // Удаление фрукта с синхронизацией
        public synchronized void removeFruit(int index) {
            fruits.remove(index);
        }

        // Добавление овоща с синхронизацией
        public synchronized void addVegetable(int index, String vegetable) {
            vegetables.add(index, vegetable);
        }

        // Удаление овоща с синхронизацией
        public synchronized void removeVegetable(int index) {
            vegetables.remove(index);
        }
    }
}





/*
Для выполнения задачи, нужно создать методы для добавления и удаления элементов в/из списков fruits и vegetables,
а также сделать так, чтобы каждый из этих методов был синхронизирован на уровне объекта (this).

Решение:
Добавим методы addFruit, removeFruit, addVegetable, и removeVegetable.
Все методы должны быть синхронизированы на объекте (this), чтобы они могли использоваться в многопоточном окружении безопасно.
Пояснение:
Методы синхронизированы: Все методы addFruit, removeFruit, addVegetable, и removeVegetable объявлены с модификатором synchronized, что обеспечивает блокировку на уровне объекта this. Это гарантирует, что только одна нить может одновременно изменять состояние объекта Garden.

Минимизация кода: Мы используем синхронизацию на уровне метода (через ключевое слово synchronized), что минимизирует количество кода и делает реализацию простой и понятной.

Таким образом, все операции с фруктами и овощами теперь безопасны для многопоточного использования.
 */
