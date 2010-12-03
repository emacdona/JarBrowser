package net.edmacdonald.jarBrowser;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: emacdona
 * Date: Dec 2, 2010
 * Time: 8:36:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class JarBrowserFileFactory {
    /**
     * Get an instance of a File.
     *
     * @param file
     * @return
     */
    static public File getInstance(File file)
    {
        if(file.exists()){
            if(JarBrowserFile.isBrowsableFile(file)) {
                return new JarBrowserFile(file.getName());
            }
            else if(file.isDirectory()){
                return new JarBrowserDirectory(file.getName());
            }
        }

        return file;
    }
}
