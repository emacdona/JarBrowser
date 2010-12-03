package net.edmacdonald.jarBrowser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: emacdona
 * Date: Dec 2, 2010
 * Time: 5:59:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class JarBrowserFileTest
{

    private Log log = LogFactory.getLog(JarBrowserFileTest.class);

    @Test
    public void testIsBrowseableFile()
    {
        log.info("Running: testIsBrowseableFile");
        
        Assert.assertTrue(
                JarBrowserFile.isBrowsableFile(
                        new File("test.jar") ) );

        Assert.assertTrue(
                JarBrowserFile.isBrowsableFile(
                        new File("test.war") ) );

        Assert.assertTrue(
                JarBrowserFile.isBrowsableFile(
                        new File("test.ear") ) );

        Assert.assertTrue(
                JarBrowserFile.isBrowsableFile(
                        new File("test.zip") ) );

        Assert.assertFalse(
                JarBrowserFile.isBrowsableFile(
                        new File("test.txt") ) );

    }

    @Test
    public void testBrowsableFileExpandedUponListing(){
        File file = JarBrowserFileFactory.getInstance(
                new File(
                        JarBrowserFileFactory.config.getString(
                                JarBrowserFileFactory.SAMPLE_COMPRESSED_FILE_NAME_PROPERTY)));

        file.listFiles();
    }
}
