package cz.uhk.fim.rssfeeder.gui;

import utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JFrame {
    private JLabel lblError = new JLabel("Zpráva");
    private JTextField txtInputField = new JTextField();
    private String cestaSoubor = "rss.xml";

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
        JButton btnSave = new JButton("save");

        controlPanel.add(btnLoad, BorderLayout.WEST);
        controlPanel.add(txtInputField, BorderLayout.CENTER);
        controlPanel.add(btnSave, BorderLayout.EAST);

        // validace zdali je txtInputField prázdný -- (https://stackoverflow.com/questions/21879243/how-to-create-on-click-event-for-buttons-in-swing)
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtInputField.getText().isEmpty() == true) {
                    lblError.setVisible(true);
                    lblError.setText("Nezdali jste název souboru, do kterého chcete uložit.");
                } else {
                    cestaSoubor = txtInputField.getText();
                    lblError.setVisible(false);
                }
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtInputField.getText().isEmpty() == true) {
                    lblError.setVisible(true);
                    lblError.setText("Nezdali jste název souboru, ze kterého chcete načíst data.");
                } else {
                    cestaSoubor = txtInputField.getText();
                    lblError.setVisible(false);
                }
            }
        });

        //umisteni do panelu a zarovani na stred -- (https://stackoverflow.com/questions/16957329/borderlayout-center-doesnt-center)
        controlPanel.add(lblError, BorderLayout.SOUTH);
        lblError.setVerticalAlignment(SwingConstants.CENTER);
        lblError.setHorizontalAlignment(SwingConstants.CENTER);
        lblError.setForeground(Color.RED); // nastaveni barvy textu -- (https://stackoverflow.com/questions/2966334/how-do-i-set-the-colour-of-a-label-coloured-text-in-java)

        add(controlPanel, BorderLayout.NORTH);

        JTextArea txtContent = new JTextArea();
        add(new JScrollPane(txtContent), BorderLayout.CENTER);

        try {
            txtContent.setText(FileUtils.loadStringFromFile(/*"rss.xml"*/ cestaSoubor ));
            System.out.println(45);
            System.out.println(cestaSoubor);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            showErrorMessage("ChybaLoad");
        }

    }

    public void showErrorMessage(String type) {
        if (type == "ChybaLoad" || type == "ChybaSave") {
            if (txtInputField.getText().equals("rss.xml") == false) {
                System.out.println(12121);
                lblError.setVisible(true);
                if (type == "ChybaLoad") {
                    lblError.setText("Chyba načtení souboru - špatné jméno");
                } else {
                    lblError.setText("Chyba uložení souboru - špatné jméno");
                }
            }
        } else {
            lblError.setVisible(false); //nezobrazi label
        }
    }

    public MainFrame() {
        init();
    }
}
