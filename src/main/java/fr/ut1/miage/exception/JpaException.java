package fr.ut1.miage.exception;


public class JpaException extends RuntimeException {

    public JpaException(String message, Throwable cause) {
        super(message, cause);
    }

}
