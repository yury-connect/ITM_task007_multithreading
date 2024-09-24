package task1721;

import java.io.IOException;


public class CorruptedDataException extends IOException {

    public CorruptedDataException(String message) { // я дописал конструктор для своих нужд
        super(message);
    }
}
