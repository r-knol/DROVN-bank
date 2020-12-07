package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dao.KlantDao;
import nl.hva.miw.internetbanking.model.Klant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KlantService {

    private KlantDao klantDao;

    @Autowired
    public KlantService(KlantDao klantDao) { // zorgt jdbc klantdao object wordt geïnjecteerd
        this.klantDao = klantDao;
    }

    public boolean valideerLogin(String gebruikersnaam, String wachtwoord) { // checkt gegevens
        Klant klant = klantDao.getKlantByGebruikersnaam(gebruikersnaam);
        return klant != null && klant.getWachtwoord().equals(wachtwoord);
    }
}
