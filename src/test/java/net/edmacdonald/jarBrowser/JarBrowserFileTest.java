package net.edmacdonald.jarBrowser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
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

    private String sampleArchiveFileName;
    
    @BeforeClass
    public void setuptTest(){
        sampleArchiveFileName = JarBrowserFileFactory.config.getString(
                JarBrowserFileFactory.SAMPLE_COMPRESSED_FILE_NAME_PROPERTY);

    }

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

    @Test(enabled = false) //disabling b/c it depends on the property for the location of the sample data
                           //being set. I don't want people who download this to give up b/c a test fails, which
                           //it inevitably will (b/c they won't have the property set correctly).
    public void testBrowsableFileExpandedUponListing(){
        File file = JarBrowserFileFactory.getInstance(new File(sampleArchiveFileName));
        Assert.assertNotNull(file.listFiles());
    }
}
