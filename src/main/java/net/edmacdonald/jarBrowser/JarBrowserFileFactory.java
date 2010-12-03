package net.edmacdonald.jarBrowser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: emacdona
 * Date: Dec 2, 2010
 * Time: 8:36:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class JarBrowserFileFactory {

    public static String SAMPLE_COMPRESSED_FILE_NAME_PROPERTY = "sampleCompressedFile";

    public static ResourceBundle config;

    private static Log log = LogFactory.getLog(JarBrowserFileFactory.class);

    static{
        config =  ResourceBundle.getBundle("jarBrowserFileFactory");

        log.info("Property listing:");
        for(String key : config.keySet()){
            log.info("Property: " + key + " = " + config.getString(key));
        }
    }

    private JarBrowserFileFactory(){}

    /**
     * Get an instance of a File.
     *
     * @param file
     * @return
     */
    static public File getInstance(File file)
    {
        log.info("Getting a file instance for: " + (file != null ? file.getAbsolutePath() : "null"));

        if(file.exists()){
            if(JarBrowserFile.isBrowsableFile(file)) {
                return new JarBrowserFile(file.getAbsolutePath());
            }
            else if(file.isDirectory()){
                return new JarBrowserDirectory(file.getAbsolutePath());
            }
        }

        return file;
    }
}
