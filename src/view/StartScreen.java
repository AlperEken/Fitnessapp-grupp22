package view;

import javax.swing.*;
import java.awt.*;

public class StartScreen extends JFrame {

    public StartScreen() {
        setTitle("Välkommen till FitnessApp");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Appnamn
        JLabel titleLabel = new JLabel("FitnessApp", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Ladda och skala bild
        ImageIcon originalIcon = new ImageIcon(getClass().getClassLoader().getResource("resources/fitness_splash.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(700, 350, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        // Startknapp
        JButton startBtn = new JButton("Starta appen");
        startBtn.setFont(new Font("Arial", Font.PLAIN, 18));
        startBtn.addActionListener(e -> {
            dispose(); // Stäng splash screen
            new LoginForm(); // Starta login
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        bottomPanel.add(startBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
