package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.Klant;
import nl.hva.miw.internetbanking.model.Rekening;

import java.util.List;

public interface RekeningDao {

     public List<Rekening> zoekRekOpKlant(Klant klant);
}
