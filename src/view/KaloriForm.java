package view;

import controller.KaloriController;

import javax.swing.*;
import java.awt.*;

public class KaloriForm extends JFrame {

    private JTextField matnamnField, kalorierField;
    private JLabel resultatLabel;
    private int kontoID;
    private KaloriController controller = new KaloriController();

    public KaloriForm(int kontoID) {
        this.kontoID = kontoID;

        setTitle("Kaloriräknare");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 10, 10));

        add(new JLabel("Maträtt:"));
        matnamnField = new JTextField();
        add(matnamnField);

        add(new JLabel("Kalorier:"));
        kalorierField = new JTextField();
        add(kalorierField);

        JButton läggTillBtn = new JButton("Lägg till");
        läggTillBtn.addActionListener(e -> läggTillKalorier());
        add(läggTillBtn);

        resultatLabel = new JLabel("", SwingConstants.CENTER);
        add(resultatLabel);

        setVisible(true);
        uppdateraTotal();
    }

    private void läggTillKalorier() {
        String mat = matnamnField.getText();
        int kalorier;

        try {
            kalorier = Integer.parseInt(kalorierField.getText());
        } catch (NumberFormatException e) {
            resultatLabel.setText("Kalorier måste vara ett heltal.");
            return;
        }

        boolean success = controller.läggTillKalorier(kontoID, mat, kalorier);

        if (success) {
            matnamnField.setText("");
            kalorierField.setText("");
            uppdateraTotal();
        } else {
            resultatLabel.setText("Fel vid sparning.");
        }
    }

    private void uppdateraTotal() {
        int total = controller.hämtaTotalKalorierIdag(kontoID);
        int mal = controller.hämtaDagligtMal(kontoID);

        resultatLabel.setText("Totalt idag: " + total + " / " + mal + " kcal");
    }
}
