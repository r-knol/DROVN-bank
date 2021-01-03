package nl.hva.miw.internetbanking.model;

import lombok.Getter;

@Getter
public enum Sector {

    RETAIL("Detailhandel, groothandel en autohandel"),
    HOSPITALITY("Horeca"),
    EDUCATION("Onderwijs"),
    CARE("Gezondheidszorg"),
    REALESTATE("Onroerend goed"),
    CONSTRUCTION("Bouw, installatie en infrastructuur"),
    PROFESSIONALSERVICES("Zakelijke dienstverlening"),
    PERSONALSERVICES("Persoonlijke dienstverlening"),
    CULTURESPORTS("Cultuur en sport"),
    FINANCIALSERVICES("Financiële dienstverlening"),
    TRANSPORT("Vervoer, post en opslag"),
    FARMING("Agrosector"),
    MANUFACTURING("Industrie"),
    WATERWASTE("Water en afval"),
    ENERGIE("Energie"),
    CONSULTANCY("Advies en consultancy"),
    IT("ICT, media en communicatie");


    @Getter
    private final String labelSector;

    Sector(String labelSector) {
        this.labelSector = labelSector;
    }
}
