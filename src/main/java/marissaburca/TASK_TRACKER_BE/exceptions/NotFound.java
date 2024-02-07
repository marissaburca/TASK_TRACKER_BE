package marissaburca.TASK_TRACKER_BE.exceptions;

public class NotFound extends RuntimeException {
    public NotFound ( String message ) {
        super(message);
    }

    public NotFound ( Long id ) {
        super("Id '" + id + "' not found!");
    }
}
