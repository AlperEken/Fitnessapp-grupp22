package controller;

import model.Konto;
import model.KontoDAO;

public class RegistreraController {

    private KontoDAO kontoDAO;

    public RegistreraController() {
        kontoDAO = new KontoDAO();
    }

    public String registreraAnvandare(String namn, String efternamn, String epost, String losenord, String ålder, String vikt, String kön, String dagligtMal) {
        // Kontrollera att alla fält är ifyllda
        if (namn.isEmpty() || efternamn.isEmpty() || epost.isEmpty() || losenord.isEmpty() || dagligtMal.isEmpty()) {
            return "Alla fält måste fyllas i.";
        }

        // Konvertera ålder, vikt och dagligt mål till rätt format
        int ålderInt = Integer.parseInt(ålder);
        double viktDouble = Double.parseDouble(vikt);
        int dagligtMalInt = Integer.parseInt(dagligtMal);

        // Skapa kontoobjekt
        Konto konto = new Konto(namn, efternamn, epost, losenord, ålderInt, viktDouble, kön, dagligtMalInt);
        boolean success = kontoDAO.registreraKonto(konto);

        if (success) {
            return "OK";
        } else {
            return "Registrering misslyckades. E-post kan redan vara registrerad.";
        }
    }
}
