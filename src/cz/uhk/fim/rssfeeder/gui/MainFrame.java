package cz.uhk.fim.rssfeeder.gui;

import Model.RSSItem;
import Model.RSSList;
import org.xml.sax.SAXException;
import utils.FileUtils;
import utils.RSSParser;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainFrame extends JFrame {

    private static final String VALIDATION_TYPE = "VATLIDATION_TYPE";
    private static final String IO_LOAD_TYPE = "IO_LOAD_TYPE";
    private static final String IO_SAVE_TYPE = "IO_SAVE_TYPE";


    private JLabel lblErrorMessage = new JLabel("Zpráva");
    private JTextField txtInputField = new JTextField();

    private RSSList rssList;
    //private String cestaSoubor = "rss.xml";

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
        controlPanel.add(lblErrorMessage, BorderLayout.SOUTH);


        // validace zdali je txtInputField prázdný -- (https://stackoverflow.com/questions/21879243/how-to-create-on-click-event-for-buttons-in-swing)
        /*btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtInputField.getText().isEmpty() == true) {
                    lblErrorMessage.setVisible(true);
                    lblErrorMessage.setText("Nezdali jste název souboru, do kterého chcete uložit.");
                } else {
                    cestaSoubor = txtInputField.getText();
                    lblErrorMessage.setVisible(false);
                }
            }
        });

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtInputField.getText().isEmpty() == true) {
                    lblErrorMessage.setVisible(true);
                    lblErrorMessage.setText("Nezdali jste název souboru, ze kterého chcete načíst data.");
                } else {
                    cestaSoubor = txtInputField.getText();
                    lblErrorMessage.setVisible(false);
                }
            }
        });
*/
        //umisteni do panelu a zarovani na stred -- (https://stackoverflow.com/questions/16957329/borderlayout-center-doesnt-center)
        lblErrorMessage.setVerticalAlignment(SwingConstants.CENTER);
        lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblErrorMessage.setForeground(Color.RED); // nastaveni barvy textu -- (https://stackoverflow.com/questions/2966334/how-do-i-set-the-colour-of-a-label-coloured-text-in-java)
        lblErrorMessage.setVisible(false);

        add(controlPanel, BorderLayout.NORTH);

        JTextArea txtContent = new JTextArea();
        add(new JScrollPane(txtContent), BorderLayout.CENTER);

        btnLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validatInput()) {
                    try {
                        txtContent.setText(FileUtils.loadStringFromFile(txtInputField.getText()));
                    } catch (IOException e1) {
                        showErrorMessage(IO_LOAD_TYPE);
                        e1.printStackTrace();
                    }
                }
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validatInput()) {
                    /*try {
                        FileUtils.saveStringToFile(txtInputField.getText(), txtContent.getText().getBytes("UTF-8"));
                    } catch (IOException e1) {
                        showErrorMessage(IO_SAVE_TYPE);
                        e1.printStackTrace();
                    }*/
                    try {
                        rssList = new RSSParser().getParsedRSS(txtInputField.getText());
                        txtContent.setText("");
                        for(RSSItem item: rssList.getAllItem()){
                            txtContent.append(String.format("%s - autor: %s%n", item.getTitle(), item.getAuthor()));
                        }
                    } catch (IOException | SAXException | ParserConfigurationException e1 ) {
                        e1.printStackTrace();
                    }

                }
            }
        });

       /* try {
            txtContent.setText(FileUtils.loadStringFromFile("rss.xml"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }*/

    }

    /*public void showErrorMessage(String type) {
        if (type == "ChybaLoad" || type == "ChybaSave") {
            if (txtInputField.getText().equals("rss.xml") == false) {
                System.out.println(12121);
                lblErrorMessage.setVisible(true);
                if (type == "ChybaLoad") {
                    lblErrorMessage.setText("Chyba načtení souboru - špatné jméno");
                } else {
                    lblErrorMessage.setText("Chyba uložení souboru - špatné jméno");
                }
            }
        } else {
            lblErrorMessage.setVisible(false); //nezobrazi label
        }
    }*/

    private boolean validatInput() {
        if (txtInputField.getText().trim().isEmpty()) {
            showErrorMessage(VALIDATION_TYPE);
            return false;
        }
        lblErrorMessage.setVisible(false);
        return true;
    }

    private void showErrorMessage(String type) {
        String message;
        switch (type) {
            case VALIDATION_TYPE:
                message = "Chyba! Pole nesmí být prázdné!";
                break;
            case IO_LOAD_TYPE:
                message = "Chyba při načítání souboru!";
                break;
            case IO_SAVE_TYPE:
                message = "Chyba při ukládání souboru!";
                break;
            default:
                message = "Něco se nepovedlo!";
                break;
        }
        lblErrorMessage.setText(message);
        lblErrorMessage.setVisible(true);
    }

    public MainFrame() {
        init();
    }
}
