package cz.uhk.fim.rssfeeder.gui;

import model.RSSItem;
import model.RSSList;
import org.xml.sax.SAXException;
import utils.RSSParser;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MainFrame extends JFrame {

    private static final String VALIDATION_TYPE = "VATLIDATION_TYPE";
    private static final String IO_LOAD_TYPE = "IO_LOAD_TYPE";
    private static final String IO_SAVE_TYPE = "IO_SAVE_TYPE";


    private JLabel lblErrorMessage = new JLabel("Zpráva");
    private JTextField txtInputField = new JTextField();

    private RSSList rssList;

    public void init() {
        setTitle("RSSfeeder");
        setSize(700, 800);
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

        //umisteni do panelu a zarovani na stred -- (https://stackoverflow.com/questions/16957329/borderlayout-center-doesnt-center)
        lblErrorMessage.setVerticalAlignment(SwingConstants.CENTER);
        lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        lblErrorMessage.setForeground(Color.RED); // nastaveni barvy textu -- (https://stackoverflow.com/questions/2966334/how-do-i-set-the-colour-of-a-label-coloured-text-in-java)
        lblErrorMessage.setVisible(false);

        add(controlPanel, BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new WrapLayout());

        try {
            rssList = new RSSParser().getParsedRSS("rss.xml");
            for (RSSItem item : rssList.getAllItem()) {
                CardView cardView = new CardView(item);
                cardView.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            if (e.getClickCount() == 2) {
                                SwingUtilities.invokeLater(() -> new DetailFrame(item).setVisible(true));
                            }
                        }
                    }
                });
                contentPanel.add(cardView);
            }
        } catch (IOException | SAXException | ParserConfigurationException e1) {
            e1.printStackTrace();
        }

        add(new JScrollPane(contentPanel), BorderLayout.CENTER);
        /* btnLoad.addActionListener(new ActionListener() {
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

                    try {
                        rssList = new RSSParser().getParsedRSS(txtInputField.getText());
                        txtContent.setText("");
                        for (RSSItem item : rssList.getAllItem()) {
                            txtContent.append(String.format("%s - autor: %s%n", item.getTitle(), item.getAuthor()));
                        }
                    } catch (IOException | SAXException | ParserConfigurationException e1) {
                        e1.printStackTrace();
                    }

                }
            }
        });
*/
    }


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
