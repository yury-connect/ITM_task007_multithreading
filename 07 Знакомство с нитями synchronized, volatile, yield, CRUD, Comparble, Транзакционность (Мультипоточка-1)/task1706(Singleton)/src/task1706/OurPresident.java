package task1706;

public class OurPresident {
    private static OurPresident president;

    private OurPresident() {
        // Приватный конструктор для предотвращения создания объектов извне
    }

    static { // Статический блок с синхронизацией для инициализации president
        synchronized (OurPresident.class) {
            if (president == null) {
                president = new OurPresident();
            }
        }
    }

    public static OurPresident getOurPresident() {
        return president;
    }
}
