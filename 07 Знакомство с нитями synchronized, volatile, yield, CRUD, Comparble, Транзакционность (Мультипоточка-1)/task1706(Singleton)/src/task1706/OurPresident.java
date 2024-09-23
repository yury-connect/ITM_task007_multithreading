package task1706;

public class OurPresident {
    private static OurPresident president;

    // Приватный конструктор для предотвращения создания объектов извне
    private OurPresident() {
    }

    // Статический метод для получения единственного экземпляра
    public static OurPresident getOurPresident() {
        return president;
    }

    // Статический блок с синхронизацией для инициализации president
    static {
        synchronized (OurPresident.class) {
            if (president == null) {
                president = new OurPresident();
            }
        }
    }
}
