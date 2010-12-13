package net.edmacdonald.jarBrowser.gui;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * Created by IntelliJ IDEA.
 * User: emacdona
 * Date: Dec 2, 2010
 * Time: 4:28:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileWindow {
    private JTree tree1;
    private JPanel panel1;
    private JComboBox comboBox1;

    private static Log log = LogFactory.getLog(FileWindow.class);

    public FileWindow() {
        /*
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
        root.add(new DefaultMutableTreeNode("foo"));
        tree1.setModel(new DefaultTreeModel(root));
        */

        tree1.setModel(new Model());

        tree1.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent treeExpansionEvent) {
                log.info(treeExpansionEvent.getPath().getLastPathComponent());
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent treeExpansionEvent) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame jarBrowser = new JFrame("Jar Browser");

        jarBrowser.setContentPane(new FileWindow().panel1);
        jarBrowser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jarBrowser.pack();
        jarBrowser.setVisible(true);
    }

    public void setData(Model data) {
    }

    public void getData(Model data) {
    }

    public boolean isModified(Model data) {
        return false;
    }
}
