package net.edmacdonald.jarBrowser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.util.ResourceBundle;

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

        return new JTreeFile(file.getAbsolutePath());
    }
}
