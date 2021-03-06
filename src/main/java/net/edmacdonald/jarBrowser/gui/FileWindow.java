package net.edmacdonald.jarBrowser.gui;

import com.sun.java.swing.plaf.nimbus.FileChooserPainter;
import net.edmacdonald.jarBrowser.JarBrowserFileFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton openFileButton;

    private static Log log = LogFactory.getLog(FileWindow.class);

    public FileWindow() {
        tree1.setModel(new DefaultTreeModel(new DefaultMutableTreeNode("Open a file using the button above.")));

        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(panel1);
                log.debug("Attempting to open a file!");

                tree1.setModel(new Model(JarBrowserFileFactory.getInstance(fc.getSelectedFile())));

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
