package nl.hva.miw.internetbanking.service;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dao.BalanceDAO;
import nl.hva.miw.internetbanking.data.dao.LegalPersonDAO;
import nl.hva.miw.internetbanking.data.dao.NaturalPersonDAO;
import nl.hva.miw.internetbanking.data.dto.BalancePerSectorDTO;
import nl.hva.miw.internetbanking.data.dto.LegalPersonHasAccountDTO;
import nl.hva.miw.internetbanking.data.dto.NaturalPersonHasAccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j(topic = "BalanceService")
public class BalanceService {

    private final BalanceDAO balanceDAO;

    @Autowired
    public BalanceService(BalanceDAO balanceDAO) {
        this.balanceDAO = balanceDAO;
    }

    public List<NaturalPersonHasAccountDTO> getNaturalAccountsWithHighestBalance() {
        return balanceDAO.getNaturalAccountsWithHighestBalance();
    }

    public List<LegalPersonHasAccountDTO> getClientsWithHighestBalance() {
        return balanceDAO.getClientsWithHighestBalance();
    }

    public List<BalancePerSectorDTO> getAvgBalancePerSector() {
        return balanceDAO.getAvgBalancePerSector();
    }
}
