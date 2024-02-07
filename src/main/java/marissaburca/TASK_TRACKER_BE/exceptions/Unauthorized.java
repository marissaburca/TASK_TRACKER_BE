package marissaburca.TASK_TRACKER_BE.exceptions;

public class Unauthorized extends RuntimeException {
    public Unauthorized ( String message ) {
        super(message);
    }
}
