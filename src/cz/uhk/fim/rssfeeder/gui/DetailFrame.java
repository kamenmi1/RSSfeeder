package cz.uhk.fim.rssfeeder.gui;

import model.RSSItem;
import model.RSSList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DetailFrame extends JFrame {
    private RSSList rssList;
    private RSSItem item;


    public void init() {
        setSize(350, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);

        initContentUI();
    }

    private void initContentUI() {
        JPanel controlPanel = new JPanel(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        JPanel contentPanel = new JPanel(new WrapLayout());

        add(new JScrollPane(contentPanel), BorderLayout.CENTER);

        contentPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                   dispose();

                }
            }
        });
           /* item.getAuthor();
            item.getDescrition();
            item.getTitle();
            item.getPubDate();*/
    }

    public DetailFrame() {
        init();
    }
}
