package viewModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import core.DepositCalc;
import models.DepositRequest;
import models.DepositResponse;

public class DepositHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DepositHandler.class);

    public DepositResponse getDeposit(DepositRequest request) {
        LOGGER.info("[getDeposit] request " + request);

        DepositResponse response = new DepositResponse();
        DepositCalc depositCalc = new DepositCalc();
        depositCalc.setAmount(request.getAmount());
        depositCalc.setPeriod(request.getPeriod());
        depositCalc.setPercent(request.getPercent());
        depositCalc.setFrequency(request.getFrequency());
        depositCalc.setCapitalisation(request.isCapitalisation());
        depositCalc.setTax(request.getTax());
        depositCalc.setDeposits(request.getDeposits());
        depositCalc.setWithdrawals(request.getWithdrawals());


        try {

            depositCalc.calculate();
            response.setStatus("OK");
            response.setProfit(Math.round(depositCalc.getProfit() * 100.0) / 100.0);
            response.setTaxes(Math.round(depositCalc.getTaxes() * 100.0) / 100.0);
            response.setTotal(Math.round(depositCalc.getTotal() * 100.0) / 100.0);

            LOGGER.info("[getDeposit] response " + response);
        } catch (Exception ex) {
            response.setStatus("Error");
            response.setMessage(ex.getMessage());
            LOGGER.error("[getDeposit] just an error: " + ex.getMessage());
        }
        return response;
    }
}
