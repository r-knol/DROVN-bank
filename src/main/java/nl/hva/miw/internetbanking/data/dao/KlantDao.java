package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.Klant;

public interface KlantDao {
    Klant getKlantByGebruikersnaam(String gebruikersnaam);
}
