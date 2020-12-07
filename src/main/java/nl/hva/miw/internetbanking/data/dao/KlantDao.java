package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.Klant;

import java.sql.Connection;

public interface KlantDao {

    Klant getKlantByGebruikersnaam(String gebruikersnaam);
}
