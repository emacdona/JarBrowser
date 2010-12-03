package net.edmacdonald.jarBrowser.gui;

import javax.swing.*;

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

    public static void main(String[] args)
    {
        JFrame jarBrowser = new JFrame("Jar Browser");

        jarBrowser.setContentPane(new FileWindow().panel1);
        jarBrowser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jarBrowser.pack();
        jarBrowser.setVisible(true);
    }
}
