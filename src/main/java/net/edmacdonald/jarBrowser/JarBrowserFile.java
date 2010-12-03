package net.edmacdonald.jarBrowser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.*;
import java.util.zip.InflaterInputStream;

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

    protected File explodedFile;

    private Log log = LogFactory.getLog(JarBrowserFile.class);

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
        explodedFile = new File(tempDirectory.getAbsoluteFile() + File.pathSeparator + getExplodedFileName());

        log.info("Created JarBrowserFile: " + this.toString());
    }

    private String getExplodedFileName(){
        return this.getName().substring(
                    this.getName().lastIndexOf(".") + 1,
                    this.getName().length() );
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
        log.info("Listing files for a compressed file. Magic happening!");

        FileInputStream fis;
        InflaterInputStream iis;
        FileOutputStream fos;
        try{
            fis = new FileInputStream(this.getName());
            iis = new InflaterInputStream(fis);
            fos = new FileOutputStream(this.explodedFile);

            for(int c = iis.read(); c != -1; c = iis.read()){
                fos.write(c);
            }
        }
        catch(FileNotFoundException e){

        }
        catch(IOException e){

        }

        //Now, since these files all live in the temp directory, they all need to be JarBrowserFiles
        //TODO: do something useful here... just returning an empty list so it compiles
        return (File[]) new ArrayList<File>().toArray();

       // return super.listFiles();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public String toString() {
        return "JarBrowserFile{" +
                "explodedFile=" + explodedFile +
                '}';
    }
}
