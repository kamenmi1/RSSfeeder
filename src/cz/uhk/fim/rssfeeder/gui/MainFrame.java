package cz.uhk.fim.rssfeeder.gui;

import javax.swing.*;

public class MainFrame extends JFrame{

    public void init() {
        setTitle("RSSfeeder");
        setSize(500,800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public MainFrame() {
        init();
    }
}
