package cz.uhk.fim.rssfeeder.gui;

import Model.RSSItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CardView extends JPanel {

    private static final int ITEM_WIDTH = 180;
    private static final int COMPONENT_WIDTH = 160;
    private static final int HEIGHT = 1;

    private int r,g,b = 0;
    private ArrayList<Color> nahodnaBarva = new ArrayList<>();
    private ArrayList<Color> Color = new ArrayList<>();
    private final ArrayList<Integer> finalniBarvaR = new ArrayList<>();
    private final ArrayList<Integer> finalniBarvaG = new ArrayList<>();
    private final ArrayList<Integer> finalniBarvaB = new ArrayList<>();

    private Color barvaPozadi;


    final String startHtml = "<html><p style='width:" + COMPONENT_WIDTH + " px'>";
    final String endHtml = "</p></html>";

    public CardView(RSSItem item) {
        setLayout(new WrapLayout());
        setSize(ITEM_WIDTH, HEIGHT);
        setTitle(item.getTitle());
        setDescription(item.getDescrition());
        setInfo(String.format("%s - %s", item.getPubDate(), item.getAuthor()));
        createRandomColor();
        nastavBarvu();
        //System.out.println(nahodnaBarva);
        //System.out.println("===============");
        System.out.println(barvaPozadi);
        System.out.println("=========");
        setBackground(new Color(r,g,b));
    }

    private void nastavBarvu() {
        //ArrayList<java.awt.Color> color = randomColor;
        //Color = color;
        finalniBarvaR.add(0,214);
        finalniBarvaG.add(0,161);
        finalniBarvaB.add(0,147);
        finalniBarvaR.add(1,141);
        finalniBarvaG.add(1,142);
        finalniBarvaB.add(1,163);
        finalniBarvaR.add(2,201);
        finalniBarvaG.add(2,142);
        finalniBarvaB.add(2,163);


        for (int i = 0; i < finalniBarvaR.size(); i++) {
            r = finalniBarvaR.get(i);
        }
        for (int i = 0; i < finalniBarvaG.size(); i++) {
            g = finalniBarvaG.get(i);
        }
        for (int i = 0; i < finalniBarvaB.size(); i++) {
            b = finalniBarvaB.get(i);
        }

    }

    private void setInfo(String format) {
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

    private void setTitle(String title) {
        JLabel lblTitle = new JLabel();
        lblTitle.setSize(COMPONENT_WIDTH, HEIGHT);
        lblTitle.setFont(new Font("Courier New", Font.BOLD, 12)); // Zdroje: (http://www.java2s.com/Tutorial/Java/0240__Swing/SetFontandforegroundcolorforaJLabel.htm)
        lblTitle.setText(String.format("%s%s%s", startHtml, title, endHtml));
        add(lblTitle);
    }

    public void createRandomColor() {
        // Zdroje: https://stackoverflow.com/questions/4246351/creating-random-colour-in-java
        // Java 'Color' class takes 3 floats, from 0 to 1.
        Random rand = new Random();
        float r = (float) (rand.nextFloat() / 2f + 0.5);
        float g = (float) (rand.nextFloat() / 2f + 0.5);
        float b = (float) (rand.nextFloat() / 2f + 0.5);
        Color randomColor = new Color(r, g, b);
        nahodnaBarva.add(randomColor);

        for (int i = 0; i < nahodnaBarva.size(); i++) {
            Color.add(nahodnaBarva.get(i));
            System.out.println(Color);
        }
    }
}


