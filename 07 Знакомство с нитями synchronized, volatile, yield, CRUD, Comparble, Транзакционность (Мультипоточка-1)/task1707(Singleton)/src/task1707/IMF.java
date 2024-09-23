package task1707;

public class IMF {

    private static IMF imf;

    // Публичный статический метод для получения единственного экземпляра IMF
    public static IMF getFund() {
        // Синхронизируемся на классе IMF для потокобезопасности
        if (imf == null) {
            synchronized (IMF.class) {
                if (imf == null) {
                    imf = new IMF();
                }
            }
        }
        return imf;
    }

    // Приватный конструктор, чтобы предотвратить создание объектов извне
    private IMF() {
    }
}

