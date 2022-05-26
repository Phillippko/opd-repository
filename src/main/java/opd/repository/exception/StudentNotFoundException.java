package opd.repository.exception;

public class StudentNotFoundException extends Throwable {
    private static final String message = "Студент %s не найден";
    public StudentNotFoundException(String studentName) {
        super(message.formatted(studentName));
    }
}
