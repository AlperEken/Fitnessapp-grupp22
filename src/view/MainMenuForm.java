package view;

import javax.swing.*;
import model.GoogleMapsService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuForm extends JFrame {

    private GoogleMapsService googleMapsService;

    public MainMenuForm(int i) {
        // Grundläggande inställningar för fönstret
        setTitle("FitnessApp - Main Menu");
        setSize(600, 400);  // Större fönsterstorlek
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));  // Vertikal layout

        // Skapa GoogleMapsService för att hantera geolokalisering och söka efter utegym
        googleMapsService = new GoogleMapsService();

        // Skapa gamla knappar för kaloriloggen och statistik
        JButton calorieLogButton = new JButton("Logga kalorier");
        calorieLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lägg till funktionalitet för kaloriloggen här
                JOptionPane.showMessageDialog(null, "Kaloriloggen öppnas...");
                // T.ex. visa en ny dialog eller fönster för att hantera kalorier
            }
        });

        JButton statisticsButton = new JButton("Visa statistik");
        statisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lägg till funktionalitet för statistik här
                JOptionPane.showMessageDialog(null, "Statistik visas här...");
                // T.ex. visa statistik om kalorier, mål och prestationer
            }
        });

        // Lägg till knapparna för kaloriloggen och statistik
        add(calorieLogButton);
        add(statisticsButton);

        // Skapa en knapp för att hämta närmaste utegym via Google Maps API
        JButton findGymsButton = new JButton("Hitta närmaste utegym");
        findGymsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Här använder vi en statisk position för att testa (Malmö)
                double userLat = 55.604981;  // Latitud för Malmö
                double userLng = 13.003822;  // Longitud för Malmö

                // Hämta närmaste utegym från Google Places API
                String response = googleMapsService.getNearbyOutdoorGyms(userLat, userLng);
                JOptionPane.showMessageDialog(null, response);
            }
        });

        // Lägg till knappen för att hitta närmaste utegym
        add(findGymsButton);

        // Gör fönstret synligt
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenuForm(1);  // Starta applikationen
    }
}
