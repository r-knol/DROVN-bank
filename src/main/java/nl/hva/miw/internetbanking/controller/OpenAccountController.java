package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.service.RekeningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OpenAccountController {

    @Autowired
    private RekeningService rekeningService;

    private Logger logger = LoggerFactory.getLogger(OpenAccountController.class);

    @GetMapping("/toonRekeningOpenenForm")
    public String toonRekeningOpenenForm(Model model) {
        Account account = new Account();
        model.addAttribute("rekening", account);
        return "rekeningOpenen";
    }

  /*  @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveRekening(@ModelAttribute("rekening") Rekening rekening) {
        rekeningService.save(rekening);

        return "redirect:/";
    }*/

}
