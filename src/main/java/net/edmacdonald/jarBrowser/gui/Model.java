package net.edmacdonald.jarBrowser.gui;

import net.edmacdonald.jarBrowser.JarBrowserFileFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.io.File;

public class Model implements TreeModel {

    Log log = LogFactory.getLog(TreeModel.class);
    File root;

    @Override
    public Object getRoot() {
        log.debug("getting root");
        return root;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getChild(Object o, int i) {
        log.debug("Getting child [" + i + "]");
        File file = (File) o;
        File[] children = file.listFiles();
        return i < children.length ? children[i] : null;
    }

    @Override
    public int getChildCount(Object o) {
        File file = (File) o;
        return file.listFiles().length;
    }

    @Override
    public boolean isLeaf(Object o) {
        File file = (File) o;
        if(file.listFiles() == null){
            return true;
        }
        return file.listFiles().length == 0;
    }

    @Override
    public void valueForPathChanged(TreePath treePath, Object o) {
        //TODO: something?
    }

    @Override
    public int getIndexOfChild(Object o, Object o1) {
        File parent = (File) o;
        int i;

        for(i=0; i<parent.listFiles().length; i++){
            if(parent.listFiles()[i].equals(o1)){
                return i;
            }
        }

        return -1;
    }

    @Override
    public void addTreeModelListener(TreeModelListener treeModelListener) {
        //TODO: something
    }

    @Override
    public void removeTreeModelListener(TreeModelListener treeModelListener) {
        //TODO: something
    }

    public Model() {
        root = JarBrowserFileFactory.getInstance(
                new File(
                        JarBrowserFileFactory.config.getString(
                                JarBrowserFileFactory.SAMPLE_COMPRESSED_FILE_NAME_PROPERTY)));
    }

    public Model(File root){
        this.root = root;
    }

    void setRoot(File root){
        this.root = root;
    }
}