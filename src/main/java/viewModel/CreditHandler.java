package viewModel;

import core.CreditCalc;
import models.CreditRequest;
import models.CreditResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreditHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditHandler.class);

    public CreditResponse getCredit(CreditRequest request) {

        LOGGER.info("[getCredit] request " + request);
        CreditResponse response = new CreditResponse();

        try {
            CreditCalc creditCalc = new CreditCalc(request.getAmount(), request.getPeriod(), request.getPercent());
            String type = request.getType();

            if ("type_annuity".equals(type)) {
                
                creditCalc.calculateAnnuity();
                response.setStatus("OK");
                response.setMonthlyPayment(String.format("%.2f", creditCalc.getMonthlyPay()));
                response.setOverPayment(Math.round(creditCalc.getOverPay() * 100.0) / 100.0);
                response.setTotalPayment(Math.round(creditCalc.getTotalPay() * 100.0) / 100.0);

            } else if ("type_differentiated".equals(type)) {
                
                double period = request.getPeriod();
                
                if (period <= 0) {
                    throw new Exception("Loan term should be longer 0");
                }

                double firstMonthPay = creditCalc.getMonthlyPay();
                double lastMonthPay = firstMonthPay;
                double overpay = 0;
                double totalpay = 0;
                
                for (int monthCount = 0; monthCount < period; monthCount++) {
                    creditCalc.calculateDifferentiated();
                    if (monthCount == 0) {
                        firstMonthPay = creditCalc.getMonthlyPay();
                    }
                    lastMonthPay = creditCalc.getMonthlyPay();
                    overpay += creditCalc.getOverPay();
                    totalpay += creditCalc.getTotalPay();
                }

                response.setStatus("OK");
                response.setMonthlyPayment(String.format("%.2f ... %.2f", firstMonthPay, lastMonthPay));
                response.setOverPayment(Math.round(overpay * 100.0) / 100.0);
                response.setTotalPayment(Math.round(totalpay * 100.0) / 100.0);
            }

            LOGGER.info("[getCredit] response " + response);
        } catch (Exception ex) {
            response.setStatus("Error");
            response.setMessage(ex.getMessage());
            LOGGER.error("[getCredit] just an error: " + ex.getMessage());
        }
        return response;
    }
}
