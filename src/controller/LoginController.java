package controller;

import model.KontoDAO;

public class LoginController {

    private KontoDAO kontoDAO;

    public LoginController() {
        kontoDAO = new KontoDAO();
    }

    public boolean loggaIn(String epost, String losenord) {
        return kontoDAO.valideraInloggning(epost, losenord);
    }
}
