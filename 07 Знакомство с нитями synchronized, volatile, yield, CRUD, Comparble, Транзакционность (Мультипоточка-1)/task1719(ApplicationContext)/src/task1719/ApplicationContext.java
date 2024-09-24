package task1719;

import java.util.HashMap;
import java.util.Map;

/* 
ApplicationContext
ApplicationContext будет доступен множеству нитей.
Сделать так, чтобы данные не терялись: подумай, какое ключевое слово необходимо поставить и где.


Requirements:
1. Класс ApplicationContext должен быть абстрактным.
2. Класс ApplicationContext должен содержать private поле container типа Map<String, GenericsBean>.
3. В getByName(String name), если необходимо, используй synchronized.
4. В removeByName(String name), если необходимо, используй synchronized.
*/

public abstract class ApplicationContext<GenericsBean extends Bean> {
    private final Map<String, GenericsBean> container = new HashMap<>(); // Поле container объявлено final для повышения безопасности
    // Map<Name, some class that implements the Bean interface>



    protected ApplicationContext() {
        parseAllClassesAndInterfaces();
    }

    public synchronized GenericsBean getByName(String name) { // Синхронизируем доступ к контейнеру при получении значения
        return container.get(name);
    }

    public synchronized GenericsBean removeByName(String name) { // Синхронизируем...
        return container.remove(name);
    }

    protected abstract void parseAllClassesAndInterfaces();

    public static void main(String[] args) {
        /*
        getByName и removeByName теперь синхронизированы
        Теперь ApplicationContext потокобезопасен, и доступ к контейнеру будет контролироваться.
         */
    }
}
