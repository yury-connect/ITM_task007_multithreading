package task1703;

import java.util.ArrayList;
import java.util.List;

/* 
Синхронизированные заметки
1. Класс Note будет использоваться нитями. Поэтому сделай так, чтобы обращения к листу notes блокировали мьютекс notes, не this
2. Все System.out.println не должны быть заблокированы (синхронизированы), т.е. не должны находиться в блоке synchronized


Requirements:
1. Метод addNote() должен добавлять записки в список notes.
2. Метод removeNote() должен удалять записку из списка notes.
3. В методе addNote() должен находиться synchronized блок.
4. В методе removeNote() должен находиться synchronized блок.
5. Synchronized блок в методе addNote() должен блокировать мьютекс notes.
6. Synchronized блок в методе removeNote() должен блокировать мьютекс notes.
7. В synchronized блоке метода addNote() должен находиться вызов метода add у notes.
8. В synchronized блоке метода removeNote() должен находиться вызов метода remove у notes.
9. Все команды вывода на экран не должны находиться в блоке synchronized.*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class Note {

        public final List<String> notes = new ArrayList<String>();

        public void addNote(int index, String note) {
            // Вывод в консоль вне синхронизации
            System.out.println("Сейчас будет добавлена заметка [" + note + "] На позицию " + index);

            // Синхронизация на объекте notes
            synchronized (notes) {
                notes.add(index, note);
            }

            // Вывод в консоль вне синхронизации
            System.out.println("Уже добавлена заметка [" + note + "]");
        }

        public void removeNote(int index) {
            // Вывод в консоль вне синхронизации
            System.out.println("Сейчас будет удалена заметка с позиции " + index);

            String note;
            // Синхронизация на объекте notes
            synchronized (notes) {
                note = notes.remove(index);
            }

            // Вывод в консоль вне синхронизации
            System.out.println("Уже удалена заметка [" + note + "] с позиции " + index);
        }
    }
}






/*
Для выполнения задачи необходимо сделать обращения к листу notes синхронизированными,
используя мьютекс самого объекта notes. Однако, команды вывода в консоль (System.out.println)
не должны быть заблокированы с помощью synchronized.

Решение:
Добавим блоки synchronized в методы addNote и removeNote.
Блокировка должна происходить на уровне самого объекта notes, а не this.
Команды System.out.println будут находиться вне блока synchronized.

Пояснение:
Синхронизация: В методах addNote и removeNote добавлены блоки synchronized, которые блокируют объект notes, ч
тобы гарантировать потокобезопасность при изменении списка.
Команды вывода: Все вызовы System.out.println находятся вне синхронизированных блоков, как того требуют условия задачи.
Это позволяет избежать излишней блокировки, которая может замедлить выполнение программы.
Таким образом, код обеспечивает синхронизацию операций с коллекцией notes, избегая при этом блокировки вывода на консоль.
 */