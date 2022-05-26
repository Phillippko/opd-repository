package opd.repository.exception;

public class LectionNotFoundException extends Throwable {
    private static final String message = "Лекция %s не найдена!";
    public LectionNotFoundException(String lectionName) {
        super(message.formatted(lectionName));
    }
}
