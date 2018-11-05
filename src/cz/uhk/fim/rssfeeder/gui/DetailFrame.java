package cz.uhk.fim.rssfeeder.gui;

import model.RSSItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class DetailFrame extends JFrame {

    private static final int ITEM_WIDTH = 180;
    private static final int COMPONENT_WIDTH = 160;
    private static final int HEIGHT = 1;

    final String startHtml = "<html><p style='width:" + COMPONENT_WIDTH + " px'>";
    final String endHtml = "</p></html>";

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
                if (e.getButton() == MouseEvent.BUTTON3){
                    dispose();
                }
            }
        });
    }

    public DetailFrame(RSSItem item) {
        init();
    }
    /*    @Override
        public void setTitle(String title){

        }*/
/*        setLayout(new WrapLayout());
        setSize(ITEM_WIDTH, HEIGHT);
        System.out.println(item.getAuthor());
        setTitle(item.getTitle());
        setDescription(item.getDescrition());
        setInfo(String.format("%s - %s", item.getPubDate(), item.getAuthor()));
    }*/

   /* private void setInfo(String format) {
        JLabel lblInfo = new JLabel();
        lblInfo.setSize(COMPONENT_WIDTH, HEIGHT);
        lblInfo.setFont(new Font("Courier New", Font.ITALIC, 10)); // Zdroje: (http://www.java2s.com/Tutorial/Java/0240__Swing/SetFontandforegroundcolorforaJLabel.htm)
        lblInfo.setText(String.format("%s%s%s", startHtml, format, endHtml));
        add(lblInfo);
    }

    private void setDescription(String descrition) {
        JLabel lblDestription = new JLabel();
        lblDestription.setSize(COMPONENT_WIDTH, HEIGHT);
        lblDestription.setFont(new Font("Courier New", Font.PLAIN, 11)); // Zdroje: (http://www.java2s.com/Tutorial/Java/0240__Swing/SetFontandforegroundcolorforaJLabel.htm)
        lblDestription.setText(String.format("%s%5.35s%s%s", startHtml, descrition, "...", endHtml));
        add(lblDestription);
    }
    public void setTitle(String title) {
        JLabel lblTitle = new JLabel();
        lblTitle.setSize(COMPONENT_WIDTH, HEIGHT);
        lblTitle.setFont(new Font("Courier New", Font.BOLD, 12)); // Zdroje: (http://www.java2s.com/Tutorial/Java/0240__Swing/SetFontandforegroundcolorforaJLabel.htm)
        lblTitle.setText(String.format("%s%s%s", startHtml, title, endHtml));
        add(lblTitle);
     //   getRandomBgColor(title);
    }*/
    }
