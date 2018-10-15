package cz.uhk.fim.rssfeeder.gui;

import utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    public void init() {
        setTitle("RSSfeeder");
        setSize(500, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initContentUI();
    }

    private void initContentUI() {
        JPanel controlPanel = new JPanel(new BorderLayout());

        JButton btnLoad = new JButton("Load");
        JTextField txtInputField = new JTextField();
        JButton btnSave = new JButton("save");

        controlPanel.add(btnLoad, BorderLayout.WEST);
        controlPanel.add(txtInputField, BorderLayout.CENTER);
        controlPanel.add(btnSave, BorderLayout.EAST);

        add(controlPanel,BorderLayout.NORTH);

        JTextArea txtContent = new JTextArea();
        add(new JScrollPane(txtContent), BorderLayout.CENTER);

        try {
            txtContent.setText(FileUtils.loadStringFromFile("rss.xml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public MainFrame() {
        init();
    }
}
