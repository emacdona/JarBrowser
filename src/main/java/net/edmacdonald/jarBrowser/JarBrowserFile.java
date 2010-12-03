package net.edmacdonald.jarBrowser;

import java.io.File;
import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: emacdona
 * Date: Dec 2, 2010
 * Time: 5:32:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class JarBrowserFile extends File
{
    protected static Set<String> extensions;
    protected static File tempDirectory;

    public static String SYSTEM_TEMP_DIR_PROPERTY = "java.io.tmpdir";
    public static String tempDirName = ".javaFileBrowser";

    static{
        extensions = new HashSet<String>(
                Arrays.asList("war", "jar", "ear", "zip")
        );

        tempDirectory = new File(System.getProperty(SYSTEM_TEMP_DIR_PROPERTY) + "/" + tempDirName);

        if(!tempDirectory.exists()){
            tempDirectory.mkdirs();
        }
    }

    /**
     * True if a file can be decompressed and its contents browsed
     * 
     * @param file
     * @return
     */
    public static boolean isBrowsableFile(File file){
        if(file == null){
            return false;
        }

        String filename = file.getName();

        return extensions.contains(
                filename.substring(
                        filename.lastIndexOf(".") + 1,
                        filename.length()));
    }

    public JarBrowserFile(String s) {
        super(s);
    }

    /**
     * Treat browseable files like directories.
     * 
     * @return
     */
    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public File[] listFiles() {
        return super.listFiles();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
