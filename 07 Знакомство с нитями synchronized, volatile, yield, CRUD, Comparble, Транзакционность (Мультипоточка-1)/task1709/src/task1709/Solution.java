package task1709;

/* 
Предложения
Не используя synchronized сделай так, чтобы количество сделанных и принятых предложений было одинаковым.


Requirements:
1. Класс Solution должен содержать нить MakeProposal.
2. Класс Solution должен содержать нить AcceptProposal.
3. Класс Solution должен содержать публичную статическую переменную int proposal.
4. Программа не должна содержать synchronized методов или synchronized блоков.
5. Переменная int proposal не должна находится в локальном кэше.
*/

public class Solution {
    // Помечаем переменную volatile для предотвращения кэширования
    public static volatile int proposal = 0;

    public static void main(String[] args) {
        new AcceptProposal().start();
        new MakeProposal().start();
    }

    public static class MakeProposal extends Thread {
        @Override
        public void run() {
            int thisProposal = proposal;

            while (proposal < 10) {
                System.out.println("Сделано предложение №" + (thisProposal + 1));
                proposal = ++thisProposal;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class AcceptProposal extends Thread {
        @Override
        public void run() {
            int thisProposal = proposal;

            while (thisProposal < 10) {
                // Проверяем, изменилось ли предложение
                if (thisProposal != proposal) {
                    System.out.println("Принято предложение №" + proposal);
                    thisProposal = proposal;
                }
            }
        }
    }
}




/*
Для решения задачи, чтобы количество сделанных и принятых предложений было одинаковым без использования synchronized,
можно использовать ключевое слово volatile. Оно гарантирует, что переменная будет читаться и записываться
непосредственно в основную память, избегая кэширования на уровне потоков.

Объяснение изменений:
Ключевое слово volatile: Мы пометили переменную proposal как volatile. Это гарантирует,
что изменения в этой переменной, сделанные в одном потоке, будут немедленно видны другим потокам.
Чтение/запись переменной: Теперь переменная proposal будет читаться и записываться в общую память,
и потоки всегда будут видеть её актуальное значение.
Таким образом, количество сделанных и принятых предложений будет одинаковым,
поскольку потоки не будут кэшировать значение переменной proposal.
 */
