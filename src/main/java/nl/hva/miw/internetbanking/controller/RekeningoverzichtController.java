//package nl.hva.miw.internetbanking.controller;
//
//import nl.hva.miw.internetbanking.model.Klant;
//import nl.hva.miw.internetbanking.model.Rekening;
//import nl.hva.miw.internetbanking.service.RekeningService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.Mapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class RekeningoverzichtController {
//    private List<Rekening> rekeningLijst;
//
//    private RekeningService rekeningService;
//    private Logger logger = LoggerFactory.getLogger(RekeningoverzichtController.class);
//
//    @Autowired
//    public RekeningoverzichtController(RekeningService rekeningService) {
//        logger.warn("New RekeningoverzichtController.");
//    }
//
//    @GetMapping("/rekeningoverzicht") // http://localhost:8080/rekeningoverzicht
//    public String rekeningoverzichtHandler(Model model) {
//// onderstaande code null-pointers
////        if (ingelogdeKlant != null) {
////            rekeningLijst = rekeningService.zoekRekOpKlant(ingelogdeKlant);
//            model.addAttribute("alleRekeningen", rekeningLijst);
////            logger.info("De rekeningen van klant " + ingelogdeKlant + " worden getoond.");
////        }
//        return "pages/rekeningoverzicht";
//    }
//
//    @GetMapping("/nieuwe-klant") // http://localhost:8080/nieuwe-klant
//    public String nieuweKlantHandler() {
//        return "";
//    } // TODO: verwijzen naar nieuwe klant pagina
//
//
//}
