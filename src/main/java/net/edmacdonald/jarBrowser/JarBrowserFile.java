package net.edmacdonald.jarBrowser;

import org.apache.commons.collections.iterators.ArrayIterator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
        explodedFile = new File(tempDirectory.getAbsoluteFile() + File.separator + getExplodedFileName());
        explodedFile.mkdir();

        log.info("Created JarBrowserFile: " + this.toString());
    }

    private String getExplodedFileName(){
        int indexOfLastDot = this.getName().lastIndexOf(".");

        if(indexOfLastDot >= 0){
            return this.getName().substring(
                    0,
                    indexOfLastDot);
        }
        else{
            return this.getName();
        }
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
        log.info("Listing files for a compressed file: " + this.getAbsolutePath());

        InputStream is;
        FileOutputStream fos;
        ZipFile zipFile;
        Enumeration zipEnumeration;
        
        try{
            zipFile = new ZipFile(this);
            zipEnumeration= zipFile.entries();

            while(zipEnumeration.hasMoreElements())
            {
                ZipEntry zipEntry = (ZipEntry) zipEnumeration.nextElement();
                File extractedFile = new File(
                        explodedFile.getAbsolutePath() + File.separator + zipEntry.getName());

                is = zipFile.getInputStream(zipEntry);


                if(zipEntry.isDirectory()){
                    log.info("Ensuring directory exists: " + extractedFile.getAbsolutePath());
                    extractedFile.mkdirs();
                }
                else{
                    log.info("Extracting file: " + extractedFile.getAbsolutePath());
                    fos = new FileOutputStream(
                            explodedFile.getAbsolutePath() + File.separator + zipEntry.getName());

                    for(int c = is.read(); c != -1; c = is.read()){
                        fos.write(c);
                    }
                }
            }

        }
        catch(FileNotFoundException e){
            log.error("Error: ", e);
        }
        catch(IOException e){
            log.error("Error: ", e);
        }

        //Be careful... we don't want to recur here (that's why we are calling File::listFiles())
        File[] tempFiles = new File(explodedFile.getAbsolutePath()).listFiles();

        List<File> files = new ArrayList<File>();

        Iterator<File> tempFileIterator = new ArrayIterator(tempFiles);

        while(tempFileIterator.hasNext()){
            files.add(JarBrowserFileFactory.getInstance(tempFileIterator.next()));
        }

        return files.toArray(new File[0]);
    }

    @Override
    public String toString() {
        return "JarBrowserFile{" +
                "explodedFile=" + explodedFile +
                '}';
    }

    
}
