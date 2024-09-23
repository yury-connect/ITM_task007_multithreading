package task1714;

/* 
Comparable
Реализуй интерфейс Comparable<Beach> в классе Beach. Пляжи(Beach) будут использоваться нитями, поэтому позаботься, чтобы все методы были синхронизированы.
Реализуй метод compareTo так, чтобы при сравнении двух пляжей он выдавал:
– положительное число, если первый пляж лучше;
– отрицательное число, если второй пляж лучше;
– ноль, если пляжи одинаковые.
Сравни каждый критерий по отдельности, чтобы победителем был пляж с лучшими показателями по большинству критериев. Учти при сравнении, чем меньше расстояние к пляжу (distance), тем пляж лучше.


Requirements:
1. Класс Beach должен содержать три поля: String name, float distance, int quality.
2. Класс Beach должен реализовывать интерфейс Comparable<Beach>.
3. Метод compareTo класса Beach должен учитывать качество пляжа (quality) и дистанцию (distance).
4. Все методы класса Beach, кроме метода main, должны быть синхронизированы.
*/

public class Beach implements Comparable<Beach> {
    private String name;      // название
    private float distance;   // расстояние
    private int quality;      // качество

    public Beach(String name, float distance, int quality) {
        this.name = name;
        this.distance = distance;
        this.quality = quality;
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    public synchronized float getDistance() {
        return distance;
    }

    public synchronized void setDistance(float distance) {
        this.distance = distance;
    }

    public synchronized int getQuality() {
        return quality;
    }

    public synchronized void setQuality(int quality) {
        this.quality = quality;
    }

    @Override
    public synchronized int compareTo(Beach other) {
        int qualityComparison = Integer.compare(this.quality, other.quality);
        int distanceComparison = Float.compare(other.distance, this.distance); // чем меньше расстояние, тем лучше пляж

        // Победителем станет пляж с лучшими показателями
        return qualityComparison + distanceComparison;
    }

    public static void main(String[] args) {
        // Пример использования
        Beach beach1 = new Beach("Beach1", 5.0f, 8);
        Beach beach2 = new Beach("Beach2", 7.0f, 7);

        System.out.println(beach1.compareTo(beach2)); // Вывод зависит от значений distance и quality
    }
}





/*
Для решения задачи, нужно реализовать интерфейс Comparable<Beach>, а также синхронизировать методы класса Beach,
чтобы они могли безопасно использоваться в многопоточной среде.

Шаги:
Реализовать метод compareTo(Beach other) для сравнения пляжей.

Сравнить пляжи по двум критериям: качество (quality) и расстояние (distance).
Чем выше качество, тем лучше пляж.
Чем меньше расстояние, тем лучше пляж.
Синхронизировать все методы, кроме метода main.


Объяснение:
Метод compareTo(Beach other):

Сравниваем пляжи по качеству (quality) — чем выше качество, тем лучше.
Сравниваем по расстоянию (distance) — чем меньше расстояние, тем лучше пляж.
Итоговый результат — сумма сравнений. Если пляж с меньшим расстоянием и большим качеством, он будет "лучше".
Синхронизация методов:

Методы getName, setName, getDistance, setDistance, getQuality, и setQuality синхронизированы для безопасного использования в многопоточной среде.
Теперь программа будет корректно сравнивать пляжи и поддерживать потокобезопасность.
 */