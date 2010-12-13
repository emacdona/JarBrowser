package net.edmacdonald.jarBrowser.exception;

/**
 * Created by IntelliJ IDEA.
 * User: emacdona
 * Date: 12/12/10
 * Time: 11:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class TemporaryFileNameAlreadyExists extends RuntimeException{
    public TemporaryFileNameAlreadyExists() {
        super("Attempted to use a temporary filename for a file that already exists");
    }
}
