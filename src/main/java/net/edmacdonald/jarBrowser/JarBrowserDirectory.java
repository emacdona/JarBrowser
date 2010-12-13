package net.edmacdonald.jarBrowser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: emacdona
 * Date: Dec 2, 2010
 * Time: 8:35:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class JarBrowserDirectory extends JTreeFile
{
    private List<File> children;

    public JarBrowserDirectory(String s) {
        super(s);

        children = new ArrayList<File>();

        for(File file : super.listFiles()){
            children.add(
                    JarBrowserFileFactory.getInstance(file)
            );
        }

    }

    /*
     * Override listFiles so that it chooses the right implementation for each child.
     * TODO: we may want do choose implementation for the children in the constructor instead of here?
     */
    @Override
    public File[] listFiles() {
        return children.toArray(new File[0]);
    }
}
