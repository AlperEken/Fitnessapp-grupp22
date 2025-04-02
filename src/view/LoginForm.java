package view;

import controller.LoginController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {

    private JTextField epostField;
    private JPasswordField losenordField;
    private JLabel statusLabel;
    private LoginController loginController = new LoginController();

    public LoginForm() {
        setTitle("Logga in");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("E-post:"));
        epostField = new JTextField();
        add(epostField);

        add(new JLabel("Lösenord:"));
        losenordField = new JPasswordField();
        add(losenordField);

        JButton loginButton = new JButton("Logga in");
        loginButton.addActionListener(this::loggaIn);
        add(loginButton);

        JButton skapaKontoBtn = new JButton("Skapa nytt konto");
        skapaKontoBtn.addActionListener(e -> {
            dispose();
            new RegistreraKontoForm();
        });
        add(skapaKontoBtn);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        add(statusLabel);

        setVisible(true);
    }

    private void loggaIn(ActionEvent e) {
        String epost = epostField.getText();
        String losenord = new String(losenordField.getPassword());

        boolean inloggad = loginController.loggaIn(epost, losenord);

        if (inloggad) {
            statusLabel.setText("Inloggning lyckades!");
            statusLabel.setForeground(Color.GREEN);

            dispose();
            new MainMenuForm(1); // Här kan du senare skicka korrekt kontoID

        } else {
            statusLabel.setText("Fel e-post eller lösenord.");
            statusLabel.setForeground(Color.RED);
        }
    }
}
