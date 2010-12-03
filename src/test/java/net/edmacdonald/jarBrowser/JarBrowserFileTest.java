package net.edmacdonald.jarBrowser;

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
    @Test
    public void testIsBrowseableFile()
    {
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
}
