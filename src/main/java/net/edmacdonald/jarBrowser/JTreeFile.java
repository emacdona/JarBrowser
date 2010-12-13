package net.edmacdonald.jarBrowser;

import java.io.File;
import java.net.URI;

/**
 * This class exists soley so I can control the toString() method of all Files that I intend to display as
 * tree nodes. The TreeModel doesn't appear to have a way to control how nodes are displayed (it calls toString()).
 * Kind of annoying. Hopefully I'll find a better way eventually.
 */


public class JTreeFile extends File {
    public JTreeFile(String s) {
        super(s);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public JTreeFile(String s, String s1) {
        super(s, s1);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public JTreeFile(File file, String s) {
        super(file, s);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public JTreeFile(URI uri) {
        super(uri);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public String toString(){
        return this.getName();
    }
}
