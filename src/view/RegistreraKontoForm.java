package view;

import controller.RegistreraController;

import javax.swing.*;
import java.awt.*;

public class RegistreraKontoForm extends JFrame {

    private JTextField namnField, efternamnField, epostField, ålderField, viktField, könField, dagligtMalField;
    private JPasswordField losenordField;
    private JLabel statusLabel;
    private RegistreraController controller;

    public RegistreraKontoForm() {
        controller = new RegistreraController();

        setTitle("Skapa konto");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 2, 10, 10));

        add(new JLabel("Förnamn:"));
        namnField = new JTextField();
        add(namnField);

        add(new JLabel("Efternamn:"));
        efternamnField = new JTextField();
        add(efternamnField);

        add(new JLabel("E-post:"));
        epostField = new JTextField();
        add(epostField);

        add(new JLabel("Ålder:"));
        ålderField = new JTextField();
        add(ålderField);

        add(new JLabel("Vikt (kg):"));
        viktField = new JTextField();
        add(viktField);

        add(new JLabel("Kön:"));
        könField = new JTextField();
        add(könField);

        add(new JLabel("Lösenord:"));
        losenordField = new JPasswordField();
        add(losenordField);

        add(new JLabel("Dagligt mål (kcal):"));
        dagligtMalField = new JTextField();
        add(dagligtMalField);

        JButton registreraButton = new JButton("Registrera");
        registreraButton.addActionListener(e -> registreraAnvandare());
        add(registreraButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        add(statusLabel);

        setVisible(true);
    }

    private void registreraAnvandare() {
        String namn = namnField.getText();
        String efternamn = efternamnField.getText();
        String epost = epostField.getText();
        String ålder = ålderField.getText();
        String vikt = viktField.getText();
        String kön = könField.getText();
        String losenord = new String(losenordField.getPassword());
        String dagligtMal = dagligtMalField.getText();

        // Kontrollera om alla fält är ifyllda
        if (namn.isEmpty() || efternamn.isEmpty() || epost.isEmpty() || ålder.isEmpty() || vikt.isEmpty() || kön.isEmpty() || losenord.isEmpty() || dagligtMal.isEmpty()) {
            statusLabel.setText("Alla fält måste fyllas i.");
            return;
        }

        // Försök att registrera användaren
        String resultat = controller.registreraAnvandare(namn, efternamn, epost, losenord, ålder, vikt, kön, dagligtMal);

        if (resultat.equals("OK")) {
            JOptionPane.showMessageDialog(this, "Registrering lyckades!\nLogga in med dina uppgifter.");
            dispose();
            new LoginForm();
        } else {
            statusLabel.setText(resultat);
            statusLabel.setForeground(Color.RED);
        }
    }
}
