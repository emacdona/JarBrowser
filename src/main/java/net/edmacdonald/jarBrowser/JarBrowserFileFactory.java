package net.edmacdonald.jarBrowser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.PropertyResourceBundle;
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

    //public static Properties config;
    public static ResourceBundle config;

    private static Log log = LogFactory.getLog(JarBrowserFileFactory.class);

    static{
        //config = new Properties();
        config =  ResourceBundle.getBundle("jarBrowserFileFactory");
        //try{
            //config.load(new FileInputStream("jarBrowserFileFactory.properties"));
        //}
        /*
        catch(FileNotFoundException e){
            log.error("Error configuring", e);
        }
        catch(IOException e){
            log.error("Error configuring", e);
        }
        */

        log.info("Property listing:");

        /*
        for(String key : config.stringPropertyNames()){
            log.info("Property: " + key + " = " + config.getProperty(key));
        }
        */

        for(String key : config.keySet()){
            log.info("Property: " + key + " = " + config.getString(key));
        }
    }

    /**
     * Get an instance of a File.
     *
     * @param file
     * @return
     */
    static public File getInstance(File file)
    {
        log.info("Getting a file instance for: " + file != null ? file.getName() : "null");

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
