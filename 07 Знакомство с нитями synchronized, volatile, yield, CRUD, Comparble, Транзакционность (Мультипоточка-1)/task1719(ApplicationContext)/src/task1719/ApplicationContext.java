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
    private final Map<String, GenericsBean> container = new HashMap<String, GenericsBean>();
    // Map<Name, some class that implements the Bean interface>

    protected ApplicationContext() {
        parseAllClassesAndInterfaces();
    }

    // Синхронизируем для потокобезопасного доступа
    public synchronized GenericsBean getByName(String name) {
        return container.get(name);
    }

    // Синхронизируем для потокобезопасного удаления
    public synchronized GenericsBean removeByName(String name) {
        return container.remove(name);
    }

    protected abstract void parseAllClassesAndInterfaces();

    public static void main(String[] args) {
        // Пример использования
    }
}





/*
Чтобы обеспечить потокобезопасность в классе ApplicationContext, необходимо синхронизировать методы,
которые изменяют или читают данные из общего ресурса — карты container.

Шаги:
Синхронизация методов getByName и removeByName:
Методы, которые работают с картой container, могут быть вызваны несколькими потоками одновременно.
Чтобы избежать проблем, связанных с конкурентным доступом, используем ключевое слово synchronized.
Ключевое слово synchronized:
Его можно использовать для синхронизации доступа к методам, что гарантирует, что только один поток
в одно время будет иметь доступ к изменению или чтению данных из container.


Объяснение:
synchronized для методов:

Методы getByName и removeByName теперь синхронизированы,
что предотвращает одновременный доступ нескольких потоков к общему ресурсу — container.
final для container:

Поле container помечено как final, чтобы гарантировать, что оно не будет изменено после инициализации конструктора.
Это также увеличивает стабильность программы в многопоточной среде.
Теперь доступ к container защищен от некорректного многопоточного доступа.
 */