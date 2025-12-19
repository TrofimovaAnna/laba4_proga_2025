package exceptions;

// Непроверяемое (unchecked) исключение
public class LowEnergyException extends RuntimeException {
    public LowEnergyException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Недостаточно энергии: " + super.getMessage();
    }
}