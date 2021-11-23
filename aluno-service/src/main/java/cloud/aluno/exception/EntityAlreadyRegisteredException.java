package cloud.aluno.exception;

public class EntityAlreadyRegisteredException extends RuntimeException {
    public EntityAlreadyRegisteredException(String message) {
        super(message);
    }
}
