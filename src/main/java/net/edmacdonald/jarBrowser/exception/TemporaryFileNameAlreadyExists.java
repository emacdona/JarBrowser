package net.edmacdonald.jarBrowser.exception;

public class TemporaryFileNameAlreadyExists extends RuntimeException{
    public TemporaryFileNameAlreadyExists() {
        super("Attempted to use a temporary filename for a file that already exists");
    }
}
